package com.example.producer.spring;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app")
public class Config {
    private String kinesisUri;
    private String accessKey;
    private String secretAccessKey;
    private String streamName;

    public String getKinesisUri() {
        return kinesisUri;
    }

    public void setKinesisUri(String kinesisUri) {
        this.kinesisUri = kinesisUri;
    }

    public String getStreamName() {
        return streamName;
    }

    public void setStreamName(String streamName) {
        this.streamName = streamName;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretAccessKey() {
        return secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }
}
