package com.softuni.spring_data_auto_mapping_objects.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class UserRegisterDto {
    private String email;
    private String password;
    private String confirmPassword;
    private String fullName;

    public UserRegisterDto() {
    }

    public UserRegisterDto(String confirmPassword, String email, String fullName, String password) {
        this.confirmPassword = confirmPassword;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Email(message = "Enter valid email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Size(min = 2)
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Pattern(regexp = "[A-Za-z\\d]{6,}]")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
