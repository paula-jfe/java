package com.challenge.endpoints;

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

    @Autowired
    private CandidateService candidateService;

    @Autowired
    private CandidateMapper candidateMapper;

    @GetMapping("/{userId}/{accelerationId}/{companyId}")
    public ResponseEntity<CandidateDTO> findById(
            @PathVariable("userId") Long userId,
            @PathVariable("accelerationId") Long accelerationId,
            @PathVariable("companyId") Long companyId) {
        Optional<Candidate> candidate = this.candidateService.findById(userId, companyId, accelerationId);
        return candidate.map(value -> new ResponseEntity<>(this.candidateMapper.map(value), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NO_CONTENT));
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
