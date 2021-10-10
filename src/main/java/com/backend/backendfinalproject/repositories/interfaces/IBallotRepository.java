package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Ballot;
import com.backend.backendfinalproject.models.Response;
import com.backend.backendfinalproject.models.ProductBallot;

import java.util.List;

public interface IBallotRepository {
    public List<Ballot> getBallots();
    public Object getBallot(int id);
    public Response register(Ballot ballot, List<ProductBallot> products);
}
