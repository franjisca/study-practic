package com.example.consumer.consumerpac;


import com.example.consumer.domain.Coupon;
import com.example.consumer.domain.FailEvent;
import com.example.consumer.domain.User;
import com.example.consumer.repository.coupon.CouponRepository;
import com.example.consumer.repository.coupon.FailCouponRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CouponConsumer {


    private final CouponRepository couponRepository;

    private final FailCouponRepository failCouponRepository;


    private final Logger logger = LoggerFactory.getLogger(CouponConsumer.class);

    @KafkaListener(topics = "produce_coupon", groupId = "grp1")
    public void listener(User user) {


        try {
        couponRepository.save(new Coupon(user));
        }catch (Exception e) {
            logger.error("failed to create coupon:: " + user.getId());
            failCouponRepository.save(FailEvent.builder().userId(user.getId()).build());
        }

    }
}
