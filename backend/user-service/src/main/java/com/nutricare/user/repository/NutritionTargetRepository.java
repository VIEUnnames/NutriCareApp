package com.nutricare.user.repository;

import com.nutricare.user.dto.NutritionTargetResponseDTO;
import com.nutricare.user.model.NutritionTarget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionTargetRepository extends JpaRepository<NutritionTarget, Integer> {
    @Query("SELECT new com.nutricare.user.dto.NutritionTargetResponseDTO(" +
            "n.nutritionTargetId, n.activityLevel, n.goal," +
            "n.bmr, n.tdee, n.calorieTarget, n.proteinG, n.fatG," +
            "n.carbG, n.fiberG, n.waterMl, n.sodiumG," +
            "n.effectiveFrom, n.effectiveTo, n.isActive) " +
            "FROM NutritionTarget n " +
            "WHERE n.user.userId = :userId")
    List<NutritionTargetResponseDTO> findByUser_UserId(@Param("userId") Integer userId);

    @Query("SELECT nt FROM NutritionTarget nt WHERE nt.user.userId = :userId AND nt.isActive = true")
    NutritionTarget findActiveByUser_UserId(@Param("userId") Integer userId);
}
