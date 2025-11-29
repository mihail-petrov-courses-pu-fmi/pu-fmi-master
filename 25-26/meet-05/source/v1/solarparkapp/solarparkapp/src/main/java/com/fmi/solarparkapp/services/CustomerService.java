package com.fmi.solarparkapp.services;

import com.fmi.solarparkapp.models.base.CustomerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.util.List;


@Service
public class CustomerService {

    private JdbcTemplate jdbcTemplate;

    public CustomerService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CustomerModel> fetchAllCustomers() {

        String sql = "select * from td_customers WHERE is_active = 1";
        return this.collectCustomers(sql);
    }

    public CustomerModel fetchCustomerById(int id) {

        String sql = "select * from td_customers WHERE id = " + id + " AND is_active = 1";
        var collection =  this.collectCustomers(sql);

        if(collection.isEmpty()) {
            return null;
        }

        return (CustomerModel) collection.get(0);
    }

    public boolean createNewCustomer(CustomerModel model) {


        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO td_customers");
        sb.append("(name, number_of_projects)");
        sb.append("VALUES ('");
        sb.append(model.getName()).append("',");
        sb.append(model.getNumberOfProjects());
        sb.append(");");

        String query = sb.toString();
        this.jdbcTemplate.execute(query);
        return true;
    }

    public boolean updateCustomer(CustomerModel model) {

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE td_customers SET ");
        sb.append("name = '").append(model.getName()).append("', ");
        sb.append("number_of_projects = '").append(model.getNumberOfProjects()).append("'");
        sb.append("WHERE id = ").append(model.getId()).append(";");

        String query = sb.toString();
        this.jdbcTemplate.update(query);
        return true;
    }

    public boolean deleteCustomer(int id) {

        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE td_customers SET ");
        sb.append("is_active = 0");
        sb.append("WHERE id = '").append(id).append("'");

        String query = sb.toString();
        this.jdbcTemplate.update(query);
        return true;
    }

    private List<CustomerModel> collectCustomers(String query) {

        return this.jdbcTemplate.query(query, (rs, rowNum) -> {

            // името на клиента от колона NAME в таблицата
            String customerName     = rs.getString("name");
            int id                  = rs.getInt("id");
            int numberOfProjects    = rs.getInt("number_of_projects");

            CustomerModel customerModel = new CustomerModel();
            customerModel.setId(id);
            customerModel.setName(customerName);
            customerModel.setNumberOfProjects(numberOfProjects);

            return customerModel;
        });
    }
}
