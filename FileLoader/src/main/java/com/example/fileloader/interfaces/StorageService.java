package com.example.fileloader.interfaces;

import com.amazonaws.services.s3.model.Bucket;

import java.util.List;

public interface StorageService {
    void createStorage(String value);
    List<Bucket> getStorage();
    void deleteStorage(String value);
}
