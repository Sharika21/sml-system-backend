package com.example.smlbackend.Service.impl;

import com.example.smlbackend.Dto.LoginDTO;
import com.example.smlbackend.Dto.UserDTO;
import com.example.smlbackend.Entity.User;
import com.example.smlbackend.Repo.UserRepo;
import com.example.smlbackend.Service.UserService;
import com.example.smlbackend.response.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class userIMPL implements UserService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    public userIMPL(UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public String addUser(UserDTO userDTO) {

        User user = new User(
            userDTO.getUserid(),
            userDTO.getEmail(),
            userDTO.getUsername(),
            this.passwordEncoder.encode(userDTO.getPassword())

        );

        userRepo.save(user);

        return user.getUsername();

    }

//    @Override
//    public LoginResponse loginUser(LoginDTO loginDTO) {
//        return null;
//    }
}
