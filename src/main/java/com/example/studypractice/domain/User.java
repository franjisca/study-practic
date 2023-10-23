package com.example.studypractice.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name="users")
@NoArgsConstructor(access = PROTECTED)
@Getter
public class User {

    @Id@GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String username;

    private String strId;

    @Builder
    public User(String username, String strId) {
        this.username = username;
        this.strId = strId;
    }
}
