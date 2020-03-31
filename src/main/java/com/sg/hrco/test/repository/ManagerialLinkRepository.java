package com.sg.hrco.test.repository;

import com.sg.hrco.test.domain.ManagerialLink;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the ManagerialLink entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ManagerialLinkRepository extends JpaRepository<ManagerialLink, Long> {
}
