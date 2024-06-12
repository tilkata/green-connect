package com.greenconnect.user_service.controller;

import com.greenconnect.user_service.dto.UserDTO;
import com.greenconnect.user_service.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping("/{id}")
    public Optional<UserDTO> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username/{username}")
    public UserDTO getUserByUsername(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }

    @GetMapping
    public List<UserDTO> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}/data")
    public Optional<UserDTO> getUserData(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteUserData(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
