package com.example.consumer.consumerpac;


import com.example.consumer.domain.Coupon;
import com.example.consumer.domain.User;
import com.example.consumer.repository.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponConsumer {


    private final CouponRepository couponRepository;

    @KafkaListener(topics = "produce_coupon", groupId = "grp1")
    public void listener(User user) {

        couponRepository.save(new Coupon(user));

    }
}
