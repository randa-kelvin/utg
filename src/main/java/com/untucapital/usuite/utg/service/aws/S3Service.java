package com.untucapital.usuite.utg.service.aws;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

@Service
public class S3Service {

    private AmazonS3 s3client;

    @Value("${aws.accessKeyId}")
    private String accessKeyId;

    @Value("${aws.secretKey}")
    private String secretKey;

    @Value("${aws.region}")
    private String region;

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    @PostConstruct
    private void initializeAmazon() {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKeyId, secretKey);
        this.s3client = AmazonS3ClientBuilder.standard()
                .withRegion(Regions.fromName(region))
                .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
                .build();
    }

    public String uploadPDF(String key, byte[] pdfContent) {
        InputStream is = new ByteArrayInputStream(pdfContent);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(pdfContent.length);
        metadata.setContentType("application/pdf");
        s3client.putObject(bucketName, key, is, metadata);

        // Construct the URL of the uploaded PDF
        return s3client.getUrl(bucketName, key).toString();
    }

}

