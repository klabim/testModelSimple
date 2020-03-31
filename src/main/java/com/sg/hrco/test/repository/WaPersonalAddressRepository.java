package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.WaPersonalAddress;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the WaPersonalAddress entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WaPersonalAddressRepository extends JpaRepository<WaPersonalAddress, Long> {
}
