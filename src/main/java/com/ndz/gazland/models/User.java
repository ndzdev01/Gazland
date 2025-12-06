package com.ndz.gazland.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String firstname;

    @NotNull
    private String lastname;

    @Column(unique = true, name = "phone_number", nullable = false,length = 9)
    @Pattern(regexp = "^6\\d{8}$")
    private String phoneNumber;

    @Email(regexp = "^[a-z0-9_-]+@[a-z]+\\.[a-z]{2,3}$")
    @Column(unique = true, nullable = false)
    private String emailAdress;

    @NotNull
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[.@#$%!?*=&])[A-Za-z\\d.@#$%!?*=&]{6,}$")
    private String password;
}
