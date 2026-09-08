package com.nutricare.user.dto;

import com.nutricare.user.model.ActivityLevel;
import com.nutricare.user.model.Goal;

import java.math.BigDecimal;
import java.time.LocalDate;

public class NutritionTargetResponseDTO {
    private Integer nutritionTargetId;
    private ActivityLevel activityLevel;
    private Goal goal;
    private BigDecimal bmr;
    private BigDecimal tdee;
    private BigDecimal calorieTarget;
    private BigDecimal proteinG;
    private BigDecimal fatG;
    private BigDecimal carbG;
    private BigDecimal fiberG;
    private BigDecimal waterML;
    private BigDecimal sodiumG;
    private LocalDate effectiveFrom;
    private LocalDate effectiveTo;
    private Boolean isActive;

    public NutritionTargetResponseDTO() {
    }

    public NutritionTargetResponseDTO(Integer nutritionTargetId, ActivityLevel activityLevel, Goal goal, BigDecimal bmr, BigDecimal tdee, BigDecimal calorieTarget, BigDecimal proteinG, BigDecimal fatG, BigDecimal carbG, BigDecimal fiberG, BigDecimal waterML, BigDecimal sodiumG, LocalDate effectiveFrom, LocalDate effectiveTo, Boolean isActive) {
        this.nutritionTargetId = nutritionTargetId;
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.bmr = bmr;
        this.tdee = tdee;
        this.calorieTarget = calorieTarget;
        this.proteinG = proteinG;
        this.fatG = fatG;
        this.carbG = carbG;
        this.fiberG = fiberG;
        this.waterML = waterML;
        this.sodiumG = sodiumG;
        this.effectiveFrom = effectiveFrom;
        this.effectiveTo = effectiveTo;
        this.isActive = isActive;
    }

    public Integer getNutritionTargetId() {
        return nutritionTargetId;
    }

    public void setNutritionTargetId(Integer nutritionTargetId) {
        this.nutritionTargetId = nutritionTargetId;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public BigDecimal getBmr() {
        return bmr;
    }

    public void setBmr(BigDecimal bmr) {
        this.bmr = bmr;
    }

    public BigDecimal getTdee() {
        return tdee;
    }

    public void setTdee(BigDecimal tdee) {
        this.tdee = tdee;
    }

    public BigDecimal getCalorieTarget() {
        return calorieTarget;
    }

    public void setCalorieTarget(BigDecimal calorieTarget) {
        this.calorieTarget = calorieTarget;
    }

    public BigDecimal getProteinG() {
        return proteinG;
    }

    public void setProteinG(BigDecimal proteinG) {
        this.proteinG = proteinG;
    }

    public BigDecimal getFatG() {
        return fatG;
    }

    public void setFatG(BigDecimal fatG) {
        this.fatG = fatG;
    }

    public BigDecimal getCarbG() {
        return carbG;
    }

    public void setCarbG(BigDecimal carbG) {
        this.carbG = carbG;
    }

    public BigDecimal getFiberG() {
        return fiberG;
    }

    public void setFiberG(BigDecimal fiberG) {
        this.fiberG = fiberG;
    }

    public BigDecimal getWaterML() {
        return waterML;
    }

    public void setWaterML(BigDecimal waterML) {
        this.waterML = waterML;
    }

    public BigDecimal getSodiumG() {
        return sodiumG;
    }

    public void setSodiumG(BigDecimal sodiumG) {
        this.sodiumG = sodiumG;
    }

    public LocalDate getEffectiveFrom() {
        return effectiveFrom;
    }

    public void setEffectiveFrom(LocalDate effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }

    public LocalDate getEffectiveTo() {
        return effectiveTo;
    }

    public void setEffectiveTo(LocalDate effectiveTo) {
        this.effectiveTo = effectiveTo;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
