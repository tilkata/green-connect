package com.greenconnect.postservice.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.greenconnect.postservice.dto.PostDTO;
import com.greenconnect.postservice.mapper.PostMapper;
import com.greenconnect.postservice.model.*;
import com.greenconnect.postservice.repo.LikeRepository;
import com.greenconnect.postservice.repo.PostRepository;
import com.greenconnect.postservice.service.IPostService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements IPostService {

    @Autowired
    private final PostRepository postRepository;

    @Autowired
    private final LikeRepository likeRepository;

    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Value("${azure.blob-storage.connection-string}")
    private String connectionString;

    private BlobServiceClient blobServiceClient;
    @PostConstruct
    public void init() {
        blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
    }

    //Method from the video
    public String uploadPostContent(MultipartFile file,Long userId) throws IOException {
        String fileName = file.getOriginalFilename() +'/'+userId;
        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(fileName);

        blobClient.upload(file.getInputStream(),file.getSize(),true);

        return blobClient.getBlobUrl();
    }

    @Override
    public List<Post> findAllPosts(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).getContent();
    }

    @Override
    public List<Post> findPostsByCategory(String category, int page, int size) {
        return postRepository.findByCategory(category, PageRequest.of(page, size));
    }

    @Override
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
    }

    @Override
    @Transactional
    public Post createPost(PostDTO postDTO, Long userId) {
        Post post = PostMapper.toPost(postDTO);
        post.setAuthorId(userId);
        post.setContentIdentifier(postDTO.getContentIdentifier());
        return postRepository.save(post);
    }

    @Transactional
    public Post createPost(PostDTO postDTO, Long userId,MultipartFile file) throws IOException {
        Post post = PostMapper.toPost(postDTO);
        post.setAuthorId(userId);
        post.setContentIdentifier(uploadPostContent(file,userId));

        return postRepository.save(post);
    }

    @Override
    @Transactional
    public Post updatePost(Long postId, PostDTO postDTO, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if (!post.getAuthorId().equals(userId)) {
            throw new SecurityException("Unauthorized attempt to edit a post");
        }
        post.setCategory(postDTO.getCategory());
        post.setTags(postDTO.getTags());
        post.setVisibility(postDTO.getVisibility());
        post.setContentIdentifier(postDTO.getContentIdentifier());
        return postRepository.save(post);
    }

    @Override
    @Transactional
    public void deletePost(Long postId, Long userId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found"));
        if (!post.getAuthorId().equals(userId)) {
            throw new SecurityException("Unauthorized attempt to delete a post");
        }
        // Delete all likes associated with this post
        //likeRepository.deleteByPostId(postId);
        postRepository.delete(post);
    }

    @Override
    @Transactional
    public void likePost(Long postId, Long userId) {
        if (!likeRepository.existsByPostIdAndUserId(postId, userId)) {
            Like like = new Like();
            like.setPost(postRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("Post not found")));
            like.setUserId(userId);
            likeRepository.save(like);
        }
    }

    @Override
    @Transactional
    public void unlikePost(Long postId, Long userId) {
        Like like = likeRepository.findByPostIdAndUserId(postId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Like not found"));
        likeRepository.delete(like);
    }
}
