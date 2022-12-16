package com.example.fileloader.utils;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.experimental.UtilityClass;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Objects;

@UtilityClass
public class FilesUtils {
    public ObjectMetadata getObjectMetaData(MultipartFile file) throws IOException {
        BasicFileAttributes attr = Files.readAttributes(multiPartFileToFile(file), BasicFileAttributes.class);
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.addUserMetadata("creationTime:", attr.creationTime().toString());
        objectMetadata.addUserMetadata("lastAccessTime:", attr.lastAccessTime().toString());
        objectMetadata.addUserMetadata("lastModifiedTime:", attr.lastModifiedTime().toString());
        objectMetadata.addUserMetadata("size: ", String.valueOf(attr.size()));
        return objectMetadata;
    }

    public Path multiPartFileToFile(MultipartFile multipartFile) {
        File convFile = new File(Objects.requireNonNull(multipartFile.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convFile)) {
            fos.write(multipartFile.getBytes());
            return convFile.toPath();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
