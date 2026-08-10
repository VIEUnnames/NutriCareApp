package com.nutricare.user.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private Integer userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "hash_password", nullable = false)
    private String hashPassword;

    @Column(nullable = false)
    private Boolean gender;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id", referencedColumnName = "account_id")
    private Account account;

    @OneToMany(mappedBy = "user")
    private List<HealthCondition> healthConditionList;

    @OneToMany(mappedBy = "user")
    private List<BMIRecord> bmiRecordList;

    @OneToMany(mappedBy = "user")
    private List<NutritionTarget> nutritionTargetList;

    public User() {
    }

    public User(Integer userId, String email, String fullName, String hashPassword, Boolean gender, UserType userType) {
        this.userId = userId;
        this.email = email;
        this.fullName = fullName;
        this.hashPassword = hashPassword;
        this.gender = gender;
        this.userType = userType;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<HealthCondition> getHealthConditionList() {
        return healthConditionList;
    }

    public void setHealthConditionList(List<HealthCondition> healthConditionList) {
        this.healthConditionList = healthConditionList;
    }

    public List<BMIRecord> getBmiRecordList() {
        return bmiRecordList;
    }

    public void setBmiRecordList(List<BMIRecord> bmiRecordList) {
        this.bmiRecordList = bmiRecordList;
    }

    public List<NutritionTarget> getNutritionTargetList() {
        return nutritionTargetList;
    }

    public void setNutritionTargetList(List<NutritionTarget> nutritionTargetList) {
        this.nutritionTargetList = nutritionTargetList;
    }
}
