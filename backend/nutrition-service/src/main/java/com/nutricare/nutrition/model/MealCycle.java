package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "meal_cycle", schema = "nutrition")
public class MealCycle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_cycle_id")
    private Integer mealCycleId;

    @Column(name = "number_of_day", nullable = false)
    private Integer numberOfDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_cycle_status", nullable = false)
    private MealCycleStatus mealCycleStatus;

    @ManyToOne
    @JoinColumn(name = "meal_plan_id")
    private MealPlan mealPlan;

    @OneToMany(mappedBy = "mealCycle")
    private List<DailyMeal> dailyMeals;

    public MealCycle() {
    }

    public MealCycle(Integer mealCycleId, Integer numberOfDay, MealCycleStatus mealCycleStatus) {
        this.mealCycleId = mealCycleId;
        this.numberOfDay = numberOfDay;
        this.mealCycleStatus = mealCycleStatus;
    }

    public Integer getMealCycleId() {
        return mealCycleId;
    }

    public void setMealCycleId(Integer mealCycleId) {
        this.mealCycleId = mealCycleId;
    }

    public Integer getNumberOfDay() {
        return numberOfDay;
    }

    public void setNumberOfDay(Integer numberOfDay) {
        this.numberOfDay = numberOfDay;
    }

    public MealCycleStatus getMealCycleStatus() {
        return mealCycleStatus;
    }

    public void setMealCycleStatus(MealCycleStatus mealCycleStatus) {
        this.mealCycleStatus = mealCycleStatus;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }

    public List<DailyMeal> getDailyMeals() {
        return dailyMeals;
    }

    public void setDailyMeals(List<DailyMeal> dailyMeals) {
        this.dailyMeals = dailyMeals;
    }
}
