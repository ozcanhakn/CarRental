package com.techcareer.odev;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class IndividualCustomer extends Customer {
    private String tcKimlikNo;

    public IndividualCustomer(String name, String phoneNumber, String tcKimlikNo) {
        super(name, phoneNumber);
        if (isValidTcKimlikNo(tcKimlikNo)) {
            this.tcKimlikNo = tcKimlikNo;
        } else {
            throw new IllegalArgumentException("Gecersiz T.C. Kimlik Numarasi. T.C. Kimlik Numarasi 11 haneli olmalidir.");
        }
    }

    public String getTcKimlikNo() {
        return tcKimlikNo;
    }

    private boolean isValidTcKimlikNo(String tcKimlikNo) {
        return tcKimlikNo != null && tcKimlikNo.length() == 11 && tcKimlikNo.matches("\\d+");
    }
}
