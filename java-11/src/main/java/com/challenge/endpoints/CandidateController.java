package com.challenge.endpoints;

import com.challenge.entity.Candidate;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public Optional<Candidate> findById(
            @PathVariable("userId") Long userId,
            @PathVariable("accelerationId") Long accelerationId,
            @PathVariable("companyId") Long companyId) {
        return this.candidateService.findById(userId, companyId, accelerationId);
    }

    @GetMapping
    public List<Candidate> findByCompanyIdOrFindByAccelerationId(
            @RequestParam(required = false) Long companyId, @RequestParam(required = false) Long accelerationId) {
        if (companyId != null) {
            return this.candidateService.findByCompanyId(companyId);
        }
        return this.candidateService.findByAccelerationId(accelerationId);
    }

}
