package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query(value = "SELECT * FROM users AS u " +
            "INNER JOIN candidate AS c " +
            "ON u.id = c.user_id " +
            "INNER JOIN acceleration AS a " +
            "ON a.id = c.acceleration_id " +
            "WHERE a.name = :name", nativeQuery = true)
    List<User> findByAccelerationName(@Param("name") String name);

    @Query(value = "SELECT * FROM users AS u " +
            "INNER JOIN candidate AS c " +
            "ON u.id = c.user_id " +
            "INNER JOIN company AS co " +
            "ON c.company_id = co.id " +
            "WHERE co.id = :companyId", nativeQuery = true)
    List<User> findByCompanyId(@Param("companyId") Long companyId);

}
