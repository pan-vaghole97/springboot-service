/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.assignment.company.controller;

import com.assignment.company.service.CompanyService;
import com.assignment.entity.Company;
import com.assignment.utils.Constants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author krawler
 */
@RestController
public class CompanyController {
    @Autowired
    public CompanyService companyService;
    
    @PostMapping("/addCompany")
    public String addCompany(@RequestParam(Constants.ID) String id, @RequestParam(Constants.NAME) String name, @RequestParam(Constants.ADDRESS) String address) {
        String msg = Constants.EMPTY;
        try {
            Map<String, Object> requestParams = new HashMap<>();
            requestParams.put(Constants.ID, id);
            requestParams.put(Constants.NAME, name);
            requestParams.put(Constants.ADDRESS, address);
            msg=companyService.addCompany(requestParams);
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
        } 
        return msg;
    }
    
    @GetMapping("/getCompany")
    public Object getCompany(@RequestParam(Constants.NAME) String name) {
        String msg = Constants.EMPTY;
        try {
            Company company = companyService.getCompany(name);
           if(company!=null){
               return company;
           }else{
               msg="Company name not found" ;
           }
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
        }
        return msg;
    }
    
    @PutMapping("/updateCompany")
    public String updateCompany(@RequestParam(Constants.NAME) final String name,@RequestParam(Constants.ADDRESS) final String address) {
        String msg = Constants.EMPTY;
        try {
            msg=companyService.updateCompany(name,address);
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
        } 
        return msg;
    }
    
    @DeleteMapping("/deleteCompany")
    public String deleteCompany(@RequestParam(Constants.NAME) final String name) {
        String msg = Constants.EMPTY;
        try {
            msg=companyService.deleteCompany(name);
        } catch (Exception ex) {
            Logger.getLogger(CompanyController.class.getName()).log(Level.SEVERE, null, ex);
            msg = ex.getMessage();
        } 
        return msg;
    }
    
}
