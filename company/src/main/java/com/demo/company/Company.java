package com.demo.company;

public class Company {
    String companyName;
    int companyId;
    String companyAddress;

    public Company() {

    }

    public Company(String companyName, int companyId, String companyAddress) {
        super();
        this.companyName = companyName;
        this.companyId = companyId;
        this.companyAddress = companyAddress;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }
}
