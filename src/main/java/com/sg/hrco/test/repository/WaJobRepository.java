package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaJob;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaJob entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaJobRepository extends JpaRepository<WaJob, Long> {
}
