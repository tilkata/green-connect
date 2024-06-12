package com.greenconnect.commentservice.controller;

import com.greenconnect.commentservice.dto.CommentDTO;
import com.greenconnect.commentservice.model.Comment;
import com.greenconnect.commentservice.service.CommentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentControllerTest {

    @Mock
    private CommentService commentService;

    @InjectMocks
    private CommentController commentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentsByPostId() {
        // Arrange
        Long postId = 1L;
        int page = 0;
        int size = 10;
        Pageable pageable = PageRequest.of(page, size);
        Comment comment = new Comment();
        comment.setPostId(postId);
        Page<Comment> commentPage = new PageImpl<>(Collections.singletonList(comment));

        when(commentService.getCommentsByPostId(postId, pageable)).thenReturn(commentPage);

        // Act
        ResponseEntity<Page<Comment>> response = commentController.getCommentsByPostId(postId, page, size);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().getTotalElements());
        assertEquals(postId, response.getBody().getContent().get(0).getPostId());

        verify(commentService, times(1)).getCommentsByPostId(postId, pageable);
    }

    @Test
    void testCreateComment() {
        // Arrange
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setPostId(1L);
        commentDTO.setAuthorId(1L);
        commentDTO.setContent("Test content");

        Comment comment = new Comment();
        comment.setId(1L);
        comment.setPostId(commentDTO.getPostId());
        comment.setAuthorId(commentDTO.getAuthorId());
        comment.setContent(commentDTO.getContent());

        when(commentService.createComment(any(CommentDTO.class))).thenReturn(comment);

        // Act
        ResponseEntity<Comment> response = commentController.createComment(commentDTO);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(commentDTO.getPostId(), response.getBody().getPostId());
        assertEquals(commentDTO.getAuthorId(), response.getBody().getAuthorId());
        assertEquals(commentDTO.getContent(), response.getBody().getContent());

        verify(commentService, times(1)).createComment(any(CommentDTO.class));
    }

    @Test
    void testUpdateComment() {
        // Arrange
        Long commentId = 1L;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthorId(1L);
        commentDTO.setContent("Updated content");

        Comment comment = new Comment();
        comment.setId(commentId);
        comment.setAuthorId(commentDTO.getAuthorId());
        comment.setContent(commentDTO.getContent());

        when(commentService.updateComment(commentId, commentDTO)).thenReturn(comment);

        // Act
        ResponseEntity<Comment> response = commentController.updateComment(commentId, commentDTO);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(commentDTO.getContent(), response.getBody().getContent());

        verify(commentService, times(1)).updateComment(commentId, commentDTO);
    }

    @Test
    void testDeleteComment() {
        // Arrange
        Long commentId = 1L;
        Long userId = 1L;

        doNothing().when(commentService).deleteComment(commentId, userId);

        // Act
        ResponseEntity<Void> response = commentController.deleteComment(commentId, userId);

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        verify(commentService, times(1)).deleteComment(commentId, userId);
    }
}