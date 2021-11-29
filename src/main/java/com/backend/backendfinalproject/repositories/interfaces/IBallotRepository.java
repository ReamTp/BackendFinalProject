package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Ballot;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.ProductBallot;

import java.util.List;

public interface IBallotRepository {
    List<Ballot> getBallots();
    List<Ballot> getBallotsWithToken(int id);
    Object getBallot(int id);
    List<ProductBallot> getProductsBallots(int id);
    Response register(Ballot ballot, List<ProductBallot> products, int id);
}
