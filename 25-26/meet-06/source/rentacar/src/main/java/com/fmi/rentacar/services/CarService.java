package com.fmi.rentacar.services;

import com.fmi.rentacar.models.CarModel;
import com.fmi.rentacar.repositories.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public void addNewCar(CarModel model) {

        // 1. Price calculation
        // 2. Notify customers
        // 3. Add to database
        this.carRepository.createCar(model);
    }
}
