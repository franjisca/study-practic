package com.example.studypractice.service.user;


import com.example.studypractice.domain.User;
import com.example.studypractice.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;



    public void save(User user) {

        userRepository.save(user);

    }

}
