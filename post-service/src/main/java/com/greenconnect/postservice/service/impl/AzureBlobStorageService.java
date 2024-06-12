package com.greenconnect.postservice.service.impl;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobStorageException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureBlobStorageService {
//
//    private final BlobServiceClient blobServiceClient;
//
//    private final String containerName;
//
//    public AzureBlobStorageService(BlobServiceClient blobServiceClient, String containerName) {
//        this.blobServiceClient = blobServiceClient;
//        this.containerName = containerName;
//    }
//
//    // Create or Upload
//    public String uploadPostContent(MultipartFile file, Long userId) throws IOException {
//        String originalFileName = file.getOriginalFilename();
//        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
//        String filePath = "user_" + userId + "/" + uniqueFileName;
//
//        BlobClient blobClient = blobServiceClient
//                .getBlobContainerClient(containerName)
//                .getBlobClient(filePath);
//
//        blobClient.upload(file.getInputStream(), file.getSize(), true);
//
//        return blobClient.getBlobUrl();
//    }
//
//    // Read or Download
//    public InputStream downloadFile(Long userId, String fileName) {
//        String filePath = "user_" + userId + "/" + fileName;
//        BlobClient blobClient = blobServiceClient
//                .getBlobContainerClient(containerName)
//                .getBlobClient(filePath);
//
//        if (blobClient.exists()) {
//            return blobClient.openInputStream();
//        } else {
//            throw new RuntimeException("File not found: " + filePath);
//        }
//    }
//
//    // Update
//    public String updateFile(MultipartFile file, Long userId, String existingFileName) throws IOException {
//        deleteFile(userId, existingFileName); // Delete the old file
//        return uploadPostContent(file, userId); // Upload the new file
//    }
//
//    // Delete
//    public void deleteFile(Long userId, String fileName) {
//        String filePath = "user_" + userId + "/" + fileName;
//        BlobClient blobClient = blobServiceClient
//                .getBlobContainerClient(containerName)
//                .getBlobClient(filePath);
//
//        if (blobClient.exists()) {
//            blobClient.delete();
//        } else {
//            throw new RuntimeException("File not found: " + filePath);
//        }
//    }
//
//    // Delete all files by user ID
//    public void deleteAllFilesByUserId(Long userId) {
//        String userDirectory = "user_" + userId + "/";
//
//        blobServiceClient
//                .getBlobContainerClient(containerName)
//                .listBlobsByHierarchy(userDirectory)
//                .forEach(blobItem -> {
//                    BlobClient blobClient = blobServiceClient
//                            .getBlobContainerClient(containerName)
//                            .getBlobClient(blobItem.getName());
//
//                    try {
//                        blobClient.delete();
//                    } catch (BlobStorageException e) {
//                        throw new RuntimeException("Error deleting blob: " + blobItem.getName(), e);
//                    }
//                });
//    }
}
