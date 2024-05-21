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

import java.util.Optional;

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
                userDTO.getUsername(),
                userDTO.getEmail(),
                this.passwordEncoder.encode(userDTO.getPassword())
        );

        userRepo.save(user);

        return user.getEmail();

    }

    @Override
    public LoginResponse loginUser(LoginDTO loginDTO) {
        String msg = "";

        String email = loginDTO.getEmail();
        System.out.println("Email to find: " + email);

        User user1 = userRepo.findByEmail(loginDTO.getEmail());
        if (user1 != null) {
            String password = loginDTO.getPassword();
            String encodedPassword = user1.getPassword();

            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
            System.out.println("Password matches: " + isPwdRight);

            if (isPwdRight) {
                Optional<User> user = userRepo.findOneByEmailAndPassword(loginDTO.getEmail(), encodedPassword);
                    if (user.isPresent()) {
                        return new LoginResponse("Login success", true);
                    } else {
                        return new LoginResponse("Login failed", false);
                    }
            } else {
                return new LoginResponse("Password does not match", false);
            }
        } else {
            return new LoginResponse("Email does not exist", false);
        }
    }
}
