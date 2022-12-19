package com.example.fileloader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.example.fileloader.interfaces.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class StorageServiceImpl implements StorageService {

    private final AmazonS3 amazonS3;

    @Override
    public void createStorage(String value) {
        amazonS3.createBucket(value);
    }

    @Override
    public List<Bucket> getStorage() {
        return amazonS3.listBuckets();
    }

    @Override
    public void deleteStorage(String value) {
        amazonS3.deleteBucket(value);
    }
}
