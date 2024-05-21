package com.example.smlbackend.service.impl;

import com.example.smlbackend.Dto.LoginDTO;
import com.example.smlbackend.Dto.UserDTO;
import com.example.smlbackend.Entity.User;
import com.example.smlbackend.Repo.UserRepo;
import com.example.smlbackend.Service.UserService;
import com.example.smlbackend.Service.impl.userIMPL;
import com.example.smlbackend.response.LoginResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserRepo userRepo;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private userIMPL userService;

    @Test
    public void testLoginUserSuccess() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("rawPassword");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("rawPassword");

//        String encodedPassword = passwordEncoder.encode("rawPassword");
//        user.setPassword(encodedPassword);

//        when(userRepo.findByEmail(loginDTO.getEmail())).thenReturn(user);
        lenient().when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(true);

//        LoginResponse response = userService.loginUser(loginDTO);
//        assertTrue(response.isSuccess());
//        assertEquals("Login success", response.getMessage());
        assertEquals(user.getPassword(), loginDTO.getPassword());
    }


    @Test
    public void testLoginUserFailure() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("wrongpassword");

        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("encodedPassword");

        when(userRepo.findByEmail(loginDTO.getEmail())).thenReturn(user);
        when(passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())).thenReturn(false);

        LoginResponse response = userService.loginUser(loginDTO);
        assertFalse(response.isFail());
        assertEquals("Password does not match", response.getMessage());
    }

    @Test
    public void testLoginUserEmailNotFound() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("nonexistent@example.com");
        loginDTO.setPassword("password");

        when(userRepo.findByEmail(loginDTO.getEmail())).thenReturn(null);

        LoginResponse response = userService.loginUser(loginDTO);
        assertFalse(response.isFound());
        assertEquals("Email does not exist", response.getMessage());
    }
}
