package com.example.consumer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "app")
public class Config {
    private Kinesis kinesis;
    private DynamoDb dynamoDb;
    private Cloudwatch cloudwatch;

    public Kinesis getKinesis() {
        return kinesis;
    }

    public void setKinesis(Kinesis kinesis) {
        this.kinesis = kinesis;
    }

    public DynamoDb getDynamoDb() {
        return dynamoDb;
    }

    public void setDynamoDb(DynamoDb dynamoDb) {
        this.dynamoDb = dynamoDb;
    }

    public Cloudwatch getCloudwatch() {
        return cloudwatch;
    }

    public void setCloudwatch(Cloudwatch cloudwatch) {
        this.cloudwatch = cloudwatch;
    }
}
