package com.example.consumer.config;

public class Kinesis {
    private String accessKey;
    private String secretKey;
    private String kinesisUri;

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getKinesisUri() {
        return kinesisUri;
    }

    public void setKinesisUri(String kinesisUri) {
        this.kinesisUri = kinesisUri;
    }
}
