package com.nutricare.user.dto;

import com.nutricare.user.model.ActivityLevel;
import com.nutricare.user.model.Goal;
import jakarta.validation.constraints.NotNull;

public class NutritionTargetRequestDTO {
    @NotNull(message = "Phải chọn mức độ hoạt động của bạn")
    private ActivityLevel activityLevel;

    @NotNull(message = "Phải chọn mục tiêu mà bạn muốn đạt được")
    private Goal goal;

    @NotNull(message = "Số tháng hoạt động phải lớn hơn 0")
    private Integer numberOfActive;

    public NutritionTargetRequestDTO() {
    }

    public NutritionTargetRequestDTO(ActivityLevel activityLevel, Goal goal, Integer numberOfActive) {
        this.activityLevel = activityLevel;
        this.goal = goal;
        this.numberOfActive = numberOfActive;
    }

    public ActivityLevel getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }

    public Integer getNumberOfActive() {
        return numberOfActive;
    }

    public void setNumberOfActive(Integer numberOfActive) {
        this.numberOfActive = numberOfActive;
    }
}
