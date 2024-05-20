package com.example.smlbackend.Service;

import com.example.smlbackend.Dto.LoginDTO;
import com.example.smlbackend.Dto.UserDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

//@Bea
public interface UserService {
    String addUser(UserDTO userDTO);

//    LoginResponse loginUser(LoginDTO loginDTO);
}
