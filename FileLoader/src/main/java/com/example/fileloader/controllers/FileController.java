package com.example.fileloader.controllers;

import com.example.fileloader.interfaces.FileInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RequestMapping("/api/v1/files")
@RestController
@RequiredArgsConstructor
public class FileController {
    private final FileInterface service;

    @PostMapping(value = "/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestPart("file") final MultipartFile file,
                                        @PathVariable final String bucketName) throws IOException {
        service.uploadFile(file, bucketName);
        return ResponseEntity.status(201).body("File uploaded");
    }

    @GetMapping("/{bucketName}/list/")
    public ResponseEntity<?> getFilesList(@PathVariable final String bucketName) {
        return ResponseEntity.ok(service.getFilesList(bucketName));
    }

    @GetMapping("/{bucketName}/{fileName}/")
    public ResponseEntity<?> getFile(@PathVariable final String bucketName, @PathVariable final String fileName) {
        return service.getFile(bucketName, fileName);
    }

    @DeleteMapping("/{bucketName}/{fileName}/")
    public ResponseEntity<?> deleteFile(@PathVariable final String bucketName, @PathVariable final String fileName) {
        service.deleteFile(bucketName, fileName);
        return ResponseEntity.ok().build();
    }

}
