package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaMaritalStatus;
import com.sg.hrco.test.repository.WaMaritalStatusRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaMaritalStatus}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaMaritalStatusResource {

    private final Logger log = LoggerFactory.getLogger(WaMaritalStatusResource.class);

    private static final String ENTITY_NAME = "waMaritalStatus";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaMaritalStatusRepository waMaritalStatusRepository;

    public WaMaritalStatusResource(WaMaritalStatusRepository waMaritalStatusRepository) {
        this.waMaritalStatusRepository = waMaritalStatusRepository;
    }

    /**
     * {@code POST  /wa-marital-statuses} : Create a new waMaritalStatus.
     *
     * @param waMaritalStatus the waMaritalStatus to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waMaritalStatus, or with status {@code 400 (Bad Request)} if the waMaritalStatus has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-marital-statuses")
    public ResponseEntity<WaMaritalStatus> createWaMaritalStatus(@Valid @RequestBody WaMaritalStatus waMaritalStatus) throws URISyntaxException {
        log.debug("REST request to save WaMaritalStatus : {}", waMaritalStatus);
        if (waMaritalStatus.getId() != null) {
            throw new BadRequestAlertException("A new waMaritalStatus cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaMaritalStatus result = waMaritalStatusRepository.save(waMaritalStatus);
        return ResponseEntity.created(new URI("/api/wa-marital-statuses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-marital-statuses} : Updates an existing waMaritalStatus.
     *
     * @param waMaritalStatus the waMaritalStatus to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waMaritalStatus,
     * or with status {@code 400 (Bad Request)} if the waMaritalStatus is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waMaritalStatus couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-marital-statuses")
    public ResponseEntity<WaMaritalStatus> updateWaMaritalStatus(@Valid @RequestBody WaMaritalStatus waMaritalStatus) throws URISyntaxException {
        log.debug("REST request to update WaMaritalStatus : {}", waMaritalStatus);
        if (waMaritalStatus.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaMaritalStatus result = waMaritalStatusRepository.save(waMaritalStatus);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waMaritalStatus.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-marital-statuses} : get all the waMaritalStatuses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waMaritalStatuses in body.
     */
    @GetMapping("/wa-marital-statuses")
    public List<WaMaritalStatus> getAllWaMaritalStatuses() {
        log.debug("REST request to get all WaMaritalStatuses");
        return waMaritalStatusRepository.findAll();
    }

    /**
     * {@code GET  /wa-marital-statuses/:id} : get the "id" waMaritalStatus.
     *
     * @param id the id of the waMaritalStatus to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waMaritalStatus, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-marital-statuses/{id}")
    public ResponseEntity<WaMaritalStatus> getWaMaritalStatus(@PathVariable Long id) {
        log.debug("REST request to get WaMaritalStatus : {}", id);
        Optional<WaMaritalStatus> waMaritalStatus = waMaritalStatusRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waMaritalStatus);
    }

    /**
     * {@code DELETE  /wa-marital-statuses/:id} : delete the "id" waMaritalStatus.
     *
     * @param id the id of the waMaritalStatus to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-marital-statuses/{id}")
    public ResponseEntity<Void> deleteWaMaritalStatus(@PathVariable Long id) {
        log.debug("REST request to delete WaMaritalStatus : {}", id);
        waMaritalStatusRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
