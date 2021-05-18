package com.fsm.facedetectionservice.service;

import com.fsm.facedetectionservice.configuration.MinioConfig;
import io.minio.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Optional;

import static com.fsm.facedetectionservice.constants.StorageConstants.MY_IMAGES;
import static com.fsm.facedetectionservice.constants.StorageConstants.MINIO;

@Service
@Qualifier(MINIO)
public class MinioStorageService {

    private final MinioConfig config;
    private MinioClient minioClient;
    private String rootBucketName;

    @Autowired
    public MinioStorageService(MinioConfig config) {
        this.config = config;
        init();
    }

    public void init() {
        minioClient = getMinioClient();

        rootBucketName =
                bucketExists(MY_IMAGES) ?
                        MY_IMAGES : createRootBucket();
    }

    private String createRootBucket() {
        return makeBucket(MakeBucketArgs.builder()
                .bucket(MY_IMAGES)
                .build()
        );
    }

    private MinioClient getMinioClient() {

        System.out.println("MINIO_ACCESS_NAME: " + config.getMinioAccessName());
        System.out.println("MINIO_ACCESS_SECRET: " + config.getMinioAccessSecret());
        System.out.println("MINIO_URL: " + config.getMinioURL());

        return MinioClient.builder()
                .endpoint(config.getMinioURL())
                .credentials(
                        config.getMinioAccessName(),
                        config.getMinioAccessSecret()
                ).build();
    }

    public boolean bucketExists(String bucketName) {
        try {
            return minioClient.bucketExists(BucketExistsArgs
                    .builder()
                    .bucket(bucketName)
                    .build());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public String makeBucket(MakeBucketArgs args) {
        try {
            minioClient.makeBucket(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return args.bucket();
    }

    public void save(MultipartFile file, String objectName) {
        try {
            final byte[] bytes = file.getInputStream().readAllBytes();
            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(rootBucketName)
                            .object(objectName)
                            .stream(new ByteArrayInputStream(bytes), bytes.length, -1)
                            .contentType("application/zip")
                            .build()
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<InputStream> load(String bucketName, String fileName) {
        Optional<InputStream> inputStream = Optional.empty();
        try (final GetObjectResponse response =
                     minioClient.getObject(GetObjectArgs.builder()
                             .bucket(bucketName)
                             .object(fileName)
                             .build())
        ) {
            return Optional.of(
                    new ByteArrayInputStream(response.readAllBytes())
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
