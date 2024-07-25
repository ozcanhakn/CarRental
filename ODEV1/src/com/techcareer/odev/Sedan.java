package com.techcareer.odev;

public class Sedan extends Vehicle {
    public Sedan(String brand, String model, String segment, String transmissionType, int luggageCapacity, String color,
                 int age, String fuelType, double dailyRentalPrice) {
        super(brand, model, segment, transmissionType, luggageCapacity, color, age, fuelType, dailyRentalPrice);
    }

    @Override
    public double calculateMonthlyRentalPrice() {
        return getDailyRentalPrice() * 30 * 0.93; // %7 indirim
    }
}
