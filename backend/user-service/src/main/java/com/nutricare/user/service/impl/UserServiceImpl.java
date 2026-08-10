package com.nutricare.user.service.impl;

import com.nutricare.user.dto.LoginRequestDTO;
import com.nutricare.user.dto.RegisterRequestDTO;
import com.nutricare.user.dto.UserInfoResponseDTO;
import com.nutricare.user.model.*;
import com.nutricare.user.repository.AdminRepository;
import com.nutricare.user.repository.UserRepository;
import com.nutricare.user.service.UserService;
import com.nutricare.user.utils.SecurityConfig;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserInfoResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmailOrUsername());
        Admin admin = adminRepository.findByUsername(loginRequestDTO.getEmailOrUsername());
        if (user == null && admin == null) {
            throw new RuntimeException("Tài khoản hoặc mật khâu không chính xác");
        }

        String hashPassword = (user != null) ? user.getHashPassword() : admin.getHashPassword();
        Boolean isPasswordMatch = validatePassword(loginRequestDTO.getPassword(), hashPassword);
        if (!isPasswordMatch) {
            throw new RuntimeException("Tài khoản hoặc mật khẩu không chính xác");
        }

        UserInfoResponseDTO userInfoResponseDTO = null;
        if (user != null) {
            userInfoResponseDTO = new UserInfoResponseDTO(user.getFullName(), user.getAccount().getRole());
        } else {
            userInfoResponseDTO = new UserInfoResponseDTO(null, admin.getAccount().getRole());
        }

        return userInfoResponseDTO;
    }

    private Boolean validatePassword(String rawPassword, String hashPassword) {
        return securityConfig.passwordEncoder().matches(rawPassword, hashPassword);
    }

    @Transactional
    @Override
    public void register(RegisterRequestDTO registerRequestDTO) {
        User existsUser = userRepository.findByEmail(registerRequestDTO.getEmail());
        if (existsUser != null) {
            throw new RuntimeException("Email này đã tồn tại");
        }

        Account account = new Account(null, Role.USER, false);
        User user = new User(null,
                registerRequestDTO.getEmail(),
                registerRequestDTO.getFullName(),
                hashingPassword(registerRequestDTO.getPassword()),
                registerRequestDTO.getGender(),
                UserType.FREE);
        account.setUser(user);
        user.setAccount(account);

        userRepository.save(user);
    }

    private String hashingPassword(String rawPassword) {
        return securityConfig.passwordEncoder().encode(rawPassword);
    }
}
