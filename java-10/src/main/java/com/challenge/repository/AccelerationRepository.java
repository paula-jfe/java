package com.challenge.repository;

import com.challenge.entity.Acceleration;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AccelerationRepository extends CrudRepository<Acceleration, Long> {

    @Query(value = "SELECT * FROM acceleration AS a " +
            "INNER JOIN candidate AS c " +
            "ON a.id = c.acceleration_id " +
            "WHERE c.company_id = :companyId", nativeQuery = true)
    List<Acceleration> findByCompanyId(@Param("companyId") Long companyId);

}
