package com.nutricare.user.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class BMIRequestDTO {
    @NotNull
    @Positive(message = "Cân nặng phải là số dương")
    private BigDecimal weightCm;

    @NotNull
    @Positive(message = "Chiều cao phải là số dương")
    private BigDecimal heightCm;

    public BMIRequestDTO() {
    }

    public BMIRequestDTO(BigDecimal weightCm, BigDecimal heightCm) {
        this.weightCm = weightCm;
        this.heightCm = heightCm;
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
}
