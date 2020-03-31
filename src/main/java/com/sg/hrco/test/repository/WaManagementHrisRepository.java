package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaManagementHris;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaManagementHris entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaManagementHrisRepository extends JpaRepository<WaManagementHris, Long> {
}
