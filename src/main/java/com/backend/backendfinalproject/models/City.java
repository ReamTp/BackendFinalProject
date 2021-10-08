package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
@ToString
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    @Getter @Setter
    private int id;

    @Getter @Setter @Column(nullable = false)
    private String name;

    @Getter @Setter @Column(nullable = false)
    private Boolean state;

    @OneToOne() @Getter @Setter @JoinColumn(nullable = false)
    private Department department;

    public City() {}

    public City(int id, String name, Boolean state, Department department) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.department = department;
    }
}
