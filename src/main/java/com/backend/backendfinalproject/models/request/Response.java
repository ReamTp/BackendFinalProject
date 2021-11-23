package com.backend.backendfinalproject.models.request;

import lombok.Getter;
import lombok.Setter;

public class Response {
    @Getter @Setter
    private String message;

    @Getter @Setter
    private boolean status;

    public Response() {}

    public Response(String message, boolean status) {
        this.message = message;
        this.status = status;
    }
}
