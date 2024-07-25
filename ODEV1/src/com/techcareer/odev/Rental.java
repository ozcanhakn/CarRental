package com.techcareer.odev;

public class Rental {
    private Vehicle vehicle;
    private Customer customer;
    private String rentalStartDate;
    private int rentalDuration; // Gün veya ay olarak belirtilir
    private String rentalType; // Günlük veya Aylık olarak belirtilir

    public Rental(Vehicle vehicle, Customer customer, String rentalStartDate, int rentalDuration, String rentalType) {
        this.vehicle = vehicle;
        this.customer = customer;
        this.rentalStartDate = rentalStartDate;
        this.rentalDuration = rentalDuration;
        this.rentalType = rentalType;
    }

    public double calculateTotalPrice() {
        if (rentalType.equalsIgnoreCase("günlük")) { // Günlük kiralama
            return vehicle.getDailyRentalPrice() * rentalDuration;
        } else { // Aylık kiralama
            return vehicle.calculateMonthlyRentalPrice() * rentalDuration;
        }
    }

    // Yeni metod ekleniyor
    public void printRentalDetails() {
        System.out.println("Kiralama Detaylari:");
        System.out.println("Arac: " + vehicle.getBrand() + " " + vehicle.getModel());
        System.out.println("Müsteri: " + customer.getName());
        System.out.println("Telefon: " + customer.getPhoneNumber());
        System.out.println("Tarih: " + rentalStartDate);
        System.out.println("Kiralama Suresi: " + rentalDuration + (rentalType.equalsIgnoreCase("günlük") ? " gün" : " ay"));
        System.out.println("Toplam Bedel: " + calculateTotalPrice() + " TL");
    }
}
