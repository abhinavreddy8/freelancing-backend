package com.example.demo.RowMapper;

import com.example.demo.Models.FreelancerProfile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FreelancerProfileRowMapper
        implements RowMapper<FreelancerProfile> {

    @Override
    public FreelancerProfile mapRow(
            ResultSet rs,
            int rowNum
    ) throws SQLException {

        FreelancerProfile profile =
                new FreelancerProfile();

        profile.setId(rs.getInt("id"));
        profile.setUserId(rs.getInt("user_id"));
        profile.setProfession(rs.getString("profession"));
        profile.setBio(rs.getString("bio"));
        profile.setExperience(rs.getInt("experience"));
        profile.setPhone(rs.getString("phone"));
        profile.setAddress(rs.getString("address"));
        profile.setLinkedinUrl(rs.getString("linkedin_url"));
        profile.setGithubUrl(rs.getString("github_url"));
        profile.setOtherProfileUrl(rs.getString("other_profile_url"));
        profile.setResumeUrl(rs.getString("resume_url"));
        profile.setPortfolioUrl(rs.getString("portfolio_url"));
        profile.setProfilePhoto(rs.getString("profile_photo"));

        return profile;
    }
}