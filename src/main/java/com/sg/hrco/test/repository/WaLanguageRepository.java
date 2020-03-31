package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaLanguage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaLanguage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaLanguageRepository extends JpaRepository<WaLanguage, Long> {
}
