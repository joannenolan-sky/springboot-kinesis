package com.example.producer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KinesisWebController {

    private final Producer producer;

    public KinesisWebController(Producer producer) {
        this.producer = producer;
    }

    @PostMapping("/message")
    public ResponseEntity<String> sendMessage(@RequestBody String message) {
        producer.publishRecord(message);
        return ResponseEntity.ok("OK");
    }

    @PostMapping("/messages")
    public ResponseEntity<String> sendMultipleMessages() {
        producer.produce();
        return ResponseEntity.ok("OK");
    }
}
