package com.example.hoaxify.dto;

import com.example.hoaxify.annotation.UniqueUserNameConstraint;
import jakarta.validation.constraints.*;

public class UserDto {
    private Long id;
    @NotNull(message = "{hoaxify.com.validation.userName}")
    @Size(min = 4, max = 255, message = "{hoaxify.com.validation.userName.size}")
    @UniqueUserNameConstraint
    private String userName;
    @NotNull(message = "{hoaxify.com.validation.dipalyName}")
    @Size(min = 4, max = 255, message = "{hoaxify.com.validation.disaplyName.size}")
    private String dispalyName;
    @Size(min = 4, max = 255, message = "{hoaxify.com.validation.email.size}")
    @Email
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDispalyName() {
        return dispalyName;
    }

    public void setDispalyName(String dispalyName) {
        this.dispalyName = dispalyName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
