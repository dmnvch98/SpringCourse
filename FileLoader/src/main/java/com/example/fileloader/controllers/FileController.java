package com.example.fileloader.controllers;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.example.fileloader.config.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final AmazonS3 amazonS3;
    private final StorageService storageService;


    @PostMapping(path = "bucket", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> createBucket(@RequestBody final String bucketName) {
        amazonS3.createBucket(bucketName);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("bucket")
    public List<Bucket> getBuckets() {
        return amazonS3.listBuckets();
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadFile(@RequestPart("file") final MultipartFile file) throws IOException {
        amazonS3.putObject("bucket", file.getOriginalFilename(),
                file.getInputStream(), new ObjectMetadata());


        return ResponseEntity.ok(file.getName());
    }
}
