package com.example.gateway.proxy;

import com.example.gateway.model.Vehicle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class VehicleProxy {

    private final RestTemplate restTemplate;
    private final String urlV;

    public VehicleProxy(RestTemplate restTemplate,@Value("${vehicle.urlV}") String  urlV) {
        this.restTemplate = restTemplate;
        this.urlV = urlV;
    }

    public Vehicle[] getVehicles(){
        return restTemplate.getForObject(urlV,Vehicle[].class);
    }

    public void create(Vehicle vehicle){
        restTemplate.postForObject(urlV,vehicle,Vehicle.class);

    }

    public void removeAssociation(String vehicleId, String userId){
        restTemplate.delete(urlV+"{vehicleId}/users/{userId}", Map.of("vehicleId",vehicleId,
                "userId",userId));
    }

}
