package com.backend.backendfinalproject.models.response;

import com.backend.backendfinalproject.models.City;
import lombok.Getter;
import lombok.Setter;

public class UserResponse {
    @Getter
    @Setter
    private int id;

    @Getter @Setter
    private String name;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private int phone;

    @Getter @Setter
    private String direction;

    @Getter @Setter
    private City city;

    @Getter @Setter
    private String email;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private String sex;

    @Getter @Setter
    private String avatar;

    @Getter @Setter
    private Boolean state;

    public UserResponse() {}

    public UserResponse(int id, String name, String lastName, int phone, String direction, City city, String email, String password, String sex, String avatar, Boolean state) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.direction = direction;
        this.city = city;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.avatar = avatar;
        this.state = state;
    }

    public UserResponse(String name, String lastName, int phone, String direction, City city, String email, String password, String sex, String avatar, Boolean state) {
        this.name = name;
        this.lastName = lastName;
        this.phone = phone;
        this.direction = direction;
        this.city = city;
        this.email = email;
        this.password = password;
        this.sex = sex;
        this.avatar = avatar;
        this.state = state;
    }
}
