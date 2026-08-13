package com.nutricare.user.dto;

import com.nutricare.user.model.Role;

public class UserInfoResponseDTO {
    private Integer userId;
    private String fullname;
    private Role role;

    public UserInfoResponseDTO() {
    }

    public UserInfoResponseDTO(Integer userId, String fullname, Role role) {
        this.userId = userId;
        this.fullname = fullname;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
