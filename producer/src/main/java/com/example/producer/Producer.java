package com.example.producer;

import com.example.producer.spring.Config;
import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kinesis.KinesisAsyncClient;
import software.amazon.awssdk.services.kinesis.model.KinesisException;
import software.amazon.awssdk.services.kinesis.model.ProvisionedThroughputExceededException;
import software.amazon.awssdk.services.kinesis.model.PutRecordRequest;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

@Component
public class Producer {
    private final KinesisAsyncClient kinesisClient;
    private final Config config;
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    public Producer(KinesisAsyncClient kinesisClient, Config config) {
        this.kinesisClient = kinesisClient;
        this.config = config;
    }

    public void produce() {
        for (int i = 0; i < 10; i++) {
            publishRecord(recordExample(i));
        }
    }

    public void publishRecord(String data) {
        PutRecordRequest request =
                PutRecordRequest.builder()
                        .partitionKey(RandomStringUtils.randomAlphabetic(5, 20))
                        .streamName(config.getStreamName())
                        .data(SdkBytes.fromString(data, StandardCharsets.UTF_8))
                        .build();
        try {
            kinesisClient.putRecord(request).get();
        } catch (InterruptedException e) {
            log.info("Interrupted, assuming shutdown.");
        } catch (ExecutionException e) {
            log.error("Exception while sending data to Kinesis. Will try again next cycle.", e);
        } catch (ProvisionedThroughputExceededException e ){ //KinesisException
            log.error("provisionedThroughputExceededException ", e);
        }catch (KinesisException e){
            log.error("KinesisException ", e);
        }

    }

    private String recordExample(int i) {
        return "{"
                + "\"header\":"
                + "{"
                + "\"type\":\"blah\","
                + "\"sourceId\":\"abc123456\","
                + "\"task\":\"upsert\""
                + "},"
                + "\"data\":"
                + "{"
                + "\"uuid\":\"8839e902-5fca-4648-84a9-e58e014f8040\","
                + "\"field1\":\"" + i + "\","
                + "\"field2\":\"aaaa\","
                + "\"field3\":\"bbbb\","
                + "\"fieldList\":[\"a\",\"b\",\"c\"]"
                + "}"
                + "}";
    }
}
