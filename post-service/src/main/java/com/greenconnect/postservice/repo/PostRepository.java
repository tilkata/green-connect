package com.greenconnect.postservice.repo;

import com.greenconnect.postservice.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByCategory(String category, Pageable pageable);
}
