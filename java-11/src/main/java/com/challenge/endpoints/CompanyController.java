package com.challenge.endpoints;

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/{id}")
    public Optional<Company> findById(@PathVariable("id") Long id) {
        return this.companyService.findById(id);
    }

    @GetMapping
    public List<Company> findByAccelerationId(
        @RequestParam(required = false) Long accelerationId, @RequestParam(required = false) Long userId) {
            if (accelerationId != null) {
                return this.companyService.findByAccelerationId(accelerationId);
            }
            return this.companyService.findByUserId(userId);
    }

}
