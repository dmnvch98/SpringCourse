package com.example.fileloader.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileInterface {
    void uploadFile(MultipartFile file, String bucketName) throws IOException;
    List<String> getFilesList(String bucketName);
    ResponseEntity<?> getFile(String bucketName, String fileName);
    void deleteFile(String bucketName, String fileName);
}
