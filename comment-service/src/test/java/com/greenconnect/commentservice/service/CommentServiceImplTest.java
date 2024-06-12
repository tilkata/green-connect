package com.greenconnect.commentservice.service;

import com.greenconnect.commentservice.dto.CommentDTO;
import com.greenconnect.commentservice.model.Comment;
import com.greenconnect.commentservice.repo.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CommentServiceImplTest {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentServiceImpl commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCommentsByPostId() {
        // Arrange
        Long postId = 1L;
        Pageable pageable = PageRequest.of(0, 10);
        Comment comment = new Comment();
        comment.setPostId(postId);
        Page<Comment> commentPage = new PageImpl<>(Collections.singletonList(comment));

        when(commentRepository.findByPostId(postId, pageable)).thenReturn(commentPage);

        // Act
        Page<Comment> result = commentService.getCommentsByPostId(postId, pageable);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals(postId, result.getContent().get(0).getPostId());

        verify(commentRepository, times(1)).findByPostId(postId, pageable);
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

        when(commentRepository.save(any(Comment.class))).thenReturn(comment);

        // Act
        Comment result = commentService.createComment(commentDTO);

        // Assert
        assertNotNull(result);
        assertEquals(commentDTO.getPostId(), result.getPostId());
        assertEquals(commentDTO.getAuthorId(), result.getAuthorId());
        assertEquals(commentDTO.getContent(), result.getContent());

        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testUpdateComment() {
        // Arrange
        Long commentId = 1L;
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setAuthorId(1L);
        commentDTO.setContent("Updated content");

        Comment existingComment = new Comment();
        existingComment.setId(commentId);
        existingComment.setAuthorId(commentDTO.getAuthorId());
        existingComment.setContent("Original content");

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));
        when(commentRepository.save(any(Comment.class))).thenReturn(existingComment);

        // Act
        Comment result = commentService.updateComment(commentId, commentDTO);

        // Assert
        assertNotNull(result);
        assertEquals(commentDTO.getContent(), result.getContent());

        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).save(any(Comment.class));
    }

    @Test
    void testDeleteComment() {
        // Arrange
        Long commentId = 1L;
        Long userId = 1L;

        Comment existingComment = new Comment();
        existingComment.setId(commentId);
        existingComment.setAuthorId(userId);

        when(commentRepository.findById(commentId)).thenReturn(Optional.of(existingComment));

        // Act
        commentService.deleteComment(commentId, userId);

        // Assert
        verify(commentRepository, times(1)).findById(commentId);
        verify(commentRepository, times(1)).delete(existingComment);
    }
}