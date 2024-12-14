package com.project02_solar_park_api.solarparkapi.services;

import com.project02_solar_park_api.solarparkapi.entities.Customer;
import com.project02_solar_park_api.solarparkapi.entities.Project;
import com.project02_solar_park_api.solarparkapi.repositories.ProjectRepo;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class ProjectService {

    private final ProjectRepo projectRepository;

    public ProjectService(ProjectRepo projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> getAllByCustomer(Customer customer) {
        return this.projectRepository.findByCustomerAndIsActive(customer, 1);
    }

    public List<Project> getAllByCustomer(int customerId) {
        return this.projectRepository.findByCustomer_IdAndIsActive(customerId, 1);
    }

    public boolean createNewProject(Project project) {
        return this.projectRepository.save(project)!= null;
    }
}
