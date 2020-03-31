package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.FixedCompensation;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the FixedCompensation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FixedCompensationRepository extends JpaRepository<FixedCompensation, Long> {
}
