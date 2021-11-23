package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Ballot;
import com.backend.backendfinalproject.models.request.BallotRequest;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
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

    @PostMapping(value = "/register")
    public Response register(@RequestBody BallotRequest request) {
        Ballot ballot = new Ballot();
        User user = new User();
        user.setId(request.getUser_id());

        ballot.setUser(user);
        ballot.setDate(request.getDate());
        ballot.setTotal(request.getTotal());

        return ballotRepository.register(ballot, request.getProducts());
    }
}
