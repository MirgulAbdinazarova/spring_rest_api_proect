package com.peaksoft.spring_rest_api_proect.service.impl;

import com.peaksoft.spring_rest_api_proect.converter.CompanyRequestConverter;
import com.peaksoft.spring_rest_api_proect.converter.CompanyResponseConverter;
import com.peaksoft.spring_rest_api_proect.dto.CompanyRequest;
import com.peaksoft.spring_rest_api_proect.dto.CompanyResponse;
import com.peaksoft.spring_rest_api_proect.entities.Company;
import com.peaksoft.spring_rest_api_proect.repo.CompanyRepository;
import com.peaksoft.spring_rest_api_proect.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl  implements CompanyService {

    private final CompanyRepository companyRepository;
    private final CompanyResponseConverter companyResponseConverter;
    private final CompanyRequestConverter companyRequestConverter;

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
      Company company=  companyRequestConverter.create(companyRequest);
      companyRepository.save(company);
        return companyResponseConverter.viewCompany(company);
    }

    @Override
    public List<CompanyResponse> findAllCompanies() {
        return companyResponseConverter.view(companyRepository.findAll());
    }

    @Override
    public CompanyResponse findById(Long id) {
      Company company=  companyRepository.findById(id).get();
        return  companyResponseConverter.viewCompany(company);
    }

    @Override
    public CompanyResponse deleteByIdCompany(Long id) {
      Company company=  companyRepository.findById(id).get();
      companyRepository.delete(company);
        return companyResponseConverter.viewCompany(company);
    }

    @Override
    public CompanyResponse updateCompany(Long id, CompanyRequest companyRequest) {
      Company company=  companyRepository.findById(id).get();
      companyRequestConverter.update(company,companyRequest);
        return companyResponseConverter.viewCompany(companyRepository.save(company));
    }
}
