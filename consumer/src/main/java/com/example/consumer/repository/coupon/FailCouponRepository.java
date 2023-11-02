package com.example.consumer.repository.coupon;

import com.example.consumer.domain.FailEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FailCouponRepository extends JpaRepository<FailEvent, Long> {


}
