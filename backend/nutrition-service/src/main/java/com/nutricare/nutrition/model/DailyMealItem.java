package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "daily_meal_item", schema = "nutrition")
public class DailyMealItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "daily_meal_item_id")
    private Integer dailyMealItemId;

    @Column(name = "serving_size", nullable = false)
    private BigDecimal servingSize;

    @ManyToOne
    @JoinColumn(name = "meal_unit_id")
    private MealUnit mealUnit;

    @ManyToOne
    @JoinColumn(name = "daily_meal_id")
    private DailyMeal dailyMeal;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    public DailyMealItem() {
    }

    public DailyMealItem(Integer dailyMealItemId, BigDecimal servingSize) {
        this.dailyMealItemId = dailyMealItemId;
        this.servingSize = servingSize;
    }

    public Integer getDailyMealItemId() {
        return dailyMealItemId;
    }

    public void setDailyMealItemId(Integer dailyMealItemId) {
        this.dailyMealItemId = dailyMealItemId;
    }

    public BigDecimal getServingSize() {
        return servingSize;
    }

    public void setServingSize(BigDecimal servingSize) {
        this.servingSize = servingSize;
    }

    public MealUnit getMealUnit() {
        return mealUnit;
    }

    public void setMealUnit(MealUnit mealUnit) {
        this.mealUnit = mealUnit;
    }

    public DailyMeal getDailyMeal() {
        return dailyMeal;
    }

    public void setDailyMeal(DailyMeal dailyMeal) {
        this.dailyMeal = dailyMeal;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }
}
