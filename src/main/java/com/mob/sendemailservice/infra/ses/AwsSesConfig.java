package com.mob.sendemailservice.infra.ses;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // anotação para o spring identificar como uma classe de configuração
public class AwsSesConfig {

    @Value("${aws.ses.accessKeyId}")      // anotação para  o spring pegar o valor da variável em application.properties e injetar na variável abaixo
    private String awsAccessKeyId;
    @Value("${aws.ses.secretAccessKey}")
    private String awsSecretAccessKeyId;


    private AWSCredentials awsCredentials(){
        return  new BasicAWSCredentials(awsAccessKeyId, awsSecretAccessKeyId); // get das credenciais
    }

    //anotação para que o autowired identifique o construtor da interface AmazonSimpleEmailService  e injete a dependência de alguma classe que implementa ela
    @Bean
    public AmazonSimpleEmailService amazonSimpleEmailService() {
        // a AmazonSimpleEmailServiceClientBuilder é uma classe que implementa a interface do construtor logo ela servirá
        return AmazonSimpleEmailServiceClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials())).withRegion(Regions.US_EAST_1).build();

    }

}
