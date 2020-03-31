package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaClassification;
import com.sg.hrco.test.repository.WaClassificationRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaClassification}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaClassificationResource {

    private final Logger log = LoggerFactory.getLogger(WaClassificationResource.class);

    private static final String ENTITY_NAME = "waClassification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaClassificationRepository waClassificationRepository;

    public WaClassificationResource(WaClassificationRepository waClassificationRepository) {
        this.waClassificationRepository = waClassificationRepository;
    }

    /**
     * {@code POST  /wa-classifications} : Create a new waClassification.
     *
     * @param waClassification the waClassification to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waClassification, or with status {@code 400 (Bad Request)} if the waClassification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-classifications")
    public ResponseEntity<WaClassification> createWaClassification(@Valid @RequestBody WaClassification waClassification) throws URISyntaxException {
        log.debug("REST request to save WaClassification : {}", waClassification);
        if (waClassification.getId() != null) {
            throw new BadRequestAlertException("A new waClassification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaClassification result = waClassificationRepository.save(waClassification);
        return ResponseEntity.created(new URI("/api/wa-classifications/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-classifications} : Updates an existing waClassification.
     *
     * @param waClassification the waClassification to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waClassification,
     * or with status {@code 400 (Bad Request)} if the waClassification is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waClassification couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-classifications")
    public ResponseEntity<WaClassification> updateWaClassification(@Valid @RequestBody WaClassification waClassification) throws URISyntaxException {
        log.debug("REST request to update WaClassification : {}", waClassification);
        if (waClassification.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaClassification result = waClassificationRepository.save(waClassification);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waClassification.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-classifications} : get all the waClassifications.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waClassifications in body.
     */
    @GetMapping("/wa-classifications")
    public List<WaClassification> getAllWaClassifications() {
        log.debug("REST request to get all WaClassifications");
        return waClassificationRepository.findAll();
    }

    /**
     * {@code GET  /wa-classifications/:id} : get the "id" waClassification.
     *
     * @param id the id of the waClassification to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waClassification, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-classifications/{id}")
    public ResponseEntity<WaClassification> getWaClassification(@PathVariable Long id) {
        log.debug("REST request to get WaClassification : {}", id);
        Optional<WaClassification> waClassification = waClassificationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waClassification);
    }

    /**
     * {@code DELETE  /wa-classifications/:id} : delete the "id" waClassification.
     *
     * @param id the id of the waClassification to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-classifications/{id}")
    public ResponseEntity<Void> deleteWaClassification(@PathVariable Long id) {
        log.debug("REST request to delete WaClassification : {}", id);
        waClassificationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
