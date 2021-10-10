package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Ballot;
import com.backend.backendfinalproject.models.DetailExtraBallot;
import com.backend.backendfinalproject.models.Extra;
import com.backend.backendfinalproject.models.Response;

import java.util.List;

public interface IDetailExtraBallotRepository {

    public List<DetailExtraBallot> getDetailExtraBallots();
    public Object getDetailExtraBallot(int id);
    public Response register(DetailExtraBallot detailExtraBallot);
}
