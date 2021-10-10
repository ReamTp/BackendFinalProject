package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ballots")
@ToString
public class Ballot implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(unique = true, nullable = false)
    @Getter @Setter
    private int id;
    @Getter @Setter @OneToOne
    private User user;
    @Getter @Setter
    private Date date;
    @Getter @Setter
    private double total;

    public Ballot() {}

    public Ballot(int id, User user, Date date, double total) {
        this.id = id;
        this.user = user;
        this.date = date;
        this.total = total;
    }
}
