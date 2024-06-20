package com.greenconnect.commentservice.service;

import com.greenconnect.commentservice.dto.CommentDTO;
import com.greenconnect.commentservice.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentService {
    Page<Comment> getCommentsByPostId(Long postId, Pageable pageable);
    Comment createComment(CommentDTO commentDTO);
    Comment updateComment(Long commentId, CommentDTO commentDTO);
    void deleteComment(Long commentId, Long userId);

    void deleteCommentsByPostId(Long postId);
    void deleteCommentsByAuthorId(Long userId);
}