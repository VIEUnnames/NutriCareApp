package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "food_group", schema = "nutrition")
public class FoodGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_group_id")
    private Integer foodGroupId;

    @Column(name = "food_group_name_vi", nullable = false)
    private String foodGroupNameVi;

    @Column(name = "food_group_name_en")
    private String foodGroupNameEn;

    @Column(name = "group_format", unique = true, nullable = false)
    private String groupFormat;

    @OneToMany(mappedBy = "foodGroup")
    private List<Food> foods;

    public FoodGroup() {
    }

    public FoodGroup(Integer foodGroupId, String foodGroupNameVi, String foodGroupNameEn, String groupFormat) {
        this.foodGroupId = foodGroupId;
        this.foodGroupNameVi = foodGroupNameVi;
        this.foodGroupNameEn = foodGroupNameEn;
        this.groupFormat = groupFormat;
    }

    public Integer getFoodGroupId() {
        return foodGroupId;
    }

    public void setFoodGroupId(Integer foodGroupId) {
        this.foodGroupId = foodGroupId;
    }

    public String getFoodGroupNameVi() {
        return foodGroupNameVi;
    }

    public void setFoodGroupNameVi(String foodGroupNameVi) {
        this.foodGroupNameVi = foodGroupNameVi;
    }

    public String getFoodGroupNameEn() {
        return foodGroupNameEn;
    }

    public void setFoodGroupNameEn(String foodGroupNameEn) {
        this.foodGroupNameEn = foodGroupNameEn;
    }

    public String getGroupFormat() {
        return groupFormat;
    }

    public void setGroupFormat(String groupFormat) {
        this.groupFormat = groupFormat;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
