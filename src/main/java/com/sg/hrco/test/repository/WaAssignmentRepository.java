package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaAssignment;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaAssignment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaAssignmentRepository extends JpaRepository<WaAssignment, Long> {
}
