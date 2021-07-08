package com.example.producer.spring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.kinesis.common.KinesisClientUtil;

import java.net.URI;

@Configuration
public class KinesisModule {

    private final Config config;

    public KinesisModule(Config config) {
        this.config = config;
    }

    @Bean
    public KinesisAsyncClient kinesisClient() {
        return KinesisClientUtil.createKinesisAsyncClient(
                KinesisAsyncClient.builder()
                        .endpointOverride(URI.create(config.getKinesisUri()))
                        .credentialsProvider(
                                StaticCredentialsProvider.create(
                                        AwsBasicCredentials.create(
                                                config.getAccessKey(),
                                                config.getSecretAccessKey())))
                        .region(Region.US_EAST_1));
    }
}
