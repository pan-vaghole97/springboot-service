/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.company.service;

import com.assignment.entity.Company;
import com.assignment.utils.Constants;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/**
 *
 * @author krawler
 */
@Service
public class CompanyServiceImpl implements CompanyService {

    List<Company> companyList = new ArrayList<>();

    @Override
    public String addCompany(Map<String, Object> requestParams) {
        String msg = Constants.EMPTY;
        String name = (String) requestParams.get(Constants.NAME);
        Company company = new Company();
        if (requestParams.containsKey(Constants.ID)) {
            company.setId(Integer.parseInt((String) requestParams.get(Constants.ID)));
        }
        if (requestParams.containsKey(Constants.NAME)) {
            company.setName(name);
        }
        if (requestParams.containsKey(Constants.ADDRESS)) {
            company.setAddress((String) requestParams.get(Constants.ADDRESS));
        }

        Company c = companyList.stream()
                .filter(companyObj -> companyObj.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (c == null) {
            companyList.add(company);
            msg = "Company added successfully";
        } else {
            msg = "Company already present";
        }
        return msg;
    }

    @Override
    public Company getCompany(final String name) {
        Company c = companyList.stream()
                .filter(company -> company.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        return c;
    }

    @Override
    public String deleteCompany(final String name) {
        String msg = Constants.EMPTY;
        Company c = companyList.stream()
                .filter(companyObj -> companyObj.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (c != null) {
            companyList.remove(c);
            msg = "Company deleted successfully.";
        } else {
            msg = "Company name not present for delete.";
        }
        return msg;
    }

    @Override
    public String updateCompany(final String name, final String address) {
        String msg = Constants.EMPTY;
        Company c = companyList.stream()
                .filter(companyObj -> companyObj.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
        if (c != null) {
            c.setAddress(address);
            msg = "Company address updated successfully.";
        } else {
            msg = "Company name not present for updating.";
        }
        return msg;
    }

}
