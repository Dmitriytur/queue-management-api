package ua.nure.queuemanagementapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.nure.queuemanagementapi.converter.service.ExtendedConversionService;
import ua.nure.queuemanagementapi.dto.UserDto;
import ua.nure.queuemanagementapi.entity.CategoryEntity;
import ua.nure.queuemanagementapi.entity.CompanyEntity;
import ua.nure.queuemanagementapi.entity.Role;
import ua.nure.queuemanagementapi.repository.CompanyRepository;
import ua.nure.queuemanagementapi.repository.UserRepository;
import ua.nure.queuemanagementapi.util.CategoriesUtils;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExtendedConversionService conversionService;

    @PostMapping
    public String add(@RequestBody CompanyEntity companyEntity) {
        return companyRepository.save(companyEntity).getId();
    }

    @PutMapping("/{companyId}")
    public void update(@PathVariable String companyId, @RequestBody CompanyEntity company) {
        Optional<CompanyEntity> byId = companyRepository.findById(companyId);
        byId.ifPresent(c -> {
            c.setDescription(company.getDescription());
            c.setName(company.getName());
            companyRepository.save(c);
        });
    }

    @PutMapping("/{companyId}/root-category")
    public void update(@PathVariable String companyId, @RequestBody CategoryEntity categoryEntity) {
        CategoriesUtils.initCategoriesTree(categoryEntity);
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

    @GetMapping("/{companyId}/managers")
    public List<UserDto> getManagers(@PathVariable String companyId) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setId(companyId);
        return conversionService.convertAll(userRepository.findByCompanyAndRoleNot(companyEntity, Role.ADMIN), UserDto.class);
    }

}
