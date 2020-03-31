package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.VariableCompensation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the VariableCompensation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface VariableCompensationRepository extends JpaRepository<VariableCompensation, Long> {
}
