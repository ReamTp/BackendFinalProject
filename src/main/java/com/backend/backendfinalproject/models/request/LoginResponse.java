package com.backend.backendfinalproject.models.request;

import lombok.Getter;
import lombok.Setter;

public class LoginResponse {
    @Getter @Setter
    private String token;
    @Getter @Setter
    private String abv;
    @Getter @Setter
    private Boolean result;

    public LoginResponse() {}

    public LoginResponse(String token, String abv, Boolean result) {
        this.token = token;
        this.abv = abv;
        this.result = result;
    }
}
