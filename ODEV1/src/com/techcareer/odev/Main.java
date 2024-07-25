package com.techcareer.odev;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static String formattedNow;
    private static Customer customer;
    private static String customerType;
    private static String phoneNumber;
    private static String name;
    private static String tcKimlikNo;

    private static String companyName;
    private static String taxNumber;

    private static String vehicleType;
    private static String rentalType;

    private static Vehicle vehicle;

    private static int vehicleChoice;

    private static Vehicle selectedVehicle;

    private static int rentalDuration;

    private static List<Vehicle> availableVehicles;

    private static VehicleRentalService vehicleRentalService;
    private static Rental rental;

    private static double totalPrice;

    private static String confirmation;
    private static List<Vehicle> vehicles;

    public static void main(String[] args) throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(new InputStreamReader(System.in, "UTF-8"));

        getCurrentTime();
        vehicles = createVehicleList();

        // Kullanıcı tipini alma
        System.out.print("Bireysel müsteri misiniz yoksa sirket mi? (bireysel/şirket): ");
        customerType = scanner.nextLine();

        // Müşteri tipine göre ilgili soruları yöneltme
        if (customerType.equalsIgnoreCase("bireysel")) {
            handleIndividualCustomer(scanner);
        } else if (customerType.equalsIgnoreCase("şirket")) {
            handleCorporateCustomer(scanner);
        } else {
            System.out.println("Gecersiz müsteri tipi.");
            scanner.close();
            return;
        }

        vehicleRentalService = new VehicleRentalService(vehicles);
        availableVehicles = vehicleRentalService.listVehicles(customer);

        // Bireysel müşteri ise SUV seçeneğini kaldırma
        if (customer instanceof IndividualCustomer) {
            availableVehicles = filterVehiclesByType(availableVehicles, "Sedan", "Hatchback");
        }

        // Kiralamak istenilen araç tipi sorma
        System.out.print("Kiralama yapmak istediginiz arac tipi nedir? (Sedan/Hatchback/SUV): ");
        vehicleType = scanner.nextLine();

        if (customer instanceof IndividualCustomer && vehicleType.equalsIgnoreCase("SUV")) {
            System.out.println("Bireysel müsteriler SUV arac kiralayamaz.");
            scanner.close();
            return;
        }

        availableVehicles = filterVehiclesByType(availableVehicles, vehicleType);

        if (availableVehicles.isEmpty()) {
            System.out.println("Sectiginiz tipte araç bulunmamaktadır.");
            scanner.close();
            return;
        }

        // Kiralama şekli sorma
        System.out.print("Kiralama sekli nedir? (günlük/aylık): ");
        rentalType = scanner.nextLine();

        if (rentalType.equalsIgnoreCase("aylık") && vehicleType.equalsIgnoreCase("SUV")) {
            System.out.println("SUV araclar sadece günlük kiralanabilir.");
            scanner.close();
            return;
        }

        // Kullanıcıya kiralanabilir modeller gösterme
        System.out.println("Mevcut araclar:");
        for (int i = 0; i < availableVehicles.size(); i++) {
            vehicle = availableVehicles.get(i);
            System.out.println((i + 1) + ". " + vehicle.toString());
        }

        // Kullanıcıdan araç seçimi isteme
        System.out.print("Kiralamak istediginiz aracin numarasini giriniz: ");
        vehicleChoice = scanner.nextInt();

        if (vehicleChoice < 1 || vehicleChoice > availableVehicles.size()) {
            System.out.println("Gecersiz secim.");
            scanner.close();
            return;
        }

        selectedVehicle = availableVehicles.get(vehicleChoice - 1);

        // Kiralama süresi sorma
        if (rentalType.equalsIgnoreCase("günlük")) {
            System.out.print("Kaç gün kiralamak istiyorsunuz?: ");
        } else {
            System.out.print("Kaç ay kiralamak istiyorsunuz?: ");
        }
        rentalDuration = scanner.nextInt();

        rental = new Rental(selectedVehicle, customer, formattedNow, rentalDuration, rentalType);

        // Toplam fiyat hesaplama ve gösterme
        try {
            totalPrice = rental.calculateTotalPrice();
            System.out.println("Toplam kiralama bedeli: " + totalPrice + "TL");
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        // Kullanıcıdan onay alma
        System.out.print("Kiralama islemini onayliyor musunuz? (evet/hayır): ");
        confirmation = scanner.next();

        if (confirmation.equalsIgnoreCase("evet")) {
            System.out.println("Arac basariyla kiralandi!");
            rental.printRentalDetails(); // Kiralama detaylarını yazdır
        } else {
            System.out.println("Kiralama islemi iptal edildi.");
        }

        scanner.close();
    }

    private static void getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        formattedNow = now.format(formatter);
    }

    private static List<Vehicle> createVehicleList() {
        List<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Sedan("Toyota", "Camry", "E", "Otomatik", 500, "Beyaz", 2, "Benzin", 800));
        vehicles.add(new Sedan("Mercedes", "200D", "E", "Otomatik", 500, "Siyah", 1, "Benzin", 2400));
        vehicles.add(new Sedan("BMW", "5.20", "E", "Otomatik", 500, "Siyah", 2, "Dizel", 1950));
        vehicles.add(new Sedan("Renault", "Megane", "C", "Otomatik", 500, "Beyaz", 3, "Benzin", 800));
        vehicles.add(new Sedan("Audi", "A5", "E", "Otomatik", 500, "Beyaz", 0, "Benzin", 2450));
        vehicles.add(new Hatchback("Dacia", "Sandero", "B", "Manuel", 410, "Mavi", 2, "Benzin", 2000, 0.85));
        vehicles.add(new Hatchback("Ford", "Focus", "C", "Manuel", 500, "Kırmızı", 2, "Dizel", 2000, 0.85));
        vehicles.add(new Hatchback("Opel", "Astra", "C", "Manuel", 500, "Mavi", 3, "Benzin", 2000, 0.65));
        vehicles.add(new Hatchback("Renault", "Clio", "B", "Otomatik", 391, "Siyah", 2, "Benzin", 2000, 0.85));
        vehicles.add(new Hatchback("Honda", "Jazz", "B", "Otomatik", 305, "Kırmızı", 1, "Benzin", 2000, 1));
        vehicles.add(new SUV("Jeep", "Wrangler", "SUV", "Otomatik", 600, "Siyah", 1, "Benzin", 1200));
        vehicles.add(new SUV("Audi", "Q5", "D", "Otomatik", 550, "Siyah", 3, "Dizel", 3500));
        vehicles.add(new SUV("BMW", "X5", "D", "Otomatik", 550, "Siyah", 6, "Dizel", 2400));
        vehicles.add(new SUV("Chery", "Tiggo", "D", "Otomatik", 600, "Gri", 2, "Benzin", 2950));
        vehicles.add(new SUV("Land Rover", "Range Rover Sport", "F", "Otomatik", 450, "Beyaz", 6, "Dizel", 2500));
        return vehicles;
    }

    private static void handleIndividualCustomer(Scanner scanner) {
        System.out.print("Adiniz Soyadiniz: ");
        name = scanner.nextLine();
        System.out.print("Telefon Numaraniz: ");
        phoneNumber = scanner.nextLine();

        while (true) {
            System.out.print("T.C. Kimlik Numaraniz: ");
            tcKimlikNo = scanner.nextLine();
            try {
                customer = new IndividualCustomer(name, phoneNumber, tcKimlikNo);
                break; // Geçerli T.C. kimlik numarası girildiğinde döngüden çık
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleCorporateCustomer(Scanner scanner) {
        System.out.print("Sirket Adi: ");
        companyName = scanner.nextLine();
        System.out.print("Telefon Numaraniz: ");
        phoneNumber = scanner.nextLine();
        System.out.print("Vergi Numaraniz: ");
        taxNumber = scanner.nextLine();
        customer = new CorporateCustomer(companyName, phoneNumber, taxNumber);
    }

    private static List<Vehicle> filterVehiclesByType(List<Vehicle> vehicles, String... types) {
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            for (String type : types) {
                if (type.equalsIgnoreCase("Sedan") && vehicle instanceof Sedan) {
                    filteredVehicles.add(vehicle);
                } else if (type.equalsIgnoreCase("Hatchback") && vehicle instanceof Hatchback) {
                    filteredVehicles.add(vehicle);
                } else if (type.equalsIgnoreCase("SUV") && vehicle instanceof SUV) {
                    filteredVehicles.add(vehicle);
                }
            }
        }
        return filteredVehicles;
    }
}
