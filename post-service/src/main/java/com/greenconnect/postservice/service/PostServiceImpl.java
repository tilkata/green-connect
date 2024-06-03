package com.greenconnect.postservice.service;

import com.greenconnect.postservice.dto.PostDTO;
import com.greenconnect.postservice.model.*;
import com.greenconnect.postservice.repo.LikeRepository;
import com.greenconnect.postservice.repo.PostRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private LikeRepository likeRepository;

    @Autowired
    private FileStorageService fileStorageService;

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
        Post post = new Post();
        post.setAuthorId(userId);
        post.setCategory(postDTO.getCategory());
        post.setTags(postDTO.getTags());
        post.setVisibility(postDTO.getVisibility());
        post.setContent(createContent(postDTO));
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
        post.setContent(createContent(postDTO));
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
        List<Like> likes = likeRepository.findByPostId(postId);
        likeRepository.deleteAll(likes);

        // Delete the post
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

    private Content createContent(PostDTO postDTO) {
        switch (postDTO.getContentType().toLowerCase()) {
            case "text":
                TextContent textContent = new TextContent();
                textContent.setText(postDTO.getContent());
                return textContent;
            case "image":
                ImageContent imageContent = new ImageContent();
                String imageUrl = fileStorageService.uploadFile(postDTO.getFile());
                imageContent.setImageUrl(imageUrl);
                return imageContent;
            case "video":
                VideoContent videoContent = new VideoContent();
                String videoUrl = fileStorageService.uploadFile(postDTO.getFile());
                videoContent.setVideoUrl(videoUrl);
                return videoContent;
            default:
                throw new IllegalArgumentException("Unsupported content type: " + postDTO.getContentType());
        }
    }
}
