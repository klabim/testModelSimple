package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaHomeCompany;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaHomeCompany entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaHomeCompanyRepository extends JpaRepository<WaHomeCompany, Long> {
}
