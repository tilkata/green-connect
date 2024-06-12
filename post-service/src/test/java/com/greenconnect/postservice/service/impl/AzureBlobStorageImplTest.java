package com.greenconnect.postservice.service.impl;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.models.BlobItem;
import com.greenconnect.postservice.model.Content;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Value;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import com.azure.core.http.rest.PagedIterable;
import java.util.List;

public class AzureBlobStorageImplTest {

    @Mock
    private BlobServiceClient blobServiceClient;

    @Mock
    private BlobContainerClient containerClient;

    @Mock
    private BlobClient blobClient;

    @InjectMocks
    private AzureBlobStorageImpl azureBlobStorage;

    @Value("${azure.storage.container.name}")
    private String containerName = "test-container";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Debugging statements to trace the initialization
        System.out.println("blobServiceClient: " + blobServiceClient);
        System.out.println("containerName: " + containerName);

        // Mock BlobContainerClient if it's not properly initialized
        if (containerClient == null) {
            containerClient = mock(BlobContainerClient.class);
        }

        when(blobServiceClient.getBlobContainerClient(containerName)).thenReturn(containerClient);

        // Debugging statement
        System.out.println("containerClient after mocking: " + containerClient);

        when(containerClient.getBlobClient(anyString())).thenReturn(blobClient);
        assertNotNull(containerClient, "Container client should not be null");
    }


//    @Test
//    public void testWrite() throws IOException {
//        // Arrange
//        Content content = mock(Content.class);
//        when(content.getFileName()).thenReturn("test.txt");
//        when(content.getInputStream()).thenReturn(new ByteArrayInputStream("test data".getBytes()));
//
//        // Act
//        String result = azureBlobStorage.write(content);
//
//        // Assert
//        verify(blobClient, times(1)).upload(any(InputStream.class), anyLong(), eq(false));
//        assertNotNull(result);
//    }

    @Test
    public void testUpdate() throws IOException {
        // Arrange
        Content content = mock(Content.class);
        when(content.getBlobPath()).thenReturn("path");
        when(content.getFileName()).thenReturn("test.txt");
        when(content.getInputStream()).thenReturn(new ByteArrayInputStream("test data".getBytes()));

        // Act
        String result = azureBlobStorage.update(content);

        // Assert
        verify(blobClient, times(1)).upload(any(InputStream.class), eq(true));
        assertNotNull(result);
    }

//    @Test
//    public void testRead() throws IOException {
//        // Arrange
//        Content content = mock(Content.class);
//        when(content.getBlobPath()).thenReturn("path");
//        when(content.getFileName()).thenReturn("test.txt");
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
//        doAnswer(invocation -> {
//            IOUtils.write("test data", outputStream, "UTF-8");
//            return null;
//        }).when(blobClient).download(any(OutputStream.class));
//
//        // Act
//        byte[] result = azureBlobStorage.read(content);
//
//        // Assert
//        verify(blobClient, times(1)).download(any(OutputStream.class));
//        assertEquals("test data", new String(result));
//    }

    @Test
    public void testListFiles() {
        // Arrange
        Content content = mock(Content.class);
        when(content.getBlobPath()).thenReturn("path");

        BlobItem blobItem = mock(BlobItem.class);
        when(blobItem.getName()).thenReturn("test.txt");

        PagedIterable<BlobItem> pagedIterable = mock(PagedIterable.class);
        List<BlobItem> blobItems = new ArrayList<>();
        blobItems.add(blobItem);
        when(pagedIterable.iterator()).thenReturn(blobItems.iterator());

        when(containerClient.listBlobsByHierarchy(anyString())).thenReturn(pagedIterable);

        // Act
        List<String> result = azureBlobStorage.listFiles(content);

        // Assert
        verify(containerClient, times(1)).listBlobsByHierarchy(anyString());
        assertEquals(1, result.size());
        assertEquals("test.txt", result.get(0));
    }

    @Test
    public void testDelete() {
        // Arrange
        Content content = mock(Content.class);
        when(content.getBlobPath()).thenReturn("path");
        when(content.getFileName()).thenReturn("test.txt");

        // Act
        azureBlobStorage.delete(content);

        // Assert
        verify(blobClient, times(1)).delete();
    }

//    @Test
//    public void testCreateContainer() {
//        // Act
//        azureBlobStorage.createContainer();
//
//        // Assert
//        verify(blobServiceClient, times(1)).createBlobContainer(containerName);
//    }
//
//    @Test
//    public void testDeleteContainer() {
//        // Act
//        azureBlobStorage.deleteContainer();
//
//        // Assert
//        verify(blobServiceClient, times(1)).deleteBlobContainer(containerName);
//    }
}
