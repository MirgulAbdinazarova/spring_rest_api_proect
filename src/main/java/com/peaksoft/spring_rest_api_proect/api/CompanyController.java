package com.peaksoft.spring_rest_api_proect.api;

import com.peaksoft.spring_rest_api_proect.dto.CompanyRequest;
import com.peaksoft.spring_rest_api_proect.dto.CompanyResponse;
import com.peaksoft.spring_rest_api_proect.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/company")
public class CompanyController {

    private final CompanyService companyService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyResponse save(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

     @GetMapping("/all")
     @PreAuthorize("isAuthenticated()")
    public List<CompanyResponse> findAllCompanies(){
        return  companyService.findAllCompanies();
     }

     @GetMapping("/{id}")
     @PreAuthorize("isAuthenticated()")
    public CompanyResponse findByIdCompany(@PathVariable Long id) {
        return companyService.findById(id);
     }

     @DeleteMapping("/{id}")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyResponse deleteByIdCompany(@PathVariable Long id) {
        return companyService.deleteByIdCompany(id);
     }
     @PutMapping("/{id}")
     @PreAuthorize("hasRole('ROLE_ADMIN')")
    public CompanyResponse update(@PathVariable Long id,
                                  @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(id,companyRequest);
     }
}
