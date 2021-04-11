package com.challenge.repository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CandidateRepository extends CrudRepository<Candidate, CandidateId> {

    @Query(value = "SELECT * FROM candidate AS c " +
            "INNER JOIN acceleration AS a " +
            "ON a.id = c.acceleration_id " +
            "WHERE a.id = :accelerationId", nativeQuery = true)
    List<Candidate> findByAccelerationId(@Param("accelerationId")Long accelerationId);

    @Query(value = "SELECT * FROM candidate AS c " +
            "INNER JOIN company AS co " +
            "ON co.id = c.company_id " +
            "WHERE co.id = :companyId", nativeQuery = true)
    List<Candidate> findByCompanyId(@Param("companyId")Long companyId);

}
