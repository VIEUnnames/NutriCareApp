package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "nutrients", schema = "nutrition")
public class Nutrient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_id")
    private Integer nutrientId;

    @Column(name = "nutrient_name", nullable = false, unique = true)
    private String nutrientName;

    @Column(name = "nutrient_unit", nullable = false, length = 5)
    private String nutrientUnit;

    @OneToMany(mappedBy = "nutrient")
    private List<FoodNutrient> foodNutrients;

    @ManyToOne
    @JoinColumn(name = "nutrient_category_id")
    private NutrientCategory nutrientCategory;

    public Nutrient() {
    }

    public Nutrient(Integer nutrientId, String nutrientName, String nutrientUnit) {
        this.nutrientId = nutrientId;
        this.nutrientName = nutrientName;
        this.nutrientUnit = nutrientUnit;
    }

    public Integer getNutrientId() {
        return nutrientId;
    }

    public void setNutrientId(Integer nutrientId) {
        this.nutrientId = nutrientId;
    }

    public String getNutrientName() {
        return nutrientName;
    }

    public void setNutrientName(String nutrientName) {
        this.nutrientName = nutrientName;
    }

    public String getNutrientUnit() {
        return nutrientUnit;
    }

    public void setNutrientUnit(String nutrientUnit) {
        this.nutrientUnit = nutrientUnit;
    }

    public List<FoodNutrient> getFoodNutrients() {
        return foodNutrients;
    }

    public void setFoodNutrients(List<FoodNutrient> foodNutrients) {
        this.foodNutrients = foodNutrients;
    }

    public NutrientCategory getNutritionCategory() {
        return nutrientCategory;
    }

    public void setNutritionCategory(NutrientCategory nutrientCategory) {
        this.nutrientCategory = nutrientCategory;
    }
}
