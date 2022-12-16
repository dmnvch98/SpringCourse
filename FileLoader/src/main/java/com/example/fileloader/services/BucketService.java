package com.example.fileloader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.example.fileloader.interfaces.BucketInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BucketService implements BucketInterface {

    private final AmazonS3 amazonS3;

    @Override
    public void createBucket(String value) {
        amazonS3.createBucket(value);
    }

    @Override
    public List<Bucket> getBuckets() {
        return amazonS3.listBuckets();
    }

    @Override
    public void deleteBucket(String value) {
        amazonS3.deleteBucket(value);
    }
}
