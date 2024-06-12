package com.greenconnect.postservice.config;

import com.azure.storage.blob.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AzureBlobStorageConfig {

    @Value("${azure.storage.container.name}")
    private String containerName;

    @Value("${azure.storage.connection.string}")
    private String connectionString;

    @Bean
    public BlobServiceClient getBlobServiceClient() {

        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString)
                .buildClient();

        return serviceClient;
    }

    @Bean
    public BlobContainerClient getBlobContainerClient() {
        BlobContainerClient containerClient = getBlobServiceClient().getBlobContainerClient(containerName);
        return containerClient;
    }
}
