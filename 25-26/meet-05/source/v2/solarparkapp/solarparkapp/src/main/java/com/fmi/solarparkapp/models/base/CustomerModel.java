package com.fmi.solarparkapp.models.base;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class CustomerModel {

    private int id;

    @NotNull(message="Name is required")
    private String name;

    @Min(value = 0)
    @Max(value = 100)
    private int numberOfProjects;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfProjects() {
        return numberOfProjects;
    }

    public void setNumberOfProjects(int numberOfProjects) {
        this.numberOfProjects = numberOfProjects;
    }
}
