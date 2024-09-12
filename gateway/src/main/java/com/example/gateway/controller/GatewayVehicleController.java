package com.example.gateway.controller;

import com.example.gateway.model.Vehicle;
import com.example.gateway.proxy.VehicleProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class GatewayVehicleController {

    @Autowired
    private VehicleProxy vehicleProxy;

    @PostMapping
    public void create(@RequestBody Vehicle vehicle){
        vehicleProxy.create(vehicle);
    }

    @DeleteMapping("{vehicleId}/users/{userId}")
    public void remove(@PathVariable(name="vehicleId") String vehicleId,
                       @PathVariable(name="userId") String userId){

        vehicleProxy.removeAssociation(vehicleId,userId);

    }

}
