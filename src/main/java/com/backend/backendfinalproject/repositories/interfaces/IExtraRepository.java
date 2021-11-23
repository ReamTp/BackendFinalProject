package com.backend.backendfinalproject.repositories.interfaces;

import com.backend.backendfinalproject.models.Extra;
import com.backend.backendfinalproject.models.request.Response;

import java.util.List;

public interface IExtraRepository {

    public List<Extra> getExtras();
    public Object getExtra(int id);
    public Response register(Extra extra);
    public Response update(Extra extra);

}
