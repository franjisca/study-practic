package com.example.studypractice.service.coupon;


import com.example.studypractice.domain.Coupon;
import com.example.studypractice.domain.User;
import com.example.studypractice.producer.CouponProducer;
import com.example.studypractice.repository.coupon.CouponCountRepository;
import com.example.studypractice.repository.coupon.CouponRepository;
import com.example.studypractice.repository.coupon.SettledUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ApplyService {

    private final CouponRepository couponRepository;

    private final CouponCountRepository couponCountRepository;

    private final CouponProducer couponProducer;

    private final SettledUserRepository settledUserRepository;


    public void apply(User user) {

        Long applyId = settledUserRepository.add(user.getId());

        if(applyId !=1) {
            throw  new IllegalArgumentException("이미 쿠폰이 발급되었습니다");
        }

        // 쿠폰 개수에 관한 정합성 보장
        long count = couponCountRepository.increment();

        // Set을 이용하여 1인 1개

        if(count > 100) {
            throw new IllegalArgumentException("쿠폰이 소진되었습니다.");
        }

        // couponRepository.save(Coupon.builder().user(user).build());

        couponProducer.createTemplate(user.getId());

    }


}
