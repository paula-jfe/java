package com.challenge.repository;

import com.challenge.entity.Acceleration;
import com.challenge.entity.Company;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CompanyRepository extends CrudRepository<Company, Long> {

    @Query(value = "SELECT * FROM company AS c " +
            "INNER JOIN candidate AS ca " +
            "ON c.id = ca.company_id " +
            "WHERE ca.acceleration_id = :accelerationId LIMIT 1", nativeQuery = true )
    List<Company> findByAccelerationId(@Param("accelerationId") Long accelerationId);

    @Query(value = "SELECT * FROM company AS c " +
            "INNER JOIN candidate AS ca " +
            "ON c.id = ca.company_id " +
            "WHERE ca.user_id = :userId", nativeQuery = true )
    List<Company> findByUserId(@Param("userId") Long userId);

}
