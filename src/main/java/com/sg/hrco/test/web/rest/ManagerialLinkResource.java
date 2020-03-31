package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.ManagerialLink;
import com.sg.hrco.test.repository.ManagerialLinkRepository;
import com.sg.hrco.test.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.sg.hrco.test.domain.ManagerialLink}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ManagerialLinkResource {

    private final Logger log = LoggerFactory.getLogger(ManagerialLinkResource.class);

    private static final String ENTITY_NAME = "managerialLink";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ManagerialLinkRepository managerialLinkRepository;

    public ManagerialLinkResource(ManagerialLinkRepository managerialLinkRepository) {
        this.managerialLinkRepository = managerialLinkRepository;
    }

    /**
     * {@code POST  /managerial-links} : Create a new managerialLink.
     *
     * @param managerialLink the managerialLink to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new managerialLink, or with status {@code 400 (Bad Request)} if the managerialLink has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/managerial-links")
    public ResponseEntity<ManagerialLink> createManagerialLink(@Valid @RequestBody ManagerialLink managerialLink) throws URISyntaxException {
        log.debug("REST request to save ManagerialLink : {}", managerialLink);
        if (managerialLink.getId() != null) {
            throw new BadRequestAlertException("A new managerialLink cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ManagerialLink result = managerialLinkRepository.save(managerialLink);
        return ResponseEntity.created(new URI("/api/managerial-links/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /managerial-links} : Updates an existing managerialLink.
     *
     * @param managerialLink the managerialLink to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated managerialLink,
     * or with status {@code 400 (Bad Request)} if the managerialLink is not valid,
     * or with status {@code 500 (Internal Server Error)} if the managerialLink couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/managerial-links")
    public ResponseEntity<ManagerialLink> updateManagerialLink(@Valid @RequestBody ManagerialLink managerialLink) throws URISyntaxException {
        log.debug("REST request to update ManagerialLink : {}", managerialLink);
        if (managerialLink.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        ManagerialLink result = managerialLinkRepository.save(managerialLink);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, managerialLink.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /managerial-links} : get all the managerialLinks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of managerialLinks in body.
     */
    @GetMapping("/managerial-links")
    public List<ManagerialLink> getAllManagerialLinks() {
        log.debug("REST request to get all ManagerialLinks");
        return managerialLinkRepository.findAll();
    }

    /**
     * {@code GET  /managerial-links/:id} : get the "id" managerialLink.
     *
     * @param id the id of the managerialLink to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the managerialLink, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/managerial-links/{id}")
    public ResponseEntity<ManagerialLink> getManagerialLink(@PathVariable Long id) {
        log.debug("REST request to get ManagerialLink : {}", id);
        Optional<ManagerialLink> managerialLink = managerialLinkRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(managerialLink);
    }

    /**
     * {@code DELETE  /managerial-links/:id} : delete the "id" managerialLink.
     *
     * @param id the id of the managerialLink to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/managerial-links/{id}")
    public ResponseEntity<Void> deleteManagerialLink(@PathVariable Long id) {
        log.debug("REST request to delete ManagerialLink : {}", id);
        managerialLinkRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
