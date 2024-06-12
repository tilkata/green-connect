package com.greenconnect.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserDTO {
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    private String username;

    @NotBlank
    @Size(min = 8)
    private String password;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    @Size(min = 1, max = 100)
    private String fullName;

    private String profilePictureUrl;
    private String bio;
    private LocalDate birthdate;
    private String address;
    private String city;
    private String country;
    private boolean consentGiven;

}
