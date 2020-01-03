package com.demo.company.service;

import com.demo.company.Company;
import com.demo.company.commonUtil.Constants;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CompanyServiceImpl implements CompanyService {

    private static List<Company> companies = new ArrayList<>();

    static {
        Company company = new Company("Deskera", 123456, "Pune India");
        companies.add(company);
    }

    @Override
    public Company retrieveCompany(String companyName) {
        for (Company company : companies) {
            if (company.getCompanyName().equalsIgnoreCase(companyName)) {
                return company;
            }
        }
        return null;
    }

    public void addCompany(Map<String, Object> requestMap) {
        String msg = Constants.EMPTY;
        Company company = new Company();
        if (requestMap.containsKey(Constants.COMPANY_NAME)) {
            company.setCompanyName((String) requestMap.get(Constants.COMPANY_NAME));
        }
        if (requestMap.containsKey(Constants.COMPANY_ID)) {
            company.setCompanyId((int) requestMap.get(Constants.COMPANY_ID));
        }
        if (requestMap.containsKey(Constants.COMPANY_ADDRESS)) {
            company.setCompanyAddress((String) requestMap.get(Constants.COMPANY_ADDRESS));
        }
        companies.add(company);
    }

    @Override
    public String deleteCompany(final String companyName) {
        String msg = Constants.EMPTY;
        Company c = companies.stream()
                .filter(companyObj -> companyObj.getCompanyName().equalsIgnoreCase(companyName))
                .findFirst()
                .orElse(null);
        if (c != null) {
            companies.remove(c);
            msg = "Company '" + companyName + "' has been deleted successfully.";
        } else {
            msg = "Company with the name '" + companyName + "' not present.";
        }
        return msg;
    }

    @Override
    public String updateCompany(Map<String, Object> requestMap) {
        String msg = Constants.EMPTY;

        String companyName = (String) requestMap.get(Constants.COMPANY_NAME);
        String companyAddress = (String) requestMap.get(Constants.COMPANY_ADDRESS);

        /**
         *  Before update Company information, need to check company is exist or not.
         */
        Company company = retrieveCompany(companyName);
        if (company != null) {
            company.setCompanyAddress(companyAddress);
            msg = "Company information update successfully.";
        } else {
            msg = "Company with name " + companyName + " not already exist. Please enter correct company name";
        }
        return msg;

    }

}
