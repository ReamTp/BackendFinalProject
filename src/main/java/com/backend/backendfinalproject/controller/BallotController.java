package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.*;
import com.backend.backendfinalproject.repositories.interfaces.IBallotRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ballots")
public class BallotController {
    @Autowired
    private IBallotRepository ballotRepository;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping("")
    public List<Ballot> getBallots() {
        return ballotRepository.getBallots();
    }

    @RequestMapping("/{id}")
    public Object getBallot(@PathVariable int id) {
        return ballotRepository.getBallot(id);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public Response register(@RequestBody BallotRequest request) {
        System.out.println(request);
        Ballot ballot = new Ballot();
        User user = new User();
        Extra extra = new Extra();

        user.setId(request.getUser_id());
        ballot.setUser(user);
        ballot.setDate(request.getDate());
        ballot.setTotal(request.getTotal());

        return ballotRepository.register(ballot, request.getProducts(), request.getExtras());
    }
}
