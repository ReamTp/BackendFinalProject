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
    
}
