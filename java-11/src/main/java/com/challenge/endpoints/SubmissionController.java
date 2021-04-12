package com.challenge.endpoints;

import com.challenge.entity.Submission;
import com.challenge.service.impl.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/submission")
public class SubmissionController {

    @Autowired
    private SubmissionService submissionService;

    @GetMapping
    public List<Submission> findByChallengeIdAndAccelerationId(
            @RequestParam Long challengeId, @RequestParam Long accelerationId) {
        return this.submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId);
    }

}
