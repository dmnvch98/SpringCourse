package com.example.fileloader.config;

import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    
    String upload(MultipartFile multipartFile);

}