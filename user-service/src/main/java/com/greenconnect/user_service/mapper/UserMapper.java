package com.greenconnect.user_service.mapper;

import com.greenconnect.user_service.dto.UserDTO;
import com.greenconnect.user_service.model.User;

public class UserMapper {
    public static UserDTO toUserDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setFullName(user.getFullName());
        dto.setProfilePictureUrl(user.getProfilePictureUrl());
        dto.setBio(user.getBio());
        dto.setBirthdate(user.getBirthdate());
        dto.setAddress(user.getAddress());
        dto.setCity(user.getCity());
        dto.setCountry(user.getCountry());
        dto.setConsentGiven(user.isConsentGiven());
        return dto;
    }

    public static User toUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setPassword(userDTO.getPassword()); // Encryption handled in service
        user.setEmail(userDTO.getEmail());
        user.setFullName(userDTO.getFullName());
        user.setProfilePictureUrl(userDTO.getProfilePictureUrl());
        user.setBio(userDTO.getBio());
        user.setBirthdate(userDTO.getBirthdate());
        user.setAddress(userDTO.getAddress());
        user.setCity(userDTO.getCity());
        user.setCountry(userDTO.getCountry());
        user.setConsentGiven(userDTO.isConsentGiven());
        return user;
    }
}
