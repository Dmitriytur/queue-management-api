package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.CompanyEntity;
import ua.nure.queuemanagementapi.repository.CompanyRepository;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping
    public String add(@RequestBody CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity).getId();
    }

    @PutMapping("/{companyId}/root-category")
    public void update(@PathVariable String companyId, @RequestBody CategoryEntity categoryEntity) {
        Optional<CompanyEntity> company = companyRepository.findById(companyId);
        company.ifPresent(c -> {
            c.setRootCategory(categoryEntity);
            companyRepository.save(c);
        });
    }

    @GetMapping("/{companyId}")
    public CompanyEntity findById(@PathVariable String companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

}
