package com.techcareer.odev;

public class Hatchback extends Vehicle {
    private double segmentCoefficient;

    public Hatchback(String brand, String model, String segment, String transmissionType, int luggageCapacity, String color,
                     int age, String fuelType, double dailyRentalPrice, double segmentCoefficient) {
        super(brand, model, segment, transmissionType, luggageCapacity, color, age, fuelType, dailyRentalPrice);
        this.segmentCoefficient = segmentCoefficient;
    }

    @Override
    public double calculateMonthlyRentalPrice() {
        return getDailyRentalPrice() * 30 * segmentCoefficient;
    }
}
