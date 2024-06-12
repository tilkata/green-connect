package com.greenconnect.postservice.service;

import com.greenconnect.postservice.model.Content;

import java.io.IOException;
import java.util.List;

public interface IAzureBlobStorage {
    public String write(Content content) throws IOException;
    public String update(Content content);
    public byte[] read(Content content);
    public List<String> listFiles(Content content);
    public void delete(Content content);
    public void createContainer();
    public void deleteContainer();
}
