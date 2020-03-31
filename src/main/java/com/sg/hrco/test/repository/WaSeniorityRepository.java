package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaSeniority;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaSeniority entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaSeniorityRepository extends JpaRepository<WaSeniority, Long> {
}
