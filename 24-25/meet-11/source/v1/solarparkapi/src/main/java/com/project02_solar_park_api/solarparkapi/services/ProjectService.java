package com.project02_solar_park_api.solarparkapi.services;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.entities.Project;
import com.project02_solar_park_api.solarparkapi.repositories.ProjectRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    private ProjectRepository projectRepository;

    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllByCustomer(Customer customer) {
        return this.projectRepository.fetchAll(customer.getId());
    }

    public List<Project> getAllByCustomer(int id) {
        return this.projectRepository.fetchAll(id);
    }

    public boolean createNewProject(Project project) {
        this.projectRepository.create(project);
    }
}
