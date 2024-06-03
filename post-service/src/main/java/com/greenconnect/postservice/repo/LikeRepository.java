package com.greenconnect.postservice.repo;
import com.greenconnect.postservice.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findByPostId(Long postId);
    boolean existsByPostIdAndUserId(Long postId, Long userId);
    Optional<Like> findByPostIdAndUserId(Long postId, Long userId);
}
