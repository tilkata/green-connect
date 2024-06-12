package com.greenconnect.commentservice.controller;

import com.greenconnect.commentservice.dto.CommentDTO;
import com.greenconnect.commentservice.model.Comment;
import com.greenconnect.commentservice.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/post/{postId}")
    public ResponseEntity<Page<Comment>> getCommentsByPostId(@PathVariable Long postId,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = commentService.getCommentsByPostId(postId, pageable);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Comment> createComment(@RequestBody CommentDTO commentDTO) {
        Comment createdComment = commentService.createComment(commentDTO);
        return new ResponseEntity<>(createdComment, HttpStatus.CREATED);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long commentId, @RequestBody CommentDTO commentDTO) {
        Comment updatedComment = commentService.updateComment(commentId, commentDTO);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId, @RequestParam Long userId) {
        commentService.deleteComment(commentId, userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}