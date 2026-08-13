package com.nutricare.user.dto;

import com.nutricare.user.model.ConditionType;
import com.nutricare.user.model.Severity;

public class HealthConditionResponseDTO {
    private Integer healthConditionId;
    private String healthConditionName;
    private ConditionType conditionType;
    private Severity severity;
    private String healConditionDescription;

    public HealthConditionResponseDTO() {
    }

    public HealthConditionResponseDTO(Integer healthConditionId, String healthConditionName, ConditionType conditionType, Severity severity, String healConditionDescription) {
        this.healthConditionId = healthConditionId;
        this.healthConditionName = healthConditionName;
        this.conditionType = conditionType;
        this.severity = severity;
        this.healConditionDescription = healConditionDescription;
    }

    public Integer getHealthConditionId() {
        return healthConditionId;
    }

    public void setHealthConditionId(Integer healthConditionId) {
        this.healthConditionId = healthConditionId;
    }

    public String getHealthConditionName() {
        return healthConditionName;
    }

    public void setHealthConditionName(String healthConditionName) {
        this.healthConditionName = healthConditionName;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getHealConditionDescription() {
        return healConditionDescription;
    }

    public void setHealConditionDescription(String healConditionDescription) {
        this.healConditionDescription = healConditionDescription;
    }
}
