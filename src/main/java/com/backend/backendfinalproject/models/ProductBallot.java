package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class ProductBallot {
    @Getter @Setter
    private int id;
    @Getter @Setter
    private int quantity;

    public ProductBallot() {}

    public ProductBallot(int id, int quantity) {
        this.id = id;
        this.quantity = quantity;
    }
}
