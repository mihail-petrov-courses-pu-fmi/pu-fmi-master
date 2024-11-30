package com.project02_solar_park_api.solarparkapi.repositories;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.entities.Project;
import com.project02_solar_park_api.solarparkapi.mappers.ProjectRowMapper;
import com.project02_solar_park_api.solarparkapi.system.db.QueryBuilder;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProjectRepository {

    private final QueryBuilder<Project> db;

    public ProjectRepository(QueryBuilder<Project> db) {
        this.db = db;
    }

    public List<Project> fetchAll(int customerId) {

        return this.db.selectAll()
                .from(Project.TABLE)
                .where(Project.columns.CUSTOMER_ID, customerId)
                .andWhere(Project.columns.IS_ACTIVE, 1)
                .fetchAll(new ProjectRowMapper());
    }

    public boolean create(Project project) {

        return this.db.into(Project.TABLE)
                .withValue(Project.columns.CUSTOMER_ID, project.getCustomerId())
                .withValue(Project.columns.NAME, project.getName())
                .withValue(Project.columns.COST, project.getCost())
                .insert();
    }
}
