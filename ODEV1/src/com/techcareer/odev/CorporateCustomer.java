package com.techcareer.odev;

public class CorporateCustomer extends Customer {
    private String companyName;
    private String taxNumber;

    public CorporateCustomer(String companyName, String phoneNumber, String taxNumber) {
        super(companyName, phoneNumber);
        this.companyName = companyName;
        this.taxNumber = taxNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTaxNumber() {
        return taxNumber;
    }
}
