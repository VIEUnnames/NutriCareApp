package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "meal_plan", schema = "nutrition")
public class MealPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_plan_id")
    private Integer mealPlanId;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "nutrition_target_id", nullable = false)
    private Integer nutritionTargetId;

    @Column(name = "number_of_month", nullable = false)
    private Integer numberOfMonth;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_plan_status", nullable = false)
    private MealPlanStatus mealPlanStatus;

    @OneToMany(mappedBy = "mealPlan")
    private List<MealCycle> mealCycles;

    public MealPlan() {
    }

    public MealPlan(Integer mealPlanId, Integer userId, Integer nutritionTargetId, Integer numberOfMonth, MealPlanStatus mealPlanStatus) {
        this.mealPlanId = mealPlanId;
        this.userId = userId;
        this.nutritionTargetId = nutritionTargetId;
        this.numberOfMonth = numberOfMonth;
        this.mealPlanStatus = mealPlanStatus;
    }

    public Integer getMealPlanId() {
        return mealPlanId;
    }

    public void setMealPlanId(Integer mealPlanId) {
        this.mealPlanId = mealPlanId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getNutritionTargetId() {
        return nutritionTargetId;
    }

    public void setNutritionTargetId(Integer nutritionTargetId) {
        this.nutritionTargetId = nutritionTargetId;
    }

    public Integer getNumberOfMonth() {
        return numberOfMonth;
    }

    public void setNumberOfMonth(Integer numberOfMonth) {
        this.numberOfMonth = numberOfMonth;
    }

    public MealPlanStatus getMealPlanStatus() {
        return mealPlanStatus;
    }

    public void setMealPlanStatus(MealPlanStatus mealPlanStatus) {
        this.mealPlanStatus = mealPlanStatus;
    }

    public List<MealCycle> getMealCycles() {
        return mealCycles;
    }

    public void setMealCycles(List<MealCycle> mealCycles) {
        this.mealCycles = mealCycles;
    }
}
