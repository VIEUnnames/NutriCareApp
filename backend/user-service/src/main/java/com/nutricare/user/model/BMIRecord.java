package com.nutricare.user.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedBy;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "bmi_records")
public class BMIRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bmi_record_id")
    private Integer bmiRecordId;

    @Column(name = "weight_cm", length = 3, nullable = false)
    private BigDecimal weightCm;

    @Column(name = "height_cm", length = 3, nullable = false)
    private BigDecimal heightCm;

    @Column(name = "record_at")
    @CreationTimestamp
    private LocalDateTime recordAt;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(mappedBy = "bmiRecord")
    private List<NutritionTarget> nutritionTargetList;

    public BMIRecord() {
    }

    public BMIRecord(Integer bmiRecordId, BigDecimal weightCm, BigDecimal heightCm, LocalDateTime recordAt) {
        this.bmiRecordId = bmiRecordId;
        this.weightCm = weightCm;
        this.heightCm = heightCm;
        this.recordAt = recordAt;
    }

    public Integer getBmiRecordId() {
        return bmiRecordId;
    }

    public void setBmiRecordId(Integer bmiRecordId) {
        this.bmiRecordId = bmiRecordId;
    }

    public BigDecimal getWeightCm() {
        return weightCm;
    }

    public void setWeightCm(BigDecimal weightCm) {
        this.weightCm = weightCm;
    }

    public BigDecimal getHeightCm() {
        return heightCm;
    }

    public void setHeightCm(BigDecimal heightCm) {
        this.heightCm = heightCm;
    }

    public LocalDateTime getRecordAt() {
        return recordAt;
    }

    public void setRecordAt(LocalDateTime recordAt) {
        this.recordAt = recordAt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<NutritionTarget> getNutritionTargetList() {
        return nutritionTargetList;
    }

    public void setNutritionTargetList(List<NutritionTarget> nutritionTargetList) {
        this.nutritionTargetList = nutritionTargetList;
    }
}
