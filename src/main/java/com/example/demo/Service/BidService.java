package com.example.demo.Service;

import com.example.demo.Models.Bid;
import com.example.demo.Models.Project;
import com.example.demo.Models.TeamRequest;
import com.example.demo.Repository.BidRepository;
import com.example.demo.Repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

import com.example.demo.Models.MlBidData;

import java.util.List;

@Service
public class BidService {

    @Autowired
    private BidRepository bidRepository;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ProjectRepository projectRepository;

    public void placeBid(Bid bid){
        bidRepository.placeBid(bid);
    }

    public List<Bid> getBidsForProject(
            Integer projectId
    ) {

        List<Bid> bids =
                bidRepository.getProjectBids(
                        projectId
                );

        Project project =
                projectRepository.getProjectById(
                        projectId
                );

        List<MlBidData> mlData =
                bidRepository.getMlBidData(
                        projectId
                );

        Map<String, Object> body =
                new HashMap<>();

        body.put(

                "required_skills",

                String.join(
                        ", ",
                        project.getSkills()
                )
        );

        body.put(
                "bids",
                mlData
        );

        String flaskUrl =
                "http://127.0.0.1:5000/rank-bids";

        ResponseEntity<List> response =
                restTemplate.postForEntity(

                        flaskUrl,

                        body,

                        List.class
                );

        List<Map<String, Object>> ranked =
                response.getBody();

        Map<Integer, Double> scores =
                new HashMap<>();

        for (Map<String, Object> item : ranked) {

            Integer bidId =
                    ((Number)item.get("bid_id"))
                            .intValue();

            Double score =
                    ((Number)item.get("score"))
                            .doubleValue();

            scores.put(
                    bidId,
                    score
            );
        }

        bids.sort((a, b) -> Double.compare(

                scores.getOrDefault(
                        b.getId(),
                        0.0
                ),

                scores.getOrDefault(
                        a.getId(),
                        0.0
                )
        ));

        return bids;
    }

    public void acceptBid(Integer bidId,Integer projectId,Integer freelancerId){
        bidRepository.acceptBid(bidId,projectId,freelancerId);
    }
    public void deleteBid(Integer bidId){
        bidRepository.deleteBid(bidId);
    }
    public List<Bid> getBidsByFreelancer(Integer freelancerId){
        return bidRepository.getBidsByFreelancer(freelancerId);
    }
    public void rejectBid(Integer bidId){
        bidRepository.rejectBid(bidId);
    }
    public List<Map<String, Object>> generateTeam(
            Integer projectId,
            Integer teamSize
    ) {

        // =====================================
        // PROJECT
        // =====================================

        Project project =
                projectRepository.getProjectById(
                        projectId
                );

        // =====================================
        // ML DATA
        // =====================================

        List<MlBidData> mlData =
                bidRepository.getMlBidData(
                        projectId
                );

        // =====================================
        // REQUEST BODY
        // =====================================

        TeamRequest request =
                new TeamRequest();

        request.setRequired_skills(
                String.join(
                        ", ",
                        project.getSkills()
                )
        );

        request.setTeam_size(teamSize);

        request.setBids(mlData);

        // =====================================
        // FLASK URL
        // =====================================

        String flaskUrl =
                "http://127.0.0.1:5000/generate-team";

        // =====================================
        // CALL FLASK
        // =====================================

        ResponseEntity<List> response =
                restTemplate.postForEntity(

                        flaskUrl,

                        request,

                        List.class
                );

        // =====================================
        // RETURN DIRECTLY
        // =====================================

        return response.getBody();
    }
}
