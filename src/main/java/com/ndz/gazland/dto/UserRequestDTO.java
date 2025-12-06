package com.ndz.gazland.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class UserRequestDTO {

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @NotNull
    @Email(regexp = "^[a-z_-]+@[a-z0-9_-]+\\.[a-z]{2,3}$")
    @Column(unique = true)
    private String emailAdress;

    @NotNull
    @Pattern(regexp = "^6\\d{8}$")
    @Column(unique = true)
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.@#$%!?*=&])[A-Za-z\\d.@#$%!?*=&]{6,}$")
    private String password;

    @NotNull
    private String role;

    public UserRequestDTO() {
    }

    public UserRequestDTO(String firstname, String lastname, String emailAdress, String phoneNumber, String password, String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAdress = emailAdress;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.role = role;
    }
}
