package com.nutricare.user.controller;

import com.nutricare.user.dto.ErrorResponseDTO;
import com.nutricare.user.dto.LoginRequestDTO;
import com.nutricare.user.dto.RegisterRequestDTO;
import com.nutricare.user.dto.UserInfoResponseDTO;
import com.nutricare.user.model.User;
import com.nutricare.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@ResponseBody
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequestDTO loginRequestDTO,
                                   BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        UserInfoResponseDTO userInfoResponseDTO = null;
        try {
            userInfoResponseDTO = userService.login(loginRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.ok(userInfoResponseDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO,
                                      BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", result.getFieldError().getDefaultMessage()));
        }

        try {
            userService.register(registerRequestDTO);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("400", e.getMessage()));
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
