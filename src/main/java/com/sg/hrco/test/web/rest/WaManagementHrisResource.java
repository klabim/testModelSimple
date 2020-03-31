package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaManagementHris;
import com.sg.hrco.test.repository.WaManagementHrisRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaManagementHris}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaManagementHrisResource {

    private final Logger log = LoggerFactory.getLogger(WaManagementHrisResource.class);

    private static final String ENTITY_NAME = "waManagementHris";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaManagementHrisRepository waManagementHrisRepository;

    public WaManagementHrisResource(WaManagementHrisRepository waManagementHrisRepository) {
        this.waManagementHrisRepository = waManagementHrisRepository;
    }

    /**
     * {@code POST  /wa-management-hrises} : Create a new waManagementHris.
     *
     * @param waManagementHris the waManagementHris to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waManagementHris, or with status {@code 400 (Bad Request)} if the waManagementHris has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-management-hrises")
    public ResponseEntity<WaManagementHris> createWaManagementHris(@Valid @RequestBody WaManagementHris waManagementHris) throws URISyntaxException {
        log.debug("REST request to save WaManagementHris : {}", waManagementHris);
        if (waManagementHris.getId() != null) {
            throw new BadRequestAlertException("A new waManagementHris cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaManagementHris result = waManagementHrisRepository.save(waManagementHris);
        return ResponseEntity.created(new URI("/api/wa-management-hrises/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-management-hrises} : Updates an existing waManagementHris.
     *
     * @param waManagementHris the waManagementHris to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waManagementHris,
     * or with status {@code 400 (Bad Request)} if the waManagementHris is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waManagementHris couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-management-hrises")
    public ResponseEntity<WaManagementHris> updateWaManagementHris(@Valid @RequestBody WaManagementHris waManagementHris) throws URISyntaxException {
        log.debug("REST request to update WaManagementHris : {}", waManagementHris);
        if (waManagementHris.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaManagementHris result = waManagementHrisRepository.save(waManagementHris);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waManagementHris.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-management-hrises} : get all the waManagementHrises.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waManagementHrises in body.
     */
    @GetMapping("/wa-management-hrises")
    public List<WaManagementHris> getAllWaManagementHrises() {
        log.debug("REST request to get all WaManagementHrises");
        return waManagementHrisRepository.findAll();
    }

    /**
     * {@code GET  /wa-management-hrises/:id} : get the "id" waManagementHris.
     *
     * @param id the id of the waManagementHris to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waManagementHris, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-management-hrises/{id}")
    public ResponseEntity<WaManagementHris> getWaManagementHris(@PathVariable Long id) {
        log.debug("REST request to get WaManagementHris : {}", id);
        Optional<WaManagementHris> waManagementHris = waManagementHrisRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waManagementHris);
    }

    /**
     * {@code DELETE  /wa-management-hrises/:id} : delete the "id" waManagementHris.
     *
     * @param id the id of the waManagementHris to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-management-hrises/{id}")
    public ResponseEntity<Void> deleteWaManagementHris(@PathVariable Long id) {
        log.debug("REST request to delete WaManagementHris : {}", id);
        waManagementHrisRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
