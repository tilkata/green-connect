package com.greenconnect.user_service.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    private String profilePictureUrl;
    private String bio;
    private LocalDate birthdate;

    // Address information
    private String address;
    private String city;
    private String country;

    // GDPR consent
    private boolean consentGiven;
}

