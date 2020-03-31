package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaClassification;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaClassification entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaClassificationRepository extends JpaRepository<WaClassification, Long> {
}
