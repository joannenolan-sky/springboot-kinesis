package com.example.consumer.service;

import com.example.consumer.model.kinesis.data.DataRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class KinesisConsumerService {

    private static final Logger logger = LoggerFactory.getLogger(KinesisConsumerService.class);

    private final ObjectMapper objectMapper;

    public KinesisConsumerService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public void handler(String message) {
        logger.info("Consumed json: {}", message);
        try {
            DataRecord dataRecord = objectMapper.readValue(message, DataRecord.class);
            logger.info("Mapped message: {}", dataRecord);
        } catch (Exception e) {
            logger.error("Error occurred while ingesting message:", e);
        }
    }
}
