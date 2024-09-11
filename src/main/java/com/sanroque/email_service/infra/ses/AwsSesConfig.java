package com.sanroque.email_service.infra.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class AwsSesConfig {

    @Value("${aws.access-key}")
    protected String accessKey;

    @Value("${aws.secret-key}")
    protected String secretKey;
    private Region newRegion = Region.getRegion(Regions.US_EAST_1);

    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService(){
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withRegion(String.valueOf(newRegion))
                .withCredentials(buildAWSCredentialsProvider())
                .build();
    }

    @Bean
    @Primary
    public AWSCredentialsProvider buildAWSCredentialsProvider(){
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKey, secretKey);
        return new AWSStaticCredentialsProvider(awsCredentials);
    }
}
