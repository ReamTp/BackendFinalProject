package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "detailExtraBallot")
@ToString
public class DetailExtraBallot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) @Column(unique = true, nullable = false)
    @Getter
    @Setter
    private int id;

    @Getter @Setter @OneToOne
    private Ballot ballot;

    @Getter @Setter @OneToOne
    private Extra extra;

    @Getter @Setter
    private int quantity;

    public DetailExtraBallot() {}

    public DetailExtraBallot(int id, Ballot ballot, Extra extra, int quantity) {
        this.id = id;
        this.ballot = ballot;
        this.extra = extra;
        this.quantity = quantity;
    }
}
