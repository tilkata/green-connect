package com.greenconnect.postservice.service.impl;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.blob.models.BlobItem;
import com.azure.storage.blob.models.BlobStorageException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@Service
public class AzureBlobStorageService {

    private final BlobServiceClient blobServiceClient;
    private final String containerName;

    public AzureBlobStorageService(
            @Value("${azure.blob-storage.connection-string}") String connectionString,
            @Value("${spring.cloud.azure.storage.blob.container-name}") String containerName) {
        this.blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();
        this.containerName = containerName;
    }

    // Create or Upload
    public String uploadPostContent(MultipartFile file, Long userId) throws IOException {
        String originalFileName = file.getOriginalFilename();
        String uniqueFileName = UUID.randomUUID().toString() + "_" + originalFileName;
        String filePath = "user_" + userId + "/" + uniqueFileName;

        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(filePath);

        blobClient.upload(file.getInputStream(), file.getSize(), true);

        return blobClient.getBlobUrl();
    }

    // Read or Download
    public InputStream downloadFile(Long userId, String fileName) {
        String filePath = "user_" + userId + "/" + fileName;
        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(filePath);

        if (blobClient.exists()) {
            return blobClient.openInputStream();
        } else {
            throw new RuntimeException("File not found: " + filePath);
        }
    }

    // Update
    public String updateFile(MultipartFile file, Long userId, String existingFileName) throws IOException {
        deleteFile(userId, existingFileName);
        return uploadPostContent(file, userId);
    }

    // Delete
    public void deleteFile(Long userId, String fileName) {
        String filePath = "user_" + userId + "/" + fileName;
        BlobClient blobClient = blobServiceClient
                .getBlobContainerClient(containerName)
                .getBlobClient(filePath);

        if (blobClient.exists()) {
            blobClient.delete();
        } else {
            throw new RuntimeException("File not found: " + filePath);
        }
    }

    // Delete all files by user ID
    public void deleteAllFilesByUserId(Long userId) {
        String userDirectory = "user_" + userId + "/";
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);

        PagedIterable<BlobItem> blobs = containerClient.listBlobsByHierarchy(userDirectory);

        if (!blobs.iterator().hasNext()) {
            // Log or handle the case when no blobs are found
            System.out.println("No blobs found for user directory: " + userDirectory);
            return;
        }

        blobs.forEach(blobItem -> {
            BlobClient blobClient = containerClient.getBlobClient(blobItem.getName());
            try {
                blobClient.delete();
                System.out.println("Deleted blob: " + blobItem.getName());
            } catch (BlobStorageException e) {
                // Log the error instead of throwing an exception to continue with the next blob
                System.err.println("Error deleting blob: " + blobItem.getName() + " - " + e.getMessage());
            }
        });
    }

}
