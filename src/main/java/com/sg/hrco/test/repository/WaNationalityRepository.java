package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaNationality;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaNationality entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaNationalityRepository extends JpaRepository<WaNationality, Long> {
}
