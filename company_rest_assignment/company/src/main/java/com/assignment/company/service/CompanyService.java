/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.company.service;

import com.assignment.entity.Company;
import java.util.Map;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author krawler
 */
public interface CompanyService {

    public String addCompany(Map<String, Object> requestParams);

    public Company getCompany(final String name);

    public String deleteCompany(final String name);

    public String updateCompany(final String name, final String address);
}
