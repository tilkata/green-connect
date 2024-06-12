package com.greenconnect.commentservice.service;

import com.greenconnect.commentservice.dto.CommentDTO;
import com.greenconnect.commentservice.model.Comment;
import com.greenconnect.commentservice.repo.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    @Cacheable(value = "comments", key = "#postId")
    public Page<Comment> getCommentsByPostId(Long postId, Pageable pageable) {
        return commentRepository.findByPostId(postId, pageable);
    }

    @Override
    @Transactional
    @CacheEvict(value = "comments", key = "#commentDTO.postId")
    public Comment createComment(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setPostId(commentDTO.getPostId());
        comment.setAuthorId(commentDTO.getAuthorId());
        comment.setContent(commentDTO.getContent());
        if (commentDTO.getParentId() != null) {
            Comment parent = commentRepository.findById(commentDTO.getParentId())
                    .orElseThrow(() -> new IllegalArgumentException("Parent comment not found"));
            comment.setParent(parent);
        }
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    @CacheEvict(value = "comments", key = "#commentDTO.postId")
    public Comment updateComment(Long commentId, CommentDTO commentDTO) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (!comment.getAuthorId().equals(commentDTO.getAuthorId())) {
            throw new SecurityException("Unauthorized attempt to edit comment");
        }
        comment.setContent(commentDTO.getContent());
        return commentRepository.save(comment);
    }

    @Override
    @Transactional
    @CacheEvict(value = "comments", key = "#commentId")
    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found"));
        if (!comment.getAuthorId().equals(userId)) {
            throw new SecurityException("Unauthorized attempt to delete comment");
        }
        commentRepository.delete(comment);
    }
}
