package com.example.demo.Repository;

import com.example.demo.Models.FreelancerProfile;
import com.example.demo.RowMapper.FreelancerProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FreelancerProfileRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private FreelancerProfileRowMapper rowMapper;

    public void createProfile(FreelancerProfile profile){

        String sql = """
        INSERT INTO freelancer_profiles
        (
            user_id,
            profession,
            bio,
            experience,
            phone,
            address,
            linkedin_url,
            github_url,
            other_profile_url,
            resume_url,
            portfolio_url,
            profile_photo
        )
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        jdbcTemplate.update(sql,

                profile.getUserId(),
                profile.getProfession(),
                profile.getBio(),
                profile.getExperience(),
                profile.getPhone(),
                profile.getAddress(),
                profile.getLinkedinUrl(),
                profile.getGithubUrl(),
                profile.getOtherProfileUrl(),
                profile.getResumeUrl(),
                profile.getPortfolioUrl(),
                profile.getProfilePhoto()
        );
    }

    public FreelancerProfile getProfile(Integer userId){

        String sql = """
        SELECT * FROM freelancer_profiles
        WHERE user_id=?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                userId
        );
    }
    public void updateProfile(
            FreelancerProfile profile
    ){

        String sql = """
    UPDATE freelancer_profiles
    SET profession=?,
        bio=?,
        phone=?,
        address=?,
        linkedin_url=?,
        github_url=?
    WHERE user_id=?
    """;

        jdbcTemplate.update(

                sql,

                profile.getProfession(),

                profile.getBio(),

                profile.getPhone(),

                profile.getAddress(),

                profile.getLinkedinUrl(),

                profile.getGithubUrl(),

                profile.getUserId()
        );
    }

}