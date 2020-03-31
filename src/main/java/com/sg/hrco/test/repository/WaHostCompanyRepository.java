package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaHostCompany;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaHostCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaHostCompanyRepository extends JpaRepository<WaHostCompany, Long> {
}
