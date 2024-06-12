package com.greenconnect.postservice.service.impl;

import com.azure.core.http.rest.PagedIterable;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import com.greenconnect.postservice.model.Content;
import com.greenconnect.postservice.service.IAzureBlobStorage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AzureBlobStorageImpl implements IAzureBlobStorage {

    @Autowired
    BlobServiceClient blobServiceClient;
    @Autowired
    BlobContainerClient containerClient;
    @Value("${azure.storage.container.name}")
    private String containerName;


    @Override
    public String write(Content content) throws IOException {
        BlobContainerClient containerClient = blobServiceClient.getBlobContainerClient(containerName);
        BlobClient blobClient = containerClient.getBlobClient(content.getFileName());

        try (InputStream inputStream = content.getInputStream()) {
            blobClient.upload(inputStream, inputStream.available(), false);
        }

        return blobClient.getBlobUrl();
    }

    @Override
    public String update(Content content) {
        String path = getPath(content);
        BlobClient client = containerClient.getBlobClient(path);
        client.upload(content.getInputStream(), true);
        return path;
    }

    @Override
    public byte[] read(Content content) {
        String path = getPath(content);
        BlobClient client = containerClient.getBlobClient(path);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        client.download(outputStream);
        final byte[] bytes = outputStream.toByteArray();
        return bytes;
    }

    @Override
    public List<String> listFiles(Content content) {
        PagedIterable<BlobItem> blobList = containerClient.listBlobsByHierarchy(content.getBlobPath() + "/");
        List<String> blobNamesList = new ArrayList<>();
        for (BlobItem blob : blobList) {
            blobNamesList.add(blob.getName());

        }
        return blobNamesList;
    }

    @Override
    public void delete(Content content) {
        String path = getPath(content);
        BlobClient client = containerClient.getBlobClient(path);
        client.delete();
        log.info("Successfully deleted file: {}", path);
    }

    @Override
    public void createContainer() {
        blobServiceClient.createBlobContainer(containerName);
        log.info("Container Created");
    }

    @Override
    public void deleteContainer() {
        blobServiceClient.deleteBlobContainer(containerName);
        log.info("Container Deleted");
    }

    private String getPath(Content content) {
        if (StringUtils.isNoneBlank(content.getBlobPath())
                && StringUtils.isNoneBlank(content.getFileName())) {
            return content.getBlobPath() + "/" + content.getFileName();
        }
        return null;
    }
}