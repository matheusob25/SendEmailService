package com.mob.sendemailservice.infra.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsSesConfig {
    @Value("${aws.ses.accessKeyId}")
    private String awsAccessKeyId;
    @Value("${aws.ses.secretAccessKey}")
    private String awsSecretAccessKeyId;
    @Value("${aws.ses.region}")
    private String awsRegion;

    private AWSCredentials awsCredentials(){
        return  new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKeyId);
    }
    //configurando construtor para o autowired encaixar na injeção de dependência
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials())).withRegion(awsRegion).build();
    }

}
