package com.example.studypractice.domain;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Coupon {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private Long userId;


    @Builder
    public Coupon(User user) {
        this.userId = user.getId();
    }
}
