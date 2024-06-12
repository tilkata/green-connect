package com.greenconnect.postservice.controller;

import com.greenconnect.postservice.dto.PostDTO;
import com.greenconnect.postservice.model.Post;
import com.greenconnect.postservice.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostServiceImpl postService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        List<Post> posts = postService.findAllPosts(page, size);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Post>> getPostsByCategory(@PathVariable String category,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "10") int size) {
        List<Post> posts = postService.findPostsByCategory(category, page, size);
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable Long id) {
        Post post = postService.getPostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO, @RequestParam Long userId) {
        Post createdPost = postService.createPost(postDTO, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO, @RequestParam Long userId) {
        Post updatedPost = postService.updatePost(id, postDTO, userId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable Long id, @RequestParam Long userId) {
        postService.deletePost(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/like")
    public ResponseEntity<Void> likePost(@PathVariable Long id, @RequestParam Long userId) {
        postService.likePost(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/{id}/unlike")
    public ResponseEntity<Void> unlikePost(@PathVariable Long id, @RequestParam Long userId) {
        postService.unlikePost(id, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
