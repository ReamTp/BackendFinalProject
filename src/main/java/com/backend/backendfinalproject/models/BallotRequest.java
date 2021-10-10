package com.backend.backendfinalproject.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter @Setter
@ToString
public class BallotRequest {
    private int user_id;
    private Date date;
    private double total;
    private List<ProductBallot> products;

    public BallotRequest() {}

    public BallotRequest(int user_id, Date date, double total, List<ProductBallot> products) {
        this.user_id = user_id;
        this.date = date;
        this.total = total;
        this.products = products;
    }
}
