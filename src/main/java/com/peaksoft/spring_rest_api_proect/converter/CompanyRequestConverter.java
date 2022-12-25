package com.peaksoft.spring_rest_api_proect.converter;

import com.peaksoft.spring_rest_api_proect.dto.CompanyRequest;
import com.peaksoft.spring_rest_api_proect.entities.Company;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CompanyRequestConverter {

    public Company create(CompanyRequest companyRequest) {
        if(companyRequest==null) {
            return null;
        }
        Company company=new Company();
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
//        company.setCourses(companyRequest.getCourses());
        return  company;
    }

    public void update(Company company, CompanyRequest companyRequest) {
        company.setCompanyName(companyRequest.getCompanyName());
        company.setLocatedCountry(companyRequest.getLocatedCountry());
//        company.setCourses(companyRequest.getCourses());
    }
}
