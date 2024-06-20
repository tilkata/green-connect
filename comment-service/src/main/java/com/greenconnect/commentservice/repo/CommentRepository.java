package com.greenconnect.commentservice.repo;

import com.greenconnect.commentservice.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
    List<Comment> findByParentId(Long parentId);
    void deleteByPostId(Long postId);
    void deleteByAuthorId(Long authorId);
}
