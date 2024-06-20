package com.greenconnect.user_service.service;

import com.greenconnect.user_service.dto.UserDTO;
import com.greenconnect.user_service.mapper.UserMapper;
import com.greenconnect.user_service.model.User;
import com.greenconnect.user_service.repo.UserRepo;
import com.greenconnect.user_service.until.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepository;

//comment
    public UserDTO createUser(@Valid UserDTO userDTO) {
        User user = UserMapper.toUser(userDTO);
        user.setPassword(PasswordUtil.hashPassword(user.getPassword()));
        User savedUser = userRepository.save(user);
        return UserMapper.toUserDTO(savedUser);
    }

    @Cacheable(value = "users", key = "#id")
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(UserMapper::toUserDTO);
    }

    @Cacheable(value = "users", key = "#username")
    public UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return UserMapper.toUserDTO(user);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream().map(UserMapper::toUserDTO).collect(Collectors.toList());
    }

    @CacheEvict(value = "users", allEntries = true)
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
