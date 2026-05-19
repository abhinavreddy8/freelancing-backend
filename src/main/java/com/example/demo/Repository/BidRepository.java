package com.example.demo.Repository;

import com.example.demo.Models.Bid;
import com.example.demo.Models.MlBidData;
import com.example.demo.RowMapper.BidRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class BidRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public void placeBid(Bid bid){

        // ✅ Check if already bid exists
        String checkSql = "SELECT COUNT(*) FROM bids WHERE project_id=? AND freelancer_id=?";

        Integer count = jdbcTemplate.queryForObject(
                checkSql,
                Integer.class,
                bid.getProjectId(),
                bid.getFreelancerId()
        );

        if (count != null && count > 0) {
            throw new RuntimeException("BID_ALREADY_EXISTS");
        }

        // ✅ Insert only if not exists
        String sql = """
        INSERT INTO bids(project_id,freelancer_id,bid_amount,proposal,status)
        VALUES (?,?,?,?,?)
        """;

        jdbcTemplate.update(sql,
                bid.getProjectId(),
                bid.getFreelancerId(),
                bid.getBidAmount(),
                bid.getProposal(),
                "PENDING");
    }

    public List<Bid> getProjectBids(Integer projectId){

        String sql="SELECT * FROM bids WHERE project_id=?";

        return jdbcTemplate.query(sql,new BidRowMapper(),projectId);
    }
    public void acceptBid(Integer bidId, Integer projectId, Integer freelancerId){

        // ✅ Only update selected bid
        jdbcTemplate.update(
                "UPDATE bids SET status='ACCEPTED' WHERE id=?", bidId);

        // ❌ REMOVE auto reject logic

        // (Optional) keep project update or remove — see below
        jdbcTemplate.update(
                "UPDATE projects SET selected_freelancer=?, status='IN_PROGRESS' WHERE id=?",
                freelancerId, projectId);
    }
    public void deleteBid(Integer bidId){

        String sql = "DELETE FROM bids WHERE id = ?";

        jdbcTemplate.update(sql, bidId);
    }
    @GetMapping("/freelancer/{freelancerId}")
    public List<Bid> getBidsByFreelancer(@PathVariable Integer freelancerId) {

        String sql = """
        SELECT * FROM bids 
        WHERE freelancer_id = ? 
        AND status IN ('PENDING', 'ACCEPTED')
    """;

        return jdbcTemplate.query(sql, new Object[]{freelancerId}, (rs, rowNum) -> {

            Timestamp ts = rs.getTimestamp("created_at");

            return new Bid(
                    rs.getInt("id"),
                    rs.getInt("project_id"),
                    rs.getInt("freelancer_id"),
                    rs.getDouble("bid_amount"),
                    rs.getString("proposal"),
                    rs.getString("status"),
                    ts != null ? ts.toLocalDateTime() : null
            );
        });
    }
    public void rejectBid(Integer bidId){

        // 1️⃣ Reject the bid
        String sql = "UPDATE bids SET status='REJECTED' WHERE id=?";
        jdbcTemplate.update(sql, bidId);

        // 2️⃣ Get project_id of this bid
        String getProjectSql = "SELECT project_id FROM bids WHERE id=?";
        Integer projectId = jdbcTemplate.queryForObject(getProjectSql, Integer.class, bidId);

        // 3️⃣ Check if ANY accepted bids exist
        String checkAcceptedSql = "SELECT COUNT(*) FROM bids WHERE project_id=? AND status='ACCEPTED'";
        Integer acceptedCount = jdbcTemplate.queryForObject(
                checkAcceptedSql,
                Integer.class,
                projectId
        );

        // 4️⃣ If no accepted bids → make project OPEN again
        if (acceptedCount == 0) {

            jdbcTemplate.update(
                    "UPDATE projects SET status='OPEN', selected_freelancer=NULL WHERE id=?",
                    projectId
            );
        }
    }
    public List<Bid> getBidsByClient(Integer clientId) {

        String sql = """
        SELECT b.* FROM bids b
        JOIN projects p ON b.project_id = p.id
        WHERE p.client_id = ?
        """;

        return jdbcTemplate.query(sql, new BidRowMapper(), clientId);
    }
    public List<Bid> getAcceptedContractsByClient(Integer clientId) {

        String sql = """
        SELECT b.*, p.title, p.budget
        FROM bids b
        JOIN projects p ON b.project_id = p.id
        WHERE p.client_id = ? AND b.status = 'ACCEPTED'
    """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {

            return new Bid(
                    rs.getInt("id"),
                    rs.getInt("project_id"),
                    rs.getInt("freelancer_id"),
                    rs.getDouble("bid_amount"),
                    rs.getString("proposal"),
                    rs.getString("status"),
                    null // ignore timestamp if not needed
            );
        }, clientId);
    }
    public List<MlBidData> getMlBidData(
            Integer projectId
    ) {

        String sql = """

    SELECT

        b.id AS bid_id,

        COALESCE(
            fp.profile_photo,
            ''
        ) AS skills,

        COALESCE(
            AVG(r.rating),
            0
        ) AS rating,

        COALESCE(
            fp.experience,
            0
        ) AS experience

    FROM bids b

    LEFT JOIN freelancer_profiles fp
        ON b.freelancer_id = fp.user_id

    LEFT JOIN reviews r
        ON r.reviewee_id = b.freelancer_id

    WHERE b.project_id = ?

    GROUP BY
        b.id,
        fp.profile_photo,
        fp.experience

    """;

        return jdbcTemplate.query(

                sql,

                (rs, rowNum) -> {

                    MlBidData data =
                            new MlBidData();

                    data.setBid_id(
                            rs.getInt("bid_id")
                    );

                    data.setSkills(
                            rs.getString("skills")
                    );

                    data.setRating(
                            rs.getDouble("rating")
                    );

                    data.setExperience(
                            rs.getDouble("experience")
                    );

                    return data;
                },

                projectId
        );
    }

}
