package com.example.consumer.config;

public class Kinesis {
    private String accessKey;
    private String secretKey;

    public String getSigningRegion() {
        return signingRegion;
    }

    public void setSigningRegion(String signingRegion) {
        this.signingRegion = signingRegion;
    }

    private String kinesisUri;
    private String signingRegion;

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
