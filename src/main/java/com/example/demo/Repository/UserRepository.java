package com.example.demo.Repository;

import com.example.demo.Models.User;
import com.example.demo.RowMapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private UserRowMapper rowMapper;

    public void register(User user){

        String sql = """
        INSERT INTO users(name,email,password,role)
        VALUES (?,?,?,?)
        """;

        jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getPassword(),
                user.getRole());
    }

    public User findByEmail(String email){

        String sql = "SELECT * FROM users WHERE email=?";

        return jdbcTemplate.queryForObject(sql,rowMapper,email);
    }
}
