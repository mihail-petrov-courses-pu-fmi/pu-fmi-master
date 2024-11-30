package com.project02_solar_park_api.solarparkapi.mappers;

import com.project02_solar_park_api.solarparkapi.entities.Project;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProjectRowMapper implements RowMapper<Project> {

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {

        Project project = new Project();
        project.setId(rs.getInt("id"));
        project.setName(rs.getString("name"));
        project.setCost(rs.getDouble("cost"));
        project.setCustomerId(rs.getInt("customer_id"));

        return project;
    }
}
