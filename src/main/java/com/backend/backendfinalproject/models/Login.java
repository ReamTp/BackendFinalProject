package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;

public class Login {
    @Getter @Setter
    private String token;
    @Getter @Setter
    private Boolean result;

    public Login() {}

    public Login(String token, Boolean result) {
        this.token = token;
        this.result = result;
    }
}
