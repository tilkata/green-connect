package com.greenconnect.commentservice;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService service;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void savePost(
            @RequestBody Comment comment){
        service.saveComment(comment);
    }

    @GetMapping
    public ResponseEntity<List<Comment>> findAllComments(){
        return ResponseEntity.ok(service.findAllComments());
    }
    @GetMapping("/post/{post-id}")
    public ResponseEntity<List<Comment>> findAllComments(
            @PathVariable("post-id") Integer postId
    ){
        return ResponseEntity.ok(service.findAllCommentsByPost(postId));
    }
}
