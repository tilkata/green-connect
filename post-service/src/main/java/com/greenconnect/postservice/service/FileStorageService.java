package com.greenconnect.postservice.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageService {

    public String uploadFile(MultipartFile file) {
        String fileName = UUID.randomUUID().toString() + "-" + file.getOriginalFilename();
        try {
            // Ensure this directory exists on the server
            String uploadDir = "/uploads/";
            Files.copy(file.getInputStream(), Paths.get(uploadDir + fileName), StandardCopyOption.REPLACE_EXISTING);
            return uploadDir + fileName;
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file " + fileName, e);
        }
    }
}

