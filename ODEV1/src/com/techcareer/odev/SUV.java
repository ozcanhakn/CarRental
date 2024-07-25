package com.techcareer.odev;

public class SUV extends Vehicle {
    public SUV(String brand, String model, String segment, String transmissionType, int luggageCapacity, String color,
               int age, String fuelType, double dailyRentalPrice) {
        super(brand, model, segment, transmissionType, luggageCapacity, color, age, fuelType, dailyRentalPrice);
    }

    @Override
    public double calculateMonthlyRentalPrice() {
        throw new UnsupportedOperationException("SUV araclar sadece gunluk kiralanabilir.");
    }
}

