package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaGender;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaGender entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaGenderRepository extends JpaRepository<WaGender, Long> {
}
