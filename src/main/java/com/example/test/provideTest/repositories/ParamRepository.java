package com.example.test.provideTest.repositories;

import com.example.test.provideTest.domain.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Param Repository
 *
 * Interface to handle the database interactions related to Param entity.
 * @author Manuel Pimentel
 */
@Repository
public interface ParamRepository extends JpaRepository<Param, Long> {
    Optional<Param> findParamBy_paramKey(String paramKey);
}
