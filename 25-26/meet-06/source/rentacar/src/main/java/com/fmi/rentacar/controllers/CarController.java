package com.fmi.rentacar.controllers;

import com.fmi.rentacar.http.AppResponse;
import com.fmi.rentacar.models.CarModel;
import com.fmi.rentacar.services.CarService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public ResponseEntity<?> createNewCar(
            @Valid @RequestBody CarModel model
    ) {
        this.carService.addNewCar(model);

        return AppResponse.success()
                .withMessage("Car created")
                .send();
    }
}