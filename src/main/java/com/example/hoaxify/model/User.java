package com.example.hoaxify.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity(name = "Hoxify_User")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String userName;
    private String dispalyName;
    private String password;
}
