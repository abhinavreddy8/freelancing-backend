package com.example.demo.Models;

import lombok.Data;

import java.util.List;

@Data
public class TeamRequest {

    private String required_skills;

    private Integer team_size;

    private List<MlBidData> bids;
}