package com.project02_solar_park_api.solarparkapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "td_projects")
@Getter
@Setter
@NoArgsConstructor
public class Project extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    private double cost;
    @ManyToMany
    @JoinTable(name="tc_project_contact",
        joinColumns=
        @JoinColumn(name="project_id", referencedColumnName="ID"),
        inverseJoinColumns=
        @JoinColumn(name="contact_id", referencedColumnName="ID")
    )
    private List<Contacts> contacts;
}
