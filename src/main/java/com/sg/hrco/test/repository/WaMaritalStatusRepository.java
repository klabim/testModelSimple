package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaMaritalStatus;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaMaritalStatus entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaMaritalStatusRepository extends JpaRepository<WaMaritalStatus, Long> {
}
