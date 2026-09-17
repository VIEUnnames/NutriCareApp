package com.nutricare.user.dto;

import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class RegisterRequestDTO {
    @NotBlank(message = "Họ và tên không được để trống")
    private String fullName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    private String email;

    @NotBlank(message = "Mật khẩu không được để trống")
    @Size(min = 8, max = 20, message = "Mật khẩu dài tối thiểu 8 kí tự và tối đa 24 kí tự")
    private String password;

    @NotNull(message = "Vui lòng chọn giới tính của bạn")
    private Boolean gender;

    @NotNull(message = "Ngày tháng năm sinh không được để trống")
    @DateTimeFormat
    private LocalDate dateOfBirth;

    public RegisterRequestDTO() {
    }

    public RegisterRequestDTO(String fullName, String email, String password, Boolean gender, LocalDate dateOfBirth) {
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
