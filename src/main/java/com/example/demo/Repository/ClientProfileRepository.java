package com.example.demo.Repository;

import com.example.demo.Models.ClientProfile;
import com.example.demo.RowMapper.ClientProfileRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ClientProfileRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private ClientProfileRowMapper rowMapper;

    public void createProfile(ClientProfile profile){

        String sql = """
        INSERT INTO client_profiles
        (
            user_id,
            company_name,
            company_description,
            profession,
            phone,
            address,
            linkedin_url,
            github_url,
            website,
            other_profile_url,
            total_projects_posted,
            total_spent,
            profile_photo
        )
        VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)
        """;

        jdbcTemplate.update(sql,

                profile.getUserId(),
                profile.getCompanyName(),
                profile.getCompanyDescription(),
                profile.getProfession(),
                profile.getPhone(),
                profile.getAddress(),
                profile.getLinkedinUrl(),
                profile.getGithubUrl(),
                profile.getWebsite(),
                profile.getOtherProfileUrl(),
                profile.getTotalProjectsPosted(),
                profile.getTotalSpent(),
                profile.getProfilePhoto()
        );
    }

    public ClientProfile getProfile(Integer userId){

        String sql = """
        SELECT * FROM client_profiles
        WHERE user_id=?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                userId
        );
    }
    public void updateProfile(
            ClientProfile profile
    ){

        String sql = """
    UPDATE client_profiles
    SET company_name=?,
        company_description=?,
        website=?,
        profession=?,
        phone=?,
        address=?,
        linkedin_url=?,
        github_url=?,
        other_profile_url=?
    WHERE user_id=?
    """;

        jdbcTemplate.update(

                sql,

                profile.getCompanyName(),

                profile.getCompanyDescription(),

                profile.getWebsite(),

                profile.getProfession(),

                profile.getPhone(),

                profile.getAddress(),

                profile.getLinkedinUrl(),

                profile.getGithubUrl(),

                profile.getOtherProfileUrl(),

                profile.getUserId()
        );
    }

}