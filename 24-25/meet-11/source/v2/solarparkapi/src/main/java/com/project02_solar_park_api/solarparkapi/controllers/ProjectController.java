package com.project02_solar_park_api.solarparkapi.controllers;

import com.project02_solar_park_api.solarparkapi.entities.Project;
import com.project02_solar_park_api.solarparkapi.http.AppResponse;
import com.project02_solar_park_api.solarparkapi.services.ProjectService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

    private final ProjectService projectService;

    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @PostMapping("/projects")
    public ResponseEntity<?> createNewProject(@RequestBody Project project) {

        if(this.projectService.createNewProject(project)) {
            return AppResponse.success()
                    .withMessage("Project created successfully")
                    .build();
        }

        return AppResponse.error()
                .withMessage("Project creation failed")
                .build();
    }

    // GET ALL PROJECTS BY CUSTOMER

    // GET SPECIFIC PROJECT

    // CREATE NEW PROJECT

    // UPDATE EXISTING

    // DELETE EXISTING
}
