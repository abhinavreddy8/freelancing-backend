package com.example.demo.Repository;

import com.example.demo.Models.Project;
import com.example.demo.RowMapper.ProjectRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProjectRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    private ProjectRowMapper projectRowMapper;

    public void createProject(Project project) {

        String sql = """
        INSERT INTO projects(client_id, title, description, budget, deadline, status)
        VALUES (?, ?, ?, ?, ?, ?)
        """;

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setObject(1, project.getClientId());
            ps.setString(2, project.getTitle());
            ps.setString(3, project.getDescription());
            ps.setDouble(4, project.getBudget());
            ps.setObject(5, project.getDeadline()); // LocalDate works directly with PostgreSQL
            ps.setString(6, "OPEN");
            return ps;
        }, keyHolder);

        Integer projectId = ((Number) keyHolder.getKeys().get("id")).intValue();

        if (project.getSkills() != null && !project.getSkills().isEmpty()) {
            String skillSql = "INSERT INTO project_skills(project_id, skills) VALUES (?, ?)";
            for (String skill : project.getSkills()) {
                jdbcTemplate.update(skillSql, projectId, skill);
            }
        }
    }

//    public List<Project> getAllProjects(){
//
//        String sql="SELECT * FROM projects";
//
//        return jdbcTemplate.query(sql,new ProjectRowMapper());
//    }

    public List<Project> getAllProjects(){

        String sql = """
    SELECT * FROM projects
    ORDER BY id DESC
    """;

        return jdbcTemplate.query(
                sql,
                projectRowMapper
        );
    }

    public Project getProjectById(Integer id){

        String sql = "SELECT * FROM projects WHERE id=?";

        return jdbcTemplate.queryForObject(sql, projectRowMapper, id);
    }

    public List<Project> searchProjects(String keyword,
                                        Double minBudget,
                                        Double maxBudget) {

        StringBuilder sql = new StringBuilder(
                "SELECT * FROM projects WHERE status='OPEN' "
        );

        List<Object> params = new ArrayList<>();

        if(keyword != null && !keyword.isEmpty()) {

            sql.append(" AND (title ILIKE ? OR description ILIKE ?) ");

            String search = "%" + keyword + "%";

            params.add(search);
            params.add(search);
        }

        if(minBudget != null) {

            sql.append(" AND budget >= ? ");
            params.add(minBudget);

        }

        if(maxBudget != null) {

            sql.append(" AND budget <= ? ");
            params.add(maxBudget);

        }

        return jdbcTemplate.query(
                sql.toString(),
                projectRowMapper,
                params.toArray()
        );
    }

    public List<Project> getProjectsByClient(Integer clientId){

        String sql = "SELECT * FROM projects WHERE client_id=?";

        return jdbcTemplate.query(sql, projectRowMapper, clientId);
    }
    public void updateProject(Project project) {
        String sql = """
        UPDATE projects 
        SET title=?, description=?, budget=?, deadline=?, status=?
        WHERE id=?
        """;
        jdbcTemplate.update(sql,
                project.getTitle(),
                project.getDescription(),
                project.getBudget(),
                project.getDeadline(),
                project.getStatus(),
                project.getId()
        );

        // Delete old skills and re-insert
        jdbcTemplate.update("DELETE FROM project_skills WHERE project_id=?", project.getId());

        if (project.getSkills() != null) {
            String skillSql = "INSERT INTO project_skills(project_id, skills) VALUES (?,?)";
            for (String skill : project.getSkills()) {
                jdbcTemplate.update(skillSql, project.getId(), skill);
            }
        }
    }

    public void deleteProject(Integer id) {

        jdbcTemplate.update(
                "DELETE FROM project_skills WHERE project_id=?",
                id
        );

        jdbcTemplate.update(
                "DELETE FROM bids WHERE project_id=?",
                id
        );

        jdbcTemplate.update(
                "DELETE FROM submissions WHERE project_id=?",
                id
        );

        jdbcTemplate.update(
                "DELETE FROM bugs WHERE project_id=?",
                id
        );

        jdbcTemplate.update(
                "DELETE FROM notifications WHERE reference_id=?",
                id
        );

        jdbcTemplate.update(
                "DELETE FROM reviews WHERE project_id=?",
                id
        );

        jdbcTemplate.update(
                "DELETE FROM projects WHERE id=?",
                id
        );
    }
}
