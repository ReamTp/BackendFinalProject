package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.*;

import java.util.List;

public interface IBallotRepository {
    public List<Ballot> getBallots();
    public Object getBallot(int id);
    public Response register(Ballot ballot, List<ProductBallot> products, List<ExtraBallot> extras);
}
