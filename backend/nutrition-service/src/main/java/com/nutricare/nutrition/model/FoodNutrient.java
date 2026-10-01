package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "food_nutrients", schema = "nutrition")
public class FoodNutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer foodNutritionId;

    private BigDecimal value;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    @ManyToOne
    @JoinColumn(name = "nutrient_id")
    private Nutrient nutrient;

    public FoodNutrient() {
    }

    public FoodNutrient(Integer foodNutritionId, BigDecimal value) {
        this.foodNutritionId = foodNutritionId;
        this.value = value;
    }

    public Integer getFoodNutritionId() {
        return foodNutritionId;
    }

    public void setFoodNutritionId(Integer foodNutritionId) {
        this.foodNutritionId = foodNutritionId;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Food getDish() {
        return food;
    }

    public void setDish(Food food) {
        this.food = food;
    }

    public Nutrient getNutrient() {
        return nutrient;
    }

    public void setNutrient(Nutrient nutrient) {
        this.nutrient = nutrient;
    }
}
