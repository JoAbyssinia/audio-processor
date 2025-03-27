package org.example.audioprocesserservice.aws;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Yohannes k Yimam
 */
@Service
public class S3clientService {


    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3clientService(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    public void uploadFile(String fileName) {

    }

    public void downloadFile(String fileName) {

    }

    public List<String> listFiles() {
        ListObjectsV2Request request = ListObjectsV2Request.builder().bucket(bucketName).build();
        return s3Client.listObjectsV2(request).contents().stream().map(S3Object::key).collect(Collectors.toList());
    }

}
