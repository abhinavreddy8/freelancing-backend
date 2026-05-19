package com.example.demo.Repository;

import com.example.demo.Models.User;
import com.example.demo.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AuthRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    UserRowMapper rowMapper;

    // CHECK EMAIL EXISTS
    public Integer checkEmailExists(
            String email
    ) {

        String sql = """
        SELECT COUNT(*)
        FROM users
        WHERE email=?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                Integer.class,
                email
        );
    }

    // REGISTER USER
    public User register(
            User user
    ) {

        String sql = """
        INSERT INTO users
        (name,email,role)
        VALUES (?,?,?)
        RETURNING *
        """;

        return jdbcTemplate.queryForObject(

                sql,

                rowMapper,

                user.getName(),
                user.getEmail(),
                user.getRole()
        );
    }

    // LOGIN
    public User login(
            String email
    ) {

        String sql = """
        SELECT *
        FROM users
        WHERE email=?
        """;

        return jdbcTemplate.queryForObject(
                sql,
                rowMapper,
                email
        );
    }

}