package com.example.fileloader.interfaces;

import com.amazonaws.services.s3.model.Bucket;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BucketInterface {
    void createBucket(String value);
    List<Bucket> getBuckets();
    void deleteBucket(String value);
}
