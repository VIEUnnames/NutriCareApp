package com.nutricare.user.dto;

import com.nutricare.user.model.Role;

public class UserInfoResponseDTO {
    private String fullname;
    private Role role;

    public UserInfoResponseDTO() {
    }

    public UserInfoResponseDTO(String fullname, Role role) {
        this.fullname = fullname;
        this.role = role;
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
