package com.example.fileloader.controllers;

import com.example.fileloader.dto.BucketDto;
import com.example.fileloader.interfaces.BucketInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/buckets")
@RestController
@RequiredArgsConstructor
public class BucketController {
    private final BucketInterface service;

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBucket(@RequestBody final BucketDto bucket) {
        service.createBucket(bucket.getBucketName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<?> getBuckets() {
        return ResponseEntity.ok(service.getBuckets());
    }

    @DeleteMapping("/{bucket}")
    public ResponseEntity<?> deleteBucket(@PathVariable final String bucket) {
        service.deleteBucket(bucket);
        return ResponseEntity.ok().build();
    }
}
