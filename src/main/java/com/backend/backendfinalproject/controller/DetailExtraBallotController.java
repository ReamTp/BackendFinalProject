package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.*;
import com.backend.backendfinalproject.repositories.interfaces.IDetailExtraBallotRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detailExtraBallot")
public class DetailExtraBallotController {

    @Autowired
    private IDetailExtraBallotRepository iDetailExtraBallotRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping("")
    public List<DetailExtraBallot> getBallots() {
        return iDetailExtraBallotRepository.getDetailExtraBallots();
    }

    @RequestMapping("/{id}")
    public Object getBallot(@PathVariable int id) {
        return iDetailExtraBallotRepository.getDetailExtraBallot(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody DetailExtraBallot request) {
        System.out.println(request);

        DetailExtraBallot detailExtraBallot = new DetailExtraBallot();

        Ballot ballot = new Ballot();
        ballot.setId(request.getBallot().getId());

        Extra extra = new Extra();
        extra.setId(request.getExtra().getId());

        detailExtraBallot.setBallot(ballot);
        detailExtraBallot.setExtra(extra);
        detailExtraBallot.setQuantity(request.getQuantity());

        return iDetailExtraBallotRepository.register(detailExtraBallot);
    }
}
