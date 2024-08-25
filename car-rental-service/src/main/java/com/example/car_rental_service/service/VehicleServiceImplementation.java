package com.example.car_rental_service.service;

import com.example.car_rental_service.model.Status;
import com.example.car_rental_service.model.Vehicle;
import com.example.car_rental_service.repository.VehicleRepository;
import com.example.car_rental_service.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class VehicleServiceImplementation implements VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public Vehicle create(Vehicle vehicle) {
        vehicle.setStatus(Status.AVAILABLE);
        vehicle.setOwner(null);
        vehicle.setAssociationDate(null);

        validateVehicle(String.valueOf(vehicle.getId()));
        return  vehicleRepository.save(vehicle);
    }

    @Override
    public void validateVehicle(String vehicleId) {
        vehicleRepository.findById(Long.valueOf(vehicleId)).orElseThrow();
    }

    @Override
    public void associate(String vehicleId, String userId) {
        var vehicle=vehicleRepository.findById(Long.valueOf(vehicleId))
                .filter(v->v.getStatus()==Status.AVAILABLE).orElseThrow();

        vehicle.setOwner(userId);
        vehicle.setAssociationDate(new Date());
        vehicle.setStatus(Status.ASSOCIATED);

        vehicleRepository.save(vehicle);
    }

    @Override
    public void delete(String vehicleId, String userId) {

        var vehicle=vehicleRepository.findById(Long.valueOf(vehicleId))
                .filter(v->v.getStatus()==Status.ASSOCIATED)
                .filter(v->userId.equals(v.getOwner()))
                .orElseThrow();
        vehicle.setOwner(null);
        vehicle.setAssociationDate(null);
        vehicle.setStatus(Status.AVAILABLE);

        vehicleRepository.save(vehicle);
    }
}
