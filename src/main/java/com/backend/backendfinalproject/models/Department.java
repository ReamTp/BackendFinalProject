package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "departments")
@ToString
public class Department implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @Getter @Setter
    private int id;

    @Getter @Setter @Column(nullable = false)
    private String name;

    @Getter @Setter @Column(nullable = false)
    private Boolean state;

    public Department() {}

    public Department(int id, String name, Boolean state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }
}
