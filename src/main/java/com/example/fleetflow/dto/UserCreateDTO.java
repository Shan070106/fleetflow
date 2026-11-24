package com.example.fleetflow.dto;

import com.example.fleetflow.enums.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;


public class UserCreateDTO {
    
    @NotBlank (message = "Name is required and cannot be left as blank")
    @Size (max = 120)
    private String name;
    
    @NotBlank (message = "Email is required and cannot be left as blank")
    @Email (message = "Enter a valid email address")
    @Size (max = 150)
    private String email;

    @NotBlank (message = "Password is required")
    @Size (min = 8, max = 100, message = "Password must be within 8 to 100 characters")
    private String password;

    @NotNull (message = "Role is required")
    private Role role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

   
}
