package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaAssignment;
import com.sg.hrco.test.repository.WaAssignmentRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaAssignment}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaAssignmentResource {

    private final Logger log = LoggerFactory.getLogger(WaAssignmentResource.class);

    private static final String ENTITY_NAME = "waAssignment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaAssignmentRepository waAssignmentRepository;

    public WaAssignmentResource(WaAssignmentRepository waAssignmentRepository) {
        this.waAssignmentRepository = waAssignmentRepository;
    }

    /**
     * {@code POST  /wa-assignments} : Create a new waAssignment.
     *
     * @param waAssignment the waAssignment to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waAssignment, or with status {@code 400 (Bad Request)} if the waAssignment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-assignments")
    public ResponseEntity<WaAssignment> createWaAssignment(@Valid @RequestBody WaAssignment waAssignment) throws URISyntaxException {
        log.debug("REST request to save WaAssignment : {}", waAssignment);
        if (waAssignment.getId() != null) {
            throw new BadRequestAlertException("A new waAssignment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaAssignment result = waAssignmentRepository.save(waAssignment);
        return ResponseEntity.created(new URI("/api/wa-assignments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-assignments} : Updates an existing waAssignment.
     *
     * @param waAssignment the waAssignment to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waAssignment,
     * or with status {@code 400 (Bad Request)} if the waAssignment is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waAssignment couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-assignments")
    public ResponseEntity<WaAssignment> updateWaAssignment(@Valid @RequestBody WaAssignment waAssignment) throws URISyntaxException {
        log.debug("REST request to update WaAssignment : {}", waAssignment);
        if (waAssignment.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaAssignment result = waAssignmentRepository.save(waAssignment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waAssignment.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-assignments} : get all the waAssignments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waAssignments in body.
     */
    @GetMapping("/wa-assignments")
    public List<WaAssignment> getAllWaAssignments() {
        log.debug("REST request to get all WaAssignments");
        return waAssignmentRepository.findAll();
    }

    /**
     * {@code GET  /wa-assignments/:id} : get the "id" waAssignment.
     *
     * @param id the id of the waAssignment to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waAssignment, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-assignments/{id}")
    public ResponseEntity<WaAssignment> getWaAssignment(@PathVariable Long id) {
        log.debug("REST request to get WaAssignment : {}", id);
        Optional<WaAssignment> waAssignment = waAssignmentRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waAssignment);
    }

    /**
     * {@code DELETE  /wa-assignments/:id} : delete the "id" waAssignment.
     *
     * @param id the id of the waAssignment to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-assignments/{id}")
    public ResponseEntity<Void> deleteWaAssignment(@PathVariable Long id) {
        log.debug("REST request to delete WaAssignment : {}", id);
        waAssignmentRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
