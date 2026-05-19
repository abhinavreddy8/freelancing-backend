    package com.example.demo.RowMapper;

    import com.example.demo.Models.Project;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.jdbc.core.JdbcTemplate;
    import org.springframework.jdbc.core.RowMapper;
    import org.springframework.stereotype.Component;

    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.List;

    @Component
    public class ProjectRowMapper implements RowMapper<Project> {

        @Autowired
        private JdbcTemplate jdbcTemplate;

        @Override
        public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

            Project project = new Project();

            project.setId(rs.getInt("id"));
            project.setClientId(rs.getInt("client_id"));
            project.setTitle(rs.getString("title"));
            project.setDescription(rs.getString("description"));
            project.setBudget(rs.getDouble("budget"));
            java.sql.Date deadlineDate =
                    rs.getDate("deadline");

            if(deadlineDate != null){

                project.setDeadline(
                        deadlineDate.toLocalDate()
                );

            }
            project.setStatus(rs.getString("status"));
            project.setSelectedFreelancer(rs.getObject("selected_freelancer", Integer.class));

            // 🔥 ADD THIS PART HERE
            List<String> skills = jdbcTemplate.queryForList(
                    "SELECT skills FROM project_skills WHERE project_id=?",
                    String.class,
                    project.getId()
            );

            project.setSkills(skills);

            return project;
        }
    }