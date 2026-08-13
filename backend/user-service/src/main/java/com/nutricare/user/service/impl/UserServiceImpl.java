package com.nutricare.user.service.impl;

import com.nutricare.user.dto.*;
import com.nutricare.user.model.*;
import com.nutricare.user.repository.AdminRepository;
import com.nutricare.user.repository.HealthConditionRepository;
import com.nutricare.user.repository.UserRepository;
import com.nutricare.user.service.UserService;
import com.nutricare.user.utils.SecurityConfig;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private HealthConditionRepository healthConditionRepository;
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
            userInfoResponseDTO = new UserInfoResponseDTO(user.getUserId(), user.getFullName(), user.getAccount().getRole());
        } else {
            userInfoResponseDTO = new UserInfoResponseDTO(user.getUserId(), null, admin.getAccount().getRole());
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

    @Override
    public List<HealthConditionResponseDTO> getHealthConditionList(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not exists");
        }

        return healthConditionRepository.findAllHealthConditionByUserId(userId);
    }

    @Override
    public HealthConditionResponseDTO getHealthCondition(Integer userId, Integer healthConditionId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not exists");
        }

        return healthConditionRepository.findHealthConditionByHealthConditionIdAndUserId(healthConditionId, userId);
    }

    @Transactional
    @Override
    public void addHealthCondition(HealthConditionRequestDTO healthConditionRequestDTO, Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not exists");
        }

        HealthCondition existsHealCondition = healthConditionRepository.findByHealthConditionNameAndUser_UserId(healthConditionRequestDTO.getHealthConditionName(), userId);
        if (existsHealCondition != null) {
            throw new RuntimeException("Loại bệnh / dị ứng / món ăn kiêng này đã tồn tại");
        }

        HealthCondition healthCondition = new HealthCondition(
                null,
                healthConditionRequestDTO.getHealthConditionName(),
                healthConditionRequestDTO.getConditionType(),
                healthConditionRequestDTO.getSeverity(),
                healthConditionRequestDTO.getHealthConditionDescription());
        healthCondition.setUser(user);

        healthConditionRepository.save(healthCondition);
    }

    @Transactional
    @Override
    public void updateHealthCondition(Integer userId, Integer healthConditionId, HealthConditionRequestDTO healthConditionRequestDTO) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not exists");
        }

        HealthCondition existsHealCondition = healthConditionRepository.findByHealthConditionNameAndUser_UserId(healthConditionRequestDTO.getHealthConditionName(), userId);
        if (existsHealCondition != null) {
            throw new RuntimeException("Loại bệnh / dị ứng / món ăn kiêng này đã tồn tại");
        }

        HealthCondition healthCondition = healthConditionRepository.findByHealthConditionIdAndUser_UserId(healthConditionId, userId);
        if (healthCondition == null) {
            throw new RuntimeException("Diều kiện sức khoẻ này không tồn tại");
        }

        if (healthConditionRequestDTO.getHealthConditionName() != null) {
            healthCondition.setHealthConditionName(healthConditionRequestDTO.getHealthConditionName());
        }
        if (healthConditionRequestDTO.getConditionType() != null) {
            healthCondition.setConditionType(healthConditionRequestDTO.getConditionType());
        }
        if (healthConditionRequestDTO.getSeverity() != null) {
            healthCondition.setSeverity(healthConditionRequestDTO.getSeverity());
        }
        healthCondition.setHealthConditionDescription(healthConditionRequestDTO.getHealthConditionDescription());

        healthConditionRepository.save(healthCondition);
    }

    @Transactional
    @Override
    public void deleteHealthCondition(Integer userId, Integer healthConditionId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new RuntimeException("User not exists");
        }

        HealthCondition healthCondition = healthConditionRepository.findByHealthConditionIdAndUser_UserId(healthConditionId, userId);
        if (healthCondition == null) {
            throw new RuntimeException("Diều kiện sức khoẻ này không tồn tại");
        }

        healthConditionRepository.delete(healthCondition);
    }
}
