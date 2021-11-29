package com.backend.backendfinalproject.controller;

import com.backend.backendfinalproject.models.Ballot;
import com.backend.backendfinalproject.models.ProductBallot;
import com.backend.backendfinalproject.models.request.BallotRequest;
import com.backend.backendfinalproject.models.request.Response;
import com.backend.backendfinalproject.models.User;
import com.backend.backendfinalproject.repositories.interfaces.IBallotRepository;
import com.backend.backendfinalproject.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
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

    @RequestMapping("/with-token")
    public List<Ballot> getBallotsWithToken(@RequestHeader(value = "Authorization") String token) {
        if(jwtUtil.validateToken(token)) {
            int id = Integer.parseInt(jwtUtil.getValue(token));
            return ballotRepository.getBallotsWithToken(id);
        }

        return new ArrayList<>();
    }

    @RequestMapping("/products/{id}")
    public List<ProductBallot> getProductsBallots(@PathVariable int id) {
        return ballotRepository.getProductsBallots(id);
    }

    @RequestMapping("/{id}")
    public Object getBallot(@PathVariable int id) {
        return ballotRepository.getBallot(id);
    }

    @PostMapping(value = "/register")
    public Response register(@RequestHeader(value = "Authorization") String token, @RequestBody BallotRequest request) {
        Ballot ballot = new Ballot();

        if(jwtUtil.validateToken(token)) {
            int id = Integer.parseInt(jwtUtil.getValue(token));
            User user = new User();
            user.setId(id);

            ballot.setUser(user);
            ballot.setDate(request.getDate());
            ballot.setTotal(request.getTotal());

            return ballotRepository.register(ballot, request.getProducts(), id);
        }

        return new Response("Error al registrar Usuario", false);
    }
}
