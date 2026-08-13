package com.nutricare.user.repository;

import com.nutricare.user.dto.HealthConditionResponseDTO;
import com.nutricare.user.model.HealthCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthConditionRepository extends JpaRepository<HealthCondition, Integer> {
    @Query("SELECT new com.nutricare.user.dto.HealthConditionResponseDTO(" +
            "hc.healthConditionId, hc.healthConditionName, hc.conditionType, " +
            "hc.severity, hc.healthConditionDescription) " +
            "FROM HealthCondition hc " +
            "WHERE hc.user.userId = :userId")
    List<HealthConditionResponseDTO> findAllHealthConditionByUserId(@Param("userId") Integer userId);

    @Query("SELECT new com.nutricare.user.dto.HealthConditionResponseDTO(" +
            "hc.healthConditionId, hc.healthConditionName, hc.conditionType, " +
            "hc.severity, hc.healthConditionDescription) " +
            "FROM HealthCondition hc " +
            "WHERE hc.user.userId = :userId AND hc.healthConditionId = :healthConditionId")
    HealthConditionResponseDTO findHealthConditionByHealthConditionIdAndUserId(@Param("healthConditionId") Integer healthConditionId,
                                                                      @Param("userId") Integer userId);

    HealthCondition findByHealthConditionIdAndUser_UserId(Integer healthConditionId, Integer userId);

    HealthCondition findByHealthConditionNameAndUser_UserId(String healthConditionName, Integer userId);


}
