package com.greenconnect.postservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greenconnect.postservice.dto.PostDTO;
import com.greenconnect.postservice.model.Post;
import com.greenconnect.postservice.service.impl.PostServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private final PostServiceImpl postService;

    public PostController(PostServiceImpl postService) {
        this.postService = postService;
    }

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

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Post> createPost(@RequestBody PostDTO postDTO, @RequestParam Long userId) {
        Post createdPost = postService.createPost(postDTO, userId);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "http://localhost:5173")
    @PostMapping("/upload")
    public ResponseEntity<Post> createPost(
            @RequestParam("postDTO") String postDTOStr,
            @RequestParam Long userId,
            @RequestParam MultipartFile file) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        PostDTO postDTO = objectMapper.readValue(postDTOStr, PostDTO.class);

        Post createdPost = postService.createPost(postDTO, userId, file);
        return new ResponseEntity<>(createdPost, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody PostDTO postDTO, @RequestParam Long userId) {
        Post updatedPost = postService.updatePost(id, postDTO, userId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @DeleteMapping("/{authorId}")
    public ResponseEntity<Void> deletePostByAuthorId(@PathVariable Long authorId) {
        postService.deleteAllPosts(authorId);
        return new ResponseEntity<>(HttpStatus.GONE);
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

    @GetMapping("/{userId}")
    public List<Post> getPostsByUserId(@PathVariable Long userId) {
        return postService.findPostsByUserId(userId);
    }

}
