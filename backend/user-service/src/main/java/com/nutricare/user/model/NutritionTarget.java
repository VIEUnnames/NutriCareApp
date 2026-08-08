package com.nutricare.user.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "nutrition_targets")
public class NutritionTarget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrition_target_id")
    private Integer nutritionTargetId;

    @Column(name = "activity_level", nullable = false)
    private ActivityLevel activityLevel;

    @Column(nullable = false)
    private Goal goal;

    @Column(nullable = false)
    private BigDecimal bmr;

    @Column(nullable = false)
    private BigDecimal tdee;

    @Column(name = "calorie_target", nullable = false)
    private BigDecimal calorieTarget;

    @Column(name = "protein_g", nullable = false)
    private BigDecimal proteinG;

    @Column(name = "fat_g", nullable = false)
    private BigDecimal fatG;

    @Column(name = "carb_g", nullable = false)
    private BigDecimal carbG;

    @Column(name = "fiber_g", nullable = false)
    private BigDecimal fiberG;

    @Column(name = "water_ml", nullable = false)
    private BigDecimal waterMl;

    @Column(name = "sodium_g", nullable = false)
    private BigDecimal sodiumG;

    @Column(name = "effective_from", nullable = false)
    private LocalDate effectiveFrom;

    @Column(name = "effective_to")
    private LocalDate effectiveTo;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "bmi_record_id", referencedColumnName = "bmi_record_id")
    private BMIRecord bmiRecord;

    public NutritionTarget() {
    }

    public NutritionTarget(Integer nutritionTargetId, ActivityLevel activityLevel, Goal goal, BigDecimal bmr, BigDecimal tdee, BigDecimal calorieTarget, BigDecimal proteinG, BigDecimal fatG, BigDecimal carbG, BigDecimal fiberG, BigDecimal waterMl, BigDecimal sodiumG, LocalDate effectiveFrom, LocalDate effectiveTo, Boolean isActive) {
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
        this.waterMl = waterMl;
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

    public BigDecimal getWaterMl() {
        return waterMl;
    }

    public void setWaterMl(BigDecimal waterMl) {
        this.waterMl = waterMl;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BMIRecord getBmiRecord() {
        return bmiRecord;
    }

    public void setBmiRecord(BMIRecord bmiRecord) {
        this.bmiRecord = bmiRecord;
    }
}
