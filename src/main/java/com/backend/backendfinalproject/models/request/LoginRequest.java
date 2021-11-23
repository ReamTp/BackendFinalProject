package com.backend.backendfinalproject.models.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class LoginRequest {
    @Getter @Setter
    private String email;
    @Getter @Setter
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
