package com.fmi.rentacar.repositories;

import com.fmi.rentacar.models.CarModel;
import com.fmi.rentacar.system.db.QueryBuilder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class CarRepository {

    private QueryBuilder qb;

    public CarRepository(QueryBuilder qb) {
        this.qb = qb;
    }

    // Insert car into database
    public void createCar(CarModel model) {

        // this.jdbcTemplate.execute("INSERT INTO td_cars() VALUES()");
        this.qb.into(CarModel.TABLE)
                .withValue(CarModel.columns.TITLE, model.getTitle())
                .withValue(CarModel.columns.MODEL, model.getModel())
                .insert();

        // логика за пахане на данни в базата
    }

}
