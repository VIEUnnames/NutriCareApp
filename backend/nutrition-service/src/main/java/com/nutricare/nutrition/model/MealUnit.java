package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "meal_unit", schema = "nutrition")
public class MealUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "meal_unit_id")
    private Integer mealUnitId;

    @Column(name = "meal_unit_name", nullable = false, unique = true)
    private String mealUnitName;

    @OneToMany(mappedBy = "mealUnit")
    private List<DailyMealItem> dailyMealItems;

    public MealUnit() {
    }

    public MealUnit(Integer mealUnitId, String mealUnitName) {
        this.mealUnitId = mealUnitId;
        this.mealUnitName = mealUnitName;
    }

    public Integer getMealUnitId() {
        return mealUnitId;
    }

    public void setMealUnitId(Integer mealUnitId) {
        this.mealUnitId = mealUnitId;
    }

    public String getMealUnitName() {
        return mealUnitName;
    }

    public void setMealUnitName(String mealUnitName) {
        this.mealUnitName = mealUnitName;
    }

    public List<DailyMealItem> getDailyMealItems() {
        return dailyMealItems;
    }

    public void setDailyMealItems(List<DailyMealItem> dailyMealItems) {
        this.dailyMealItems = dailyMealItems;
    }
}
