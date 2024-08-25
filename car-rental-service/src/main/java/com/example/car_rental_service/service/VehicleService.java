package com.example.car_rental_service.service;

import com.example.car_rental_service.model.Vehicle;

public interface VehicleService {

     Vehicle create(Vehicle vehicle);

     void validateVehicle(String vehicleId);

     void associate(String vehicleId,String userId);

     void delete(String vehicleId,String userId);


}
