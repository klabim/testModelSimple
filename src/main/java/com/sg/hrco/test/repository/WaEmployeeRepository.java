package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaEmployee;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaEmployee entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaEmployeeRepository extends JpaRepository<WaEmployee, Long> {
}
