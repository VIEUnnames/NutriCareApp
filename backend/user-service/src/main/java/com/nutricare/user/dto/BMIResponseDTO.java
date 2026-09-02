package com.nutricare.user.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BMIResponseDTO {
    private Integer bmiRecordId;
    private BigDecimal weightCm;
    private BigDecimal heightCm;
    private LocalDateTime recordAt;

    public BMIResponseDTO() {
    }

    public BMIResponseDTO(Integer bmiRecordId, BigDecimal weightCm, BigDecimal heightCm, LocalDateTime recordAt) {
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

    public LocalDateTime getUpdatedAt() {
        return recordAt;
    }

    public void setUpdatedAt(LocalDateTime recordAt) {
        this.recordAt = recordAt;
    }
}
