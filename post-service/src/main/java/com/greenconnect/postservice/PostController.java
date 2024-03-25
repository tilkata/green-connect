package com.greenconnect.postservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(
            @RequestBody Post post){
        service.savePost(post);
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAllPosts(){
        return ResponseEntity.ok(service.findAllPosts());
    }

    @PutMapping("/{post-id}")
    public ResponseEntity<Void> updatePost(@PathVariable("post-id") Integer postId, @RequestBody Post post) {
        post.setId(postId);
        service.updatePost(post);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{post-id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable("post-id") Integer postId) {
        service.deletePost(postId);
    }
    @GetMapping("/with-comments/{post-id}")
    public ResponseEntity<FullPostResponse> findAllPosts(
            @PathVariable("post-id") Integer postId
    ){
        return ResponseEntity.ok(service.findPostsWithComments(postId));
    }
}
