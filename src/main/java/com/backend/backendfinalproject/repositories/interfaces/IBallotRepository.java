package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Ballot;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.ProductBallot;

import java.util.List;

public interface IBallotRepository {
    List<Ballot> getBallots();
    Object getBallot(int id);
    Response register(Ballot ballot, List<ProductBallot> products);
}
