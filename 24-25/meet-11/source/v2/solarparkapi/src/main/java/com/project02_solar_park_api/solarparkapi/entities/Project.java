package com.project02_solar_park_api.solarparkapi.entities;

public class Project {

    public static final String TABLE = "td_projects";

    public static class columns {

        public static final String IS_ACTIVE            = "is_active";
        public static final String ID                   = "id";
        public static final String CUSTOMER_ID          = "customer_id";

        public static final String NAME                 = "name";
        public static final String COST                 = "cost";
    }

    private int id;
    private String name;
    private int customerId;
    private double cost;

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

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
