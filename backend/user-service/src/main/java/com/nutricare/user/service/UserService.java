package com.nutricare.user.service;

import com.nutricare.user.dto.*;
import jakarta.validation.Valid;

import java.util.List;

public interface UserService {
    UserInfoResponseDTO login(LoginRequestDTO loginRequestDTO);

    void register(RegisterRequestDTO registerRequestDTO);

    List<HealthConditionResponseDTO> getHealthConditionList(Integer userId);

    void addHealthCondition(HealthConditionRequestDTO healthConditionRequestDTO, Integer userId);

    HealthConditionResponseDTO getHealthCondition(Integer userId, Integer healthConditionId);

    void updateHealthCondition(Integer userId, Integer healthConditionId, HealthConditionRequestDTO healthConditionRequestDTO);

    void deleteHealthCondition(Integer userId, Integer healthConditionId);

    List<BMIResponseDTO> getBMIRecordList(Integer userId);

    BMIResponseDTO getBMIRecord(Integer userId, Integer bmiRecordId);

    void addBmiRecord(Integer userId, BMIRequestDTO bmiRequestDTO);

    void updateBmiRecord(Integer userId, Integer bmiRecordId, BMIRequestDTO bmiRequestDTO);

    void deleteBmiRecord(Integer userId, Integer bmiRecordId);

    List<NutritionTargetResponseDTO> getNutritionTargetList(Integer userId);

    void addNutritionTarget(Integer userId, NutritionTargetRequestDTO nutritionTargetRequestDTO);
}
