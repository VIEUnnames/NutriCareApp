package com.nutricare.user.dto;

import com.nutricare.user.model.ConditionType;
import com.nutricare.user.model.Severity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class HealthConditionRequestDTO {
    @NotNull(message = "Phải chọn ít nhất 1 loại bệnh")
    private ConditionType conditionType;

    @NotBlank(message = "Phải nhập tên dị ứng / bệnh / món ăn kiêng")
    @Length(min = 5, max = 50, message = "Tên dị ứng / bệnh / món ăn kiêng quá ngắn hoặc quá dài")
    private String healthConditionName;

    @NotNull(message = "Chưa chọn mức dộ nghiêm trọng của dị ứng / bệnh / món ăn kiêng")
    private Severity severity;

    @Length(max = 200, message = "Dòng giải thích quá dài, hãy viết ngắn gọn lại")
    private String healthConditionDescription;

    public HealthConditionRequestDTO() {
    }

    public HealthConditionRequestDTO(ConditionType conditionType, String healthConditionName, Severity severity, String healthConditionDescription) {
        this.conditionType = conditionType;
        this.healthConditionName = healthConditionName;
        this.severity = severity;
        this.healthConditionDescription = healthConditionDescription;
    }

    public ConditionType getConditionType() {
        return conditionType;
    }

    public void setConditionType(ConditionType conditionType) {
        this.conditionType = conditionType;
    }

    public String getHealthConditionName() {
        return healthConditionName;
    }

    public void setHealthConditionName(String healthConditionName) {
        this.healthConditionName = healthConditionName;
    }

    public Severity getSeverity() {
        return severity;
    }

    public void setSeverity(Severity severity) {
        this.severity = severity;
    }

    public String getHealthConditionDescription() {
        return healthConditionDescription;
    }

    public void setHealthConditionDescription(String healthConditionDescription) {
        this.healthConditionDescription = healthConditionDescription;
    }
}
