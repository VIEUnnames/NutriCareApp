package com.nutricare.user.service.impl;

import com.nutricare.user.dto.*;
import com.nutricare.user.exception.DuplicateResourceException;
import com.nutricare.user.exception.InvalidCredentialsException;
import com.nutricare.user.exception.ResourceNotFoundException;
import com.nutricare.user.exception.UserNotFoundException;
import com.nutricare.user.model.*;
import com.nutricare.user.repository.*;
import com.nutricare.user.service.UserService;
import com.nutricare.user.utils.SecurityConfig;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import com.nutricare.user.utils.NutritionCalculator;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private HealthConditionRepository healthConditionRepository;
    @Autowired
    private BMIRecordRepository bmiRecordRepository;
    @Autowired
    private NutritionTargetRepository nutritionTargetRepository;
    @Autowired
    private SecurityConfig securityConfig;

    @Override
    public UserInfoResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByEmail(loginRequestDTO.getEmailOrUsername());
        Admin admin = adminRepository.findByUsername(loginRequestDTO.getEmailOrUsername());
        if (user == null && admin == null) {
            throw new InvalidCredentialsException("Tài khoản hoặc mật khâu không chính xác");
        }

        String hashPassword = (user != null) ? user.getHashPassword() : admin.getHashPassword();
        Boolean isPasswordMatch = validatePassword(loginRequestDTO.getPassword(), hashPassword);
        if (!isPasswordMatch) {
            throw new InvalidCredentialsException("Tài khoản hoặc mật khâu không chính xác");
        }

        UserInfoResponseDTO userInfoResponseDTO = null;
        if (user != null) {
            userInfoResponseDTO = new UserInfoResponseDTO(user.getUserId(), user.getFullName(), user.getAccount().getRole());
        } else {
            userInfoResponseDTO = new UserInfoResponseDTO(admin.getAdminId(), null, admin.getAccount().getRole());
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
            throw new DuplicateResourceException("Email này đã tồn tại");
        }

        Account account = new Account(null, Role.USER, false);
        User user = new User(null,
                registerRequestDTO.getEmail(),
                registerRequestDTO.getFullName(),
                hashingPassword(registerRequestDTO.getPassword()),
                registerRequestDTO.getGender(),
                registerRequestDTO.getDateOfBirth(),
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
            throw new UserNotFoundException("User này không tồn tại");
        }

        return healthConditionRepository.findAllHealthConditionByUserId(userId);
    }

    @Override
    public HealthConditionResponseDTO getHealthCondition(Integer userId, Integer healthConditionId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        return healthConditionRepository.findHealthConditionByHealthConditionIdAndUserId(healthConditionId, userId);
    }

    @Transactional
    @Override
    public void addHealthCondition(HealthConditionRequestDTO healthConditionRequestDTO, Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        HealthCondition existsHealCondition = healthConditionRepository.findByHealthConditionNameAndUser_UserId(healthConditionRequestDTO.getHealthConditionName(), userId);
        if (existsHealCondition != null) {
            throw new DuplicateResourceException("Loại bệnh / dị ứng / món ăn kiêng này đã tồn tại");
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
            throw new UserNotFoundException("User này không tồn tại");
        }

        HealthCondition existsHealCondition = healthConditionRepository.findByHealthConditionNameAndUser_UserId(healthConditionRequestDTO.getHealthConditionName(), userId);
        if (existsHealCondition != null) {
            throw new DuplicateResourceException("Loại bệnh / dị ứng / món ăn kiêng này đã tồn tại");
        }

        HealthCondition healthCondition = healthConditionRepository.findByHealthConditionIdAndUser_UserId(healthConditionId, userId);
        if (healthCondition == null) {
            throw new ResourceNotFoundException("Diều kiện sức khoẻ này không tồn tại");
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
            throw new UserNotFoundException("User này không tồn tại");
        }

        HealthCondition healthCondition = healthConditionRepository.findByHealthConditionIdAndUser_UserId(healthConditionId, userId);
        if (healthCondition == null) {
            throw new ResourceNotFoundException("Diều kiện sức khoẻ này không tồn tại");
        }

        healthConditionRepository.delete(healthCondition);
    }

    @Override
    public List<BMIResponseDTO> getBMIRecordList(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        return bmiRecordRepository.findByUserId(userId);
    }

    @Override
    public BMIResponseDTO getBMIRecord(Integer userId, Integer bmiRecordId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        return bmiRecordRepository.findByUserIdAndBmiRecordId(userId, bmiRecordId);
    }

    @Transactional
    @Override
    public void addBmiRecord(Integer userId, BMIRequestDTO bmiRequestDTO) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        BMIRecord bmiRecord = new BMIRecord(null,
                bmiRequestDTO.getWeightCm(),
                bmiRequestDTO.getHeightCm(),
                null);

        bmiRecord.setUser(user);

        bmiRecordRepository.save(bmiRecord);
    }

    @Transactional
    @Override
    public void updateBmiRecord(Integer userId, Integer bmiRecordId, BMIRequestDTO bmiRequestDTO) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        BMIRecord bmiRecord = bmiRecordRepository.findByBmiRecordIdAndUser_UserId(bmiRecordId, userId);
        if (bmiRecord == null) {
            throw new ResourceNotFoundException("Bản ghi BMI này không tồn tại");
        }

        if (bmiRequestDTO.getHeightCm() != null) bmiRecord.setHeightCm(bmiRequestDTO.getHeightCm());
        if (bmiRequestDTO.getWeightCm() != null) bmiRecord.setWeightCm(bmiRequestDTO.getWeightCm());

        bmiRecordRepository.save(bmiRecord);
    }

    @Transactional
    @Override
    public void deleteBmiRecord(Integer userId, Integer bmiRecordId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        BMIRecord bmiRecord = bmiRecordRepository.findByBmiRecordIdAndUser_UserId(bmiRecordId, userId);
        if (bmiRecord == null) {
            throw new ResourceNotFoundException("Bản ghi BMI này không tồn tại");
        }

        bmiRecordRepository.delete(bmiRecord);
    }

    @Override
    public List<NutritionTargetResponseDTO> getNutritionTargetList(Integer userId) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        return nutritionTargetRepository.findByUser_UserId(userId);
    }

    @Transactional
    @Override
    public void addNutritionTarget(Integer userId, NutritionTargetRequestDTO nutritionTargetRequestDTO) {
        User user = userRepository.findByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User này không tồn tại");
        }

        BMIRecord bmiRecord = bmiRecordRepository.findTopByUser_UserIdOrderByRecordAtDesc(userId);
        if (bmiRecord == null) {
            throw new ResourceNotFoundException("Không có bản ghi BMI nào để thực hành tạo mục tiêu");
        }

        NutritionTarget oldNutritionTarget = nutritionTargetRepository.findActiveByUser_UserId(userId);
        if(oldNutritionTarget != null) {
            oldNutritionTarget.setActive(false);
            nutritionTargetRepository.save(oldNutritionTarget);
        }

        Integer userAge = Period.between(LocalDate.now(), user.getDateOfBirth()).getYears();
        BigDecimal bmr = NutritionCalculator.calculateBmr(bmiRecord.getWeightCm(), bmiRecord.getHeightCm(), userAge, user.getGender());
        BigDecimal tdee = NutritionCalculator.calculateTdee(bmr, nutritionTargetRequestDTO.getActivityLevel());
        BigDecimal calorieTarget = NutritionCalculator.calculateCalorieTarget(tdee, nutritionTargetRequestDTO.getGoal(), BigDecimal.valueOf(500));
        BigDecimal protein = NutritionCalculator.calculateProtein(calorieTarget);
        BigDecimal fat = NutritionCalculator.calculateFat(calorieTarget);
        BigDecimal carb = NutritionCalculator.calculateCarbon(calorieTarget, protein, fat);
        BigDecimal fiber = NutritionCalculator.calculateFiber(calorieTarget);
        BigDecimal water = NutritionCalculator.calculateWater(user.getGender());
        BigDecimal sodium = NutritionCalculator.calculateSodium(user.getGender());

        NutritionTarget nutritionTarget = new NutritionTarget(
                null,
                nutritionTargetRequestDTO.getActivityLevel(),
                nutritionTargetRequestDTO.getGoal(),
                bmr,
                tdee,
                calorieTarget,
                protein,
                fat,
                carb,
                fiber,
                water,
                sodium,
                LocalDate.now(),
                LocalDate.now().plusMonths(nutritionTargetRequestDTO.getNumberOfActive()),
                true);

        nutritionTarget.setUser(user);
        nutritionTarget.setBmiRecord(bmiRecord);

        nutritionTargetRepository.save(nutritionTarget);
    }
}
