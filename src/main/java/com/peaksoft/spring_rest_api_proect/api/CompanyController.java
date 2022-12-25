package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.CompanyRequest;
import com.peaksoft.spring_rest_api_proect.dto.CompanyResponse;
import com.peaksoft.spring_rest_api_proect.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/save")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

     @GetMapping("/all")
    public List<CompanyResponse> findAllCompanies(){
        return  companyService.findAllCompanies();
     }

     @GetMapping("/{id}")
    public CompanyResponse findByIdCompany(@PathVariable Long id) {
        return companyService.findById(id);
     }

     @DeleteMapping("/{id}")
    public CompanyResponse deleteByIdCompany(@PathVariable Long id) {
        return companyService.deleteByIdCompany(id);
     }
     @PutMapping("/{id}")
    public CompanyResponse update(@PathVariable Long id,CompanyRequest companyRequest) {
        return companyService.updateCompany(id,companyRequest);
     }
}
