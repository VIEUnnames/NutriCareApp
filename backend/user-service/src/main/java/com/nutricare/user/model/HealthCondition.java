package com.nutricare.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "health_conditions")
public class HealthCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_condition_id", nullable = false)
    private Integer healthConditionId;

    @Column(name = "health_condition_description", length = 200)
    private String healthConditionDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public HealthCondition() {
    }

    public HealthCondition(Integer healthConditionId, String healthConditionDescription) {
        this.healthConditionId = healthConditionId;
        this.healthConditionDescription = healthConditionDescription;
    }

    public Integer getHealthConditionId() {
        return healthConditionId;
    }

    public void setHealthConditionId(Integer healthConditionId) {
        this.healthConditionId = healthConditionId;
    }

    public String getHealthConditionDescription() {
        return healthConditionDescription;
    }

    public void setHealthConditionDescription(String healthConditionDescription) {
        this.healthConditionDescription = healthConditionDescription;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
