package com.demo.company.service;

import com.demo.company.Company;
import java.util.Map;

public interface CompanyService {
    public Company retrieveCompany(String companyName);
    public void addCompany(Map<String, Object> requestMap);
    public String deleteCompany(String name);
    public String updateCompany(Map<String, Object> requestMap);
}
