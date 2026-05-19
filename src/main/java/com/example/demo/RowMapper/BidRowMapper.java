package com.example.demo.RowMapper;

import com.example.demo.Models.Bid;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class BidRowMapper implements RowMapper<Bid> {

    @Override
    public Bid mapRow(ResultSet rs, int rowNum) throws SQLException {

        Bid bid = new Bid();

        bid.setId(rs.getInt("id"));
        bid.setProjectId(rs.getInt("project_id"));
        bid.setFreelancerId(rs.getInt("freelancer_id"));
        bid.setBidAmount(rs.getDouble("bid_amount"));
        bid.setProposal(rs.getString("proposal"));
        bid.setStatus(rs.getString("status"));

        return bid;
    }
}
