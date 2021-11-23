package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class UserDataRegister {
    @Getter @Setter
    private String name;
    @Getter @Setter
    private String lastName;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;

    public UserDataRegister() {}

    public UserDataRegister(String name, String lastName, String email, String password) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
