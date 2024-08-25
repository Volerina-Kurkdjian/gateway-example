package com.example.car_rental_service.controller;


import com.example.car_rental_service.model.Vehicle;
import com.example.car_rental_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1")
public class VehicleController {

    @Autowired
    private VehicleService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Vehicle create(@RequestBody Vehicle vehicle){//@RequestBody means this Vehicle is going to be in JSON representation in the request
      return  service.create(vehicle);
    }

    @PostMapping("{vehicleId}/users/{userId}")
    public void associate(@PathVariable(name="vehicleId") String vehicleId, @PathVariable(name="userId") String userId){
        service.validateVehicle(vehicleId);
        service.associate(vehicleId, userId);
    }

    @DeleteMapping("{vehicleId}/users/{userId}")
    public void delete(@PathVariable(name="vehicleId") String vehicleId, @PathVariable(name="userId") String userId){
        service.validateVehicle(vehicleId);
        service.delete(vehicleId, userId);
    }




}
