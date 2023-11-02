package com.example.studypractice.service.coupon;


import com.example.studypractice.domain.Coupon;
import com.example.studypractice.domain.User;
import com.example.studypractice.producer.CouponProducer;
import com.example.studypractice.repository.coupon.CouponCountRepository;
import com.example.studypractice.repository.coupon.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponProducer couponProducer;


    public void apply(User user) {

        // 쿠폰 개수에 관한 정합성 보장
        long count = couponCountRepository.increment();

        if(count > 100) {
            throw new IllegalArgumentException("쿠폰이 소진되었습니다.");
        }

        // couponRepository.save(Coupon.builder().user(user).build());

        couponProducer.createTemplate(user.getId());

    }


}
