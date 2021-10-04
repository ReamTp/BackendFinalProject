package com.backend.backendfinalproject.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "cities")
public class City implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private int id;
    private String name;

    @OneToOne()
    private Department department;
    private Boolean state;

    public City() {}

    public City(int id, String name, Department department, Boolean state) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    @Override
    public String toString(){
        return "City [id="+id+", name="+name+", department="+department+", state="+state+"]";
    }
}
