package com.techcareer.odev;

import java.util.List;
import java.util.stream.Collectors;

import java.util.List;
import java.util.stream.Collectors;

public class VehicleRentalService {
    private List<Vehicle> vehicles;

    public VehicleRentalService(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Vehicle> listVehicles(Customer customer) {
        if (customer instanceof IndividualCustomer) {
            return vehicles.stream()
                    .filter(vehicle -> vehicle instanceof Sedan || vehicle instanceof Hatchback)
                    .collect(Collectors.toList());
        } else {
            return vehicles;
        }
    }
}
