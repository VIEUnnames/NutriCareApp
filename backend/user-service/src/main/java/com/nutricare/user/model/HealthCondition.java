package com.nutricare.user.model;

import jakarta.persistence.*;

@Entity
@Table(name = "health_conditions")
public class HealthCondition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "health_condition_id", nullable = false)
    private Integer healthConditionId;

    @Column(name = "health_condition_name", nullable = false)
    private String healthConditionName;

    @Enumerated(EnumType.STRING)
    @Column(name = "condition_type", nullable = false)
    private ConditionType conditionType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Severity severity;

    @Column(name = "health_condition_description", length = 200)
    private String healthConditionDescription;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public HealthCondition() {
    }

    public HealthCondition(Integer healthConditionId, String healthConditionName, ConditionType conditionType, Severity severity, String healthConditionDescription) {
        this.healthConditionId = healthConditionId;
        this.healthConditionName = healthConditionName;
        this.conditionType = conditionType;
        this.severity = severity;
        this.healthConditionDescription = healthConditionDescription;
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
