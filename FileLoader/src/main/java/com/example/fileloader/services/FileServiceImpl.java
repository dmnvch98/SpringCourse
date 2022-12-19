package com.example.fileloader.services;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.*;
import com.example.fileloader.interfaces.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService {
    private final AmazonS3 amazonS3;

    public static final int CREATED = 201;
    public static final int NO_CONTENT = 204;
    public static final int NOT_FOUND = 404;
    public static final int INTERNAL_ERROR = 500;

    @Override
    public void uploadFile(String fileName, InputStream inputStream, String bucketName) {
        amazonS3.putObject(
                bucketName,
                fileName,
                inputStream,
                new ObjectMetadata());
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
    public int getFile(String bucketName, String fileName) {
        try {
            S3Object obj = amazonS3.getObject(new GetObjectRequest(bucketName, fileName));
            try (InputStream stream = obj.getObjectContent()) {
                File file = new File(fileName);
                if (file.createNewFile()) {
                    copyInputStreamToFile(stream, file);
                    deleteFile(bucketName, fileName);
                    return CREATED;
                } else {
                    return NO_CONTENT;
                }
            } catch (IOException exception) {
                return INTERNAL_ERROR;
            }
        } catch (AmazonS3Exception exception) {
            return NOT_FOUND;
        }
    }

    @Override
    public void deleteFile(String bucketName, String fileName) {
        amazonS3.deleteObject(bucketName, fileName);
    }

}
