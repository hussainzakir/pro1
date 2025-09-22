package com.repsrv.csweb.core.service.aws;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

@Slf4j
@Service
public class S3Service {

    @Value("${s3bucket.name}")
    private String bucket;

    @Value("${aws.region}")
    private String awsRegion;

    private AmazonS3 s3Client;

    private static final String PATH_FORMAT = "%s/%s";

    public String writeBytesToS3(byte[] bytes, String folderInBucket, String fileName){
        InputStream is = new ByteArrayInputStream(bytes);
        String path = String.format(PATH_FORMAT, folderInBucket, fileName);

        ObjectMetadata ometadata = new ObjectMetadata();
        ometadata.setContentLength(bytes.length);
        getS3Client().putObject(bucket, path, is, ometadata);

        log.debug("Error file successfully stored in S3: {}", path);
        return path;
    }

    public byte[] getS3File(String s3Key){
        log.debug("Fetching S3 file: {} from bucket:{}", s3Key, bucket);
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, s3Key);

            S3Object s3Obj = getS3Client().getObject(getObjectRequest);
            S3ObjectInputStream inputStream = s3Obj.getObjectContent();

            byte[] byteArray = IOUtils.toByteArray(inputStream);
            log.info("Error file sie in bytes={}", byteArray.length);

            return byteArray;
        } catch (AmazonServiceException e) {
            log.error("Error downloading from S3", e);
            throw e;
        } catch (IOException e) {
            log.error("Error downloading from S3", e);
            throw new RuntimeException(e);
        }

    }

    private AmazonS3 getS3Client(){
        if(s3Client == null)
            s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.fromName(awsRegion)).build();

        return s3Client;
    }
}
