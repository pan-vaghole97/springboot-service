package com.demo.company.controller;

import com.demo.company.Company;
import com.demo.company.commonUtil.Constants;
import com.demo.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    private static final Logger logger = Logger.getLogger(CompanyController.class.getName());

    @GetMapping("/getCompanyList")
    public Object getCompanyList(@RequestParam(Constants.COMPANY_NAME) String companyName) {
        String msg = "Company Information Not Found. Please Enter valid Company Name.";
        try {
            Company company = companyService.retrieveCompany(companyName);
            if (company != null) {
                return company;
            }
        } catch (Exception ex) {
            msg = "Failed to Search the Company. Please Retry." + ex.getMessage();
            logger.log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @PostMapping("/addCompany")
    public String addCompany(@RequestParam(Constants.COMPANY_NAME) String companyName, @RequestParam(Constants.COMPANY_ID) int companyId, @RequestParam(Constants.COMPANY_ADDRESS) String companyAddress) {
        String msg = Constants.EMPTY;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put(Constants.COMPANY_NAME, companyName);
            paramsMap.put(Constants.COMPANY_ID, companyId);
            paramsMap.put(Constants.COMPANY_ADDRESS, companyAddress);
            /**
             *  Before add New Company, please check company is already exist or not.
             */
            Company company = companyService.retrieveCompany(companyName);
            if (company != null) {
                msg = "Company with name " + companyName + " already exist. Please enter different company name.";
            } else {
                companyService.addCompany(paramsMap);
                msg = "New Company " + companyName + " Added Successfully.";
            }
        } catch (Exception ex) {
            msg = "Failed to Add New Company. Please try again.";
            logger.log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @DeleteMapping("/deleteCompany")
    public String deleteCompany(@RequestParam(Constants.COMPANY_NAME) String companyName) {
        String msg = Constants.EMPTY;
        try {
            msg = companyService.deleteCompany(companyName);
        } catch (Exception ex) {
            msg = "Failed to delete the Company. Please try again.";
            logger.log(Level.SEVERE, null, ex);
        }
        return msg;
    }

    @PutMapping("/updateCompany")
    public String updateCompany(@RequestParam(Constants.COMPANY_NAME) final String companyName, @RequestParam(Constants.COMPANY_ADDRESS) final String companyAddress) {
        String msg = Constants.EMPTY;
        try {
            Map<String, Object> paramsMap = new HashMap<>();
            paramsMap.put(Constants.COMPANY_NAME, companyName);
            paramsMap.put(Constants.COMPANY_ADDRESS, companyAddress);
            msg = companyService.updateCompany(paramsMap);
        } catch (Exception ex) {
            msg = ex.getMessage();
            logger.log(Level.SEVERE, null, ex);
        }
        return msg;
    }

}
