package com.example.studypractice.repository.coupon;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SettledUserRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public Long add(Long userId) {
        return redisTemplate.opsForSet().add("applied_user", userId.toString());
    }
}
