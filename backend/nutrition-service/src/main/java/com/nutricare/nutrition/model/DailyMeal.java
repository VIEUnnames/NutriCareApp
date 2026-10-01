package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "daily_meal", schema = "nutrition")
public class DailyMeal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_meal_id")
    private Integer dailyMealId;

    @Column(name = "day_number", nullable = false)
    private int dayNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "daily_meal_status", nullable = false)
    private DailyMealStatus dailyMealStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "meal_type", nullable = false)
    private MealType mealType;

    @Column(name = "target_calories", nullable = false)
    private BigDecimal targetCalories;

    @Column(name = "target_protein", nullable = false)
    private BigDecimal targetProtein;

    @Column(name = "target_carb", nullable = false)
    private BigDecimal targetCarb;

    @Column(name = "target_fat", nullable = false)
    private BigDecimal targetFat;

    @ManyToOne
    @JoinColumn(name = "meal_cycle_id")
    private MealCycle mealCycle;

    @OneToMany(mappedBy = "dailyMeal")
    private List<DailyMealItem> dailyMealItems;

    public DailyMeal() {
    }

    public DailyMeal(Integer dailyMealId, int dayNumber, DailyMealStatus dailyMealStatus, MealType mealType, BigDecimal targetCalories, BigDecimal targetProtein, BigDecimal targetCarb, BigDecimal targetFat) {
        this.dailyMealId = dailyMealId;
        this.dayNumber = dayNumber;
        this.dailyMealStatus = dailyMealStatus;
        this.mealType = mealType;
        this.targetCalories = targetCalories;
        this.targetProtein = targetProtein;
        this.targetCarb = targetCarb;
        this.targetFat = targetFat;
    }

    public Integer getDailyMealId() {
        return dailyMealId;
    }

    public void setDailyMealId(Integer dailyMealId) {
        this.dailyMealId = dailyMealId;
    }

    public int getDayNumber() {
        return dayNumber;
    }

    public void setDayNumber(int dayNumber) {
        this.dayNumber = dayNumber;
    }

    public DailyMealStatus getDailyMealStatus() {
        return dailyMealStatus;
    }

    public void setDailyMealStatus(DailyMealStatus dailyMealStatus) {
        this.dailyMealStatus = dailyMealStatus;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public BigDecimal getTargetCalories() {
        return targetCalories;
    }

    public void setTargetCalories(BigDecimal targetCalories) {
        this.targetCalories = targetCalories;
    }

    public BigDecimal getTargetProtein() {
        return targetProtein;
    }

    public void setTargetProtein(BigDecimal targetProtein) {
        this.targetProtein = targetProtein;
    }

    public BigDecimal getTargetCarb() {
        return targetCarb;
    }

    public void setTargetCarb(BigDecimal targetCarb) {
        this.targetCarb = targetCarb;
    }

    public BigDecimal getTargetFat() {
        return targetFat;
    }

    public void setTargetFat(BigDecimal targetFat) {
        this.targetFat = targetFat;
    }

    public MealCycle getMealCycle() {
        return mealCycle;
    }

    public void setMealCycle(MealCycle mealCycle) {
        this.mealCycle = mealCycle;
    }

    public List<DailyMealItem> getDailyMealItems() {
        return dailyMealItems;
    }

    public void setDailyMealItems(List<DailyMealItem> dailyMealItems) {
        this.dailyMealItems = dailyMealItems;
    }
}
