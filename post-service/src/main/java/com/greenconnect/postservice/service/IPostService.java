package com.greenconnect.postservice.service;

import com.greenconnect.postservice.dto.PostDTO;
import com.greenconnect.postservice.model.Post;

import java.util.List;

public interface IPostService {
    List<Post> findAllPosts(int page, int size);
    List<Post> findPostsByCategory(String category, int page, int size);
    Post getPostById(Long postId);
    Post createPost(PostDTO postDTO, Long userId);
    Post updatePost(Long postId, PostDTO postDTO, Long userId);
    void deletePost(Long postId, Long userId);
    void likePost(Long postId, Long userId);
    void unlikePost(Long postId, Long userId);
}

