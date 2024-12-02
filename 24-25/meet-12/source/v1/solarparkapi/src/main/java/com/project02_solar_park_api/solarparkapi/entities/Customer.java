package com.project02_solar_park_api.solarparkapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "td_customers")
@Getter
@Setter
@NoArgsConstructor
public class Customer extends BaseEntity {

    private String name;

    @Column(name = "number_of_projects")
    private int numberOfProjects;

    @OneToMany(mappedBy = "customer")
    private List<Project> projects;

}
