package com.example.demo.RowMapper;

import com.example.demo.Models.ClientProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ClientProfileRowMapper
        implements RowMapper<ClientProfile> {

    @Override
    public ClientProfile mapRow(
            ResultSet rs,
            int rowNum
    ) throws SQLException {

        ClientProfile profile =
                new ClientProfile();

        profile.setId(rs.getInt("id"));
        profile.setUserId(rs.getInt("user_id"));
        profile.setCompanyName(rs.getString("company_name"));
        profile.setCompanyDescription(
                rs.getString("company_description")
        );

        profile.setProfession(
                rs.getString("profession")
        );

        profile.setPhone(rs.getString("phone"));

        profile.setAddress(rs.getString("address"));

        profile.setLinkedinUrl(
                rs.getString("linkedin_url")
        );

        profile.setGithubUrl(
                rs.getString("github_url")
        );

        profile.setWebsite(
                rs.getString("website")
        );

        profile.setOtherProfileUrl(
                rs.getString("other_profile_url")
        );

        profile.setTotalProjectsPosted(
                rs.getInt("total_projects_posted")
        );

        profile.setTotalSpent(
                rs.getDouble("total_spent")
        );

        profile.setProfilePhoto(
                rs.getString("profile_photo")
        );

        return profile;
    }
}