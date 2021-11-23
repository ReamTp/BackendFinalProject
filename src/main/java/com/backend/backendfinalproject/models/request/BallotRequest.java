package com.backend.backendfinalproject.models.request;

import com.backend.backendfinalproject.models.ProductBallot;
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

    public BallotRequest(int userId, Date date, double total, List<ProductBallot> products) {
        this.user_id = userId;
        this.date = date;
        this.total = total;
        this.products = products;
    }
}
