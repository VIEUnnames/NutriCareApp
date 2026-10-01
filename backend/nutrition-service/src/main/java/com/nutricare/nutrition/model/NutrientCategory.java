package com.nutricare.nutrition.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "nutrient_categories", schema = "nutrition")
public class NutrientCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nutrient_category_id")
    private Integer nutrientCategoryId;

    @Column(name = "nutrient_category_name", unique = true, nullable = false)
    private String nutrientCategoryName;

    @Column(name = "display_order", nullable = false)
    private Integer displayOrder;

    @OneToMany(mappedBy = "nutrientCategory")
    private List<Nutrient> nutrients;

    public NutrientCategory() {
    }

    public NutrientCategory(Integer nutrientCategoryId, String nutrientCategoryName, Integer displayOrder) {
        this.nutrientCategoryId = nutrientCategoryId;
        this.nutrientCategoryName = nutrientCategoryName;
        this.displayOrder = displayOrder;
    }

    public Integer getNutrientCategoryId() {
        return nutrientCategoryId;
    }

    public void setNutrientCategoryId(Integer nutrientCategoryId) {
        this.nutrientCategoryId = nutrientCategoryId;
    }

    public String getNutrientCategoryName() {
        return nutrientCategoryName;
    }

    public void setNutrientCategoryName(String nutrientCategoryName) {
        this.nutrientCategoryName = nutrientCategoryName;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<Nutrient> getNutrients() {
        return nutrients;
    }

    public void setNutrients(List<Nutrient> nutrients) {
        this.nutrients = nutrients;
    }
}