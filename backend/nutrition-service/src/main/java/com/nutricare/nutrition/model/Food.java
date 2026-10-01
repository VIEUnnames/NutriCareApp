package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "foods", schema = "nutrition")
public class Food {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Integer foodId;

    @Column(name = "food_name_vi", nullable = false)
    private String foodNameVi;

    @Column(name = "food_name_en")
    private String foodNameEn;

    @OneToMany(mappedBy = "food")
    private List<FoodNutrient> foodNutrients;

    @ManyToOne
    @JoinColumn(name = "food_group_id")
    private FoodGroup foodGroup;

    @OneToMany(mappedBy = "food")
    private List<DailyMealItem> dailyMealItems;

    public Food() {
    }

    public Food(Integer foodId, String foodNameVi, String foodNameEn) {
        this.foodId = foodId;
        this.foodNameVi = foodNameVi;
        this.foodNameEn = foodNameEn;
    }

    public Integer getFoodId() {
        return foodId;
    }

    public void setFoodId(Integer foodId) {
        this.foodId = foodId;
    }

    public String getFoodNameVi() {
        return foodNameVi;
    }

    public void setFoodNameVi(String foodNameVi) {
        this.foodNameVi = foodNameVi;
    }

    public String getFoodNameEn() {
        return foodNameEn;
    }

    public void setFoodNameEn(String foodNameEn) {
        this.foodNameEn = foodNameEn;
    }

    public List<FoodNutrient> getFoodNutritions() {
        return foodNutrients;
    }

    public void setFoodNutritions(List<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public FoodGroup getFoodGroup() {
        return foodGroup;
    }

    public void setFoodGroup(FoodGroup foodGroup) {
        this.foodGroup = foodGroup;
    }

    public List<DailyMealItem> getDailyMealItems() {
        return dailyMealItems;
    }

    public void setDailyMealItems(List<DailyMealItem> dailyMealItems) {
        this.dailyMealItems = dailyMealItems;
    }
}
