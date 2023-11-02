package com.example.studypractice.service.coupon;

import com.example.studypractice.domain.User;
import com.example.studypractice.repository.coupon.CouponRepository;
import com.example.studypractice.repository.user.UserRepository;
import com.example.studypractice.service.coupon.ApplyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class ApplyServiceTest {

    @Autowired
    private ApplyService applyService;


    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void 한번(){

        User user1 = User.builder().strId("test1").username("test1").build();
        User user2 = User.builder().strId("test2").username("test2").build();

        userRepository.save(user1);
        userRepository.save(user2);

        applyService.apply(user1);
        applyService.apply(user2);

        long count = couponRepository.count();

        assertThat(count).isEqualTo(2);

    }

    @Test
    public void 동시에요청() throws InterruptedException {

        int threadCount = 1000;

        ExecutorService executorService = Executors.newFixedThreadPool(32);

        CountDownLatch latch = new CountDownLatch(threadCount);

        for(int i=0; i< threadCount; i++){

            User saveUser = userRepository.save(User.builder().username("test" + i).strId("test" + i).build());
            executorService.submit(() -> {

                try {
                    applyService.apply(saveUser);

                } finally {
                    latch.countDown();
                }
            });
        }

        latch.await();

        Thread.sleep(10000);

        long count = couponRepository.;

        assertThat(count).isEqualTo(100);
    }
}