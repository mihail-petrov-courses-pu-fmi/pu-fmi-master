package com.project02_solar_park_api.solarparkapi.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
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
public class Contacts extends BaseEntity{
    /*first_name VARCHAR(256),
    last_name VARCHAR(256),
    email VARCHAR(256),
    phone VARCHAR(256),*/
    @Column(name = "first_name", length = 256)
    private String firstName;
    @Column(name = "last_name", length = 256)
    private String lastName;
    private String email;
    private String phone;
    @ManyToMany(mappedBy = "contacts")
    private List<Project> projects;
}
