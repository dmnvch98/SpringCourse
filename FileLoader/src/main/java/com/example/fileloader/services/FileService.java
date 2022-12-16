package com.example.fileloader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.fileloader.interfaces.FileInterface;
import com.example.fileloader.utils.FilesUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

@RequiredArgsConstructor
@Service
public class FileService implements FileInterface {
    private final AmazonS3 amazonS3;

    @Override
    public void uploadFile(MultipartFile file, String bucketName) throws IOException {
        amazonS3.putObject(
                bucketName,
                file.getOriginalFilename(),
                file.getInputStream(),
                FilesUtils.getObjectMetaData(file));
    }

    @Override
    public List<String> getFilesList(String bucketName) {
        return amazonS3.listObjects(bucketName)
                .getObjectSummaries()
                .stream()
                .map(S3ObjectSummary::getKey)
                .toList();
    }

    @Override
    public ResponseEntity<?> getFile(String bucketName, String fileName) {
        try {
            S3Object obj = amazonS3.getObject(new GetObjectRequest(bucketName, fileName));
            try (InputStream stream = obj.getObjectContent()) {
                File file = new File(fileName);
                if (file.createNewFile()) {
                    copyInputStreamToFile(stream, file);
                    return ResponseEntity.created(file.toURI()).build();
                } else {
                    return ResponseEntity.status(204).body("File already exists on your computer");
                }
            } catch (IOException exception) {
                return ResponseEntity.internalServerError().body(exception.getMessage());
            }
        } catch (AmazonS3Exception exception) {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void deleteFile(String bucketName, String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
    }

}
