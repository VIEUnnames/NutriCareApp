package com.nutricare.user.service;

import com.nutricare.user.dto.LoginRequestDTO;
import com.nutricare.user.dto.RegisterRequestDTO;
import com.nutricare.user.dto.UserInfoResponseDTO;
import jakarta.validation.Valid;

public interface UserService {
    UserInfoResponseDTO login(LoginRequestDTO loginRequestDTO);

    void register(RegisterRequestDTO registerRequestDTO);
}
