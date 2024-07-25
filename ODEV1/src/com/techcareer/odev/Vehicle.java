package com.techcareer.odev;

public abstract class Vehicle {
    private String brand;
    private String model;
    private String segment;
    private String transmissionType;
    private int luggageCapacity;
    private String color;
    private int age;
    private String fuelType;
    private double dailyRentalPrice;

    public Vehicle(String brand, String model, String segment, String transmissionType, int luggageCapacity,
                   String color, int age, String fuelType, double dailyRentalPrice) {
        this.brand = brand;
        this.model = model;
        this.segment = segment;
        this.transmissionType = transmissionType;
        this.luggageCapacity = luggageCapacity;
        this.color = color;
        this.age = age;
        this.fuelType = fuelType;
        this.dailyRentalPrice = dailyRentalPrice;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getSegment() {
        return segment;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public int getLuggageCapacity() {
        return luggageCapacity;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getDailyRentalPrice() {
        return dailyRentalPrice;
    }

    public abstract double calculateMonthlyRentalPrice();

    @Override
    public String toString() {
        return brand + " " + model + " (" + segment + " sinifi"+", " + transmissionType + ", " + color + ", " + fuelType +
                ", " + luggageCapacity + "L, " + age + " yasinda)";
    }
}
