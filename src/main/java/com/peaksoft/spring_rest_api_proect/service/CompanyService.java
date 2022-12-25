package com.peaksoft.spring_rest_api_proect.service;

import com.peaksoft.spring_rest_api_proect.dto.CompanyRequest;
import com.peaksoft.spring_rest_api_proect.dto.CompanyResponse;

import java.util.List;

public interface CompanyService {

    CompanyResponse saveCompany(CompanyRequest companyRequest);

    List<CompanyResponse> findAllCompanies();

    CompanyResponse findById(Long id);

    CompanyResponse deleteByIdCompany(Long id);

    CompanyResponse updateCompany(Long id,CompanyRequest companyRequest);

}
