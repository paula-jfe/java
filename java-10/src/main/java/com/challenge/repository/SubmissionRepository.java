package com.challenge.repository;

import com.challenge.entity.Submission;
import com.challenge.entity.SubmissionId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SubmissionRepository extends CrudRepository<Submission, SubmissionId> {

    @Query(value = "SELECT MAX(score) FROM submission " +
            "WHERE challenge_id = :challengeId", nativeQuery = true )
    BigDecimal findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

    @Query(value = "SELECT * FROM submission AS s " +
            "INNER JOIN acceleration AS a " +
            "ON s.challenge_id = a.challenge_id " +
            "WHERE s.challenge_id = :challengeId " +
            "AND a.id = :accelerationId", nativeQuery = true )
    List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("accelerationId") Long accelerationId);

}
