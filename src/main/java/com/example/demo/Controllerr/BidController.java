package com.example.demo.Controllerr;

import com.example.demo.Models.Bid;
import com.example.demo.Models.Project;
import com.example.demo.Models.Submission;
import com.example.demo.Repository.BidRepository;
import com.example.demo.Repository.ProjectRepository;
import com.example.demo.Repository.SubmissionRepository;
import com.example.demo.Service.BidService;
import com.example.demo.Service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bids")
public class BidController {

    @Autowired
    private BidService bidService;
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private SubmissionRepository submissionRepository;
    @Autowired
    private ProjectRepository projectRepository;



    @PostMapping
    public ResponseEntity<?> placeBid(@RequestBody Bid bid) {
        try {
            bidService.placeBid(bid);
            return ResponseEntity.ok("Bid placed");
        } catch (RuntimeException e) {
            if (e.getMessage().equals("BID_ALREADY_EXISTS")) {
                return ResponseEntity.badRequest().body("BID_ALREADY_EXISTS");
            }
            return ResponseEntity.status(500).body("Error");
        }
    }

    @GetMapping("/project/{projectId}")
    public List<Bid> getBids(@PathVariable Integer projectId){
        return bidService.getBidsForProject(projectId);
    }
    @PutMapping("/accept")
    public String acceptBid(@RequestParam Integer bidId,
                            @RequestParam Integer projectId,
                            @RequestParam Integer freelancerId){

        bidService.acceptBid(bidId, projectId, freelancerId);

        // ✅ GET PROJECT (for title)
        Project project = projectRepository.getProjectById(projectId);

        // 🔔 SEND NOTIFICATION TO FREELANCER
        notificationService.createNotification(
                freelancerId,
                "Bid Accepted 🎉",
                "Your bid for project '" + project.getTitle() + "' was accepted.",
                "BID_ACCEPTED",
                projectId
        );

        return "Bid accepted successfully";
    }
    @PutMapping("/reject")
    public String rejectBid(@RequestParam Integer bidId){
        bidService.rejectBid(bidId);
        return "Bid rejected successfully";
    }

    @DeleteMapping("/{bidId}")
    public String deleteBid(@PathVariable Integer bidId) {
        bidService.deleteBid(bidId);
        return "Bid deleted successfully";
    }

    @GetMapping("/freelancer/{freelancerId}")
    public List<Bid> getFreelancerBids(@PathVariable Integer freelancerId){
        return bidService.getBidsByFreelancer(freelancerId);
    }
    @GetMapping("/client/{clientId}")
    public List<Bid> getBidsByClient(@PathVariable Integer clientId) {
        return bidRepository.getBidsByClient(clientId);
    }
    @GetMapping("/contracts/client/{clientId}")
    public List<Bid> getClientContracts(@PathVariable Integer clientId) {
        return bidRepository.getAcceptedContractsByClient(clientId);
    }
    @GetMapping("/generate-team/{projectId}")
    public List<Map<String, Object>> generateTeam(

            @PathVariable Integer projectId,

            @RequestParam Integer size
    ) {

        return bidService.generateTeam(
                projectId,
                size
        );
    }

}
