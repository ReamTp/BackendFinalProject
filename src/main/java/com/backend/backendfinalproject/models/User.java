package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @Getter @Setter
    private int id;

    @Getter @Setter @Column(nullable = false)
    private String name;

    @Getter @Setter @Column(nullable = false)
    private String last_name;

    @Getter @Setter
    private int phone;

    @Getter @Setter
    private String direction;

    @OneToOne
    @Getter @Setter
    private City city;

    @Getter @Setter @Column(unique = true, nullable = false)
    private String email;

    @Getter @Setter @Column(nullable = false)
    private String password;

    @Getter @Setter
    private String sex;

    @Getter @Setter
    private String avatar;

    @Getter @Setter @Column(nullable = false)
    private Boolean state;

    public User() {}

    public User(int id, String name, String last_name, int phone, String direction, City city, String email, String password, String sex, String avatar, Boolean state) {
        this.id = id;
        this.name = name;
        this.last_name = last_name;
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
