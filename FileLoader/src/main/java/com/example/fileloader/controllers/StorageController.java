package com.example.fileloader.controllers;

import com.amazonaws.services.s3.model.Bucket;
import com.example.fileloader.dto.BucketDto;
import com.example.fileloader.interfaces.FileService;
import com.example.fileloader.interfaces.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/api/v1/storages")
@RestController
@RequiredArgsConstructor
public class StorageController {
    private final StorageService storageService;

    private final FileService fileService;

    @ResponseStatus(CREATED)
    @PostMapping(value = "/{storageName}/files/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void uploadFile(@RequestPart("file") final MultipartFile file,
                                        @PathVariable final String storageName) throws IOException {
        fileService.uploadFile(file.getOriginalFilename(), file.getInputStream(), storageName);
    }

    @GetMapping("/{storageName}/files/")
    public ResponseEntity<List<String>> getFilesList(@PathVariable final String storageName) {
        return ResponseEntity.ok(fileService.getFilesList(storageName));
    }

    @GetMapping("/{storageName}/files/{fileName}/")
    public ResponseEntity<?> getFile(@PathVariable final String storageName, @PathVariable final String fileName) {
        return ResponseEntity.status(fileService.getFile(storageName, fileName)).build();
    }

    @DeleteMapping("/{storageName}/files/{fileName}/")
    public ResponseEntity<?> deleteFile(@PathVariable final String storageName, @PathVariable final String fileName) {
        fileService.deleteFile(storageName, fileName);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createStorage(@RequestBody final BucketDto bucket) {
        storageService.createStorage(bucket.getBucketName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<Bucket>> getStorages() {
        return ResponseEntity.ok(storageService.getStorage());
    }

    @DeleteMapping("/{storageName}")
    public ResponseEntity<?> deleteStorage(@PathVariable final String storageName) {
        storageService.deleteStorage(storageName);
        return ResponseEntity.ok().build();
    }
}
