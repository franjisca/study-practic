package com.example.studypractice.producer;


import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponProducer {


    private final KafkaTemplate<String, Long> kafkaTemplate;

    public void createTemplate(Long userId) {
        kafkaTemplate.send("produce_coupon", userId);
    }
}
