package com.example.fileloader.interfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface FileService {
    void uploadFile(String fileName, InputStream inputStream, String bucketName) throws IOException;
    List<String> getFilesList(String bucketName);
    int getFile(String bucketName, String fileName);
    void deleteFile(String bucketName, String fileName);
}
