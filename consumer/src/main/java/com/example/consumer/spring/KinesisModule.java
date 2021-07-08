package com.example.consumer.spring;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder.EndpointConfiguration;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsync;
import com.amazonaws.services.cloudwatch.AmazonCloudWatchAsyncClient;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsync;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBAsyncClient;
import com.amazonaws.services.kinesis.AmazonKinesisAsync;
import com.amazonaws.services.kinesis.AmazonKinesisAsyncClient;
import com.example.consumer.config.Config;
import com.example.consumer.config.qualifiers.Cloudwatch;
import com.example.consumer.config.qualifiers.DynamoDB;
import com.example.consumer.config.qualifiers.Kinesis;
import com.example.consumer.service.KinesisConsumerService;
import org.springframework.cloud.aws.core.region.RegionProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class KinesisModule {

    @Bean
    public Consumer<String> consume(KinesisConsumerService kinesisConsumerService) {
        return kinesisConsumerService::handler;
    }

    @Bean
    AWSCredentialsProvider awsCredentialsProvider(Config config) {
        return new AWSStaticCredentialsProvider(
                new BasicAWSCredentials(
                        config.getKinesis().getAccessKey(), config.getKinesis().getSecretKey()));
    }

    @Bean
    @Kinesis
    EndpointConfiguration kinesisEndpointConfiguration(
            Config config, RegionProvider regionProvider) {
        return new EndpointConfiguration(
                config.getKinesis().getKinesisUri(), regionProvider.getRegion().getName());
    }

    @Bean
    @DynamoDB
    EndpointConfiguration dynamoDbEndpointConfiguration(
            Config config, RegionProvider regionProvider) {
        return new EndpointConfiguration(
                config.getDynamoDb().getUri(), regionProvider.getRegion().getName());
    }

    @Bean
    @Cloudwatch
    EndpointConfiguration cloudwatchEndpointConfiguration(
            Config config, RegionProvider regionProvider) {
        return new EndpointConfiguration(
                config.getCloudwatch().getUri(), regionProvider.getRegion().getName());
    }

    @Bean
    AmazonKinesisAsync amazonKinesis(
            AWSCredentialsProvider awsCredentialsProvider,
            @Kinesis EndpointConfiguration endpointConfiguration) {
        return AmazonKinesisAsyncClient.asyncBuilder()
                .withClientConfiguration(
                        new ClientConfiguration().withMaxErrorRetry(0).withConnectionTimeout(1000))
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(awsCredentialsProvider)
                .build();
    }

    @Bean
    AmazonDynamoDBAsync dynamoDB(
            AWSCredentialsProvider awsCredentialsProvider,
            @DynamoDB EndpointConfiguration endpointConfiguration) {
        return AmazonDynamoDBAsyncClient.asyncBuilder()
                .withEndpointConfiguration(endpointConfiguration)
                .withCredentials(awsCredentialsProvider)
                .build();
    }

    @Bean
    AmazonCloudWatchAsync cloudwatch(
            AWSCredentialsProvider awsCredentialsProvider,
            @Cloudwatch EndpointConfiguration endpointConfiguration) {
        return AmazonCloudWatchAsyncClient.asyncBuilder()
                .withCredentials(awsCredentialsProvider)
                .withEndpointConfiguration(endpointConfiguration)
                .build();
    }
}
