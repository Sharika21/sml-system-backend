package com.example.smlbackend.controller.impl;

import com.example.smlbackend.Dto.LoginDTO;
import com.example.smlbackend.Dto.UserDTO;
import com.example.smlbackend.Service.UserService;
import com.example.smlbackend.UserController.UserController;
import com.example.smlbackend.response.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class UserControllerImplTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("test@example.com");
        userDTO.setPassword("password");
        userDTO.setUsername("testUser");

        when(userService.addUser(any(UserDTO.class))).thenReturn("12345");

        String response = userController.saveUser(userDTO);

        assertEquals("12345", response);
    }

    @Test
    public void testLoginUserSuccess() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("password");

        LoginResponse loginResponse = new LoginResponse("Login success");

        when(userService.loginUser(any(LoginDTO.class))).thenReturn(loginResponse);

        ResponseEntity<?> responseEntity = userController.loginUser(loginDTO);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(loginResponse, responseEntity.getBody());
    }

    @Test
    public void testLoginUserFailure() {
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setEmail("test@example.com");
        loginDTO.setPassword("wrongPassword");

        LoginResponse loginResponse = new LoginResponse("Login failed");

        when(userService.loginUser(any(LoginDTO.class))).thenReturn(loginResponse);

        ResponseEntity<?> responseEntity = userController.loginUser(loginDTO);

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(loginResponse, responseEntity.getBody());
    }
}
