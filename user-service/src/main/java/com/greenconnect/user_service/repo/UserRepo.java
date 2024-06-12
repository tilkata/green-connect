package com.greenconnect.user_service.repo;

import com.greenconnect.user_service.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
