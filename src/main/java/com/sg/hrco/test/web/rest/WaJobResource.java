package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaJob;
import com.sg.hrco.test.repository.WaJobRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaJob}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaJobResource {

    private final Logger log = LoggerFactory.getLogger(WaJobResource.class);

    private static final String ENTITY_NAME = "waJob";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaJobRepository waJobRepository;

    public WaJobResource(WaJobRepository waJobRepository) {
        this.waJobRepository = waJobRepository;
    }

    /**
     * {@code POST  /wa-jobs} : Create a new waJob.
     *
     * @param waJob the waJob to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waJob, or with status {@code 400 (Bad Request)} if the waJob has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-jobs")
    public ResponseEntity<WaJob> createWaJob(@Valid @RequestBody WaJob waJob) throws URISyntaxException {
        log.debug("REST request to save WaJob : {}", waJob);
        if (waJob.getId() != null) {
            throw new BadRequestAlertException("A new waJob cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaJob result = waJobRepository.save(waJob);
        return ResponseEntity.created(new URI("/api/wa-jobs/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-jobs} : Updates an existing waJob.
     *
     * @param waJob the waJob to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waJob,
     * or with status {@code 400 (Bad Request)} if the waJob is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waJob couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-jobs")
    public ResponseEntity<WaJob> updateWaJob(@Valid @RequestBody WaJob waJob) throws URISyntaxException {
        log.debug("REST request to update WaJob : {}", waJob);
        if (waJob.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaJob result = waJobRepository.save(waJob);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waJob.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-jobs} : get all the waJobs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waJobs in body.
     */
    @GetMapping("/wa-jobs")
    public List<WaJob> getAllWaJobs() {
        log.debug("REST request to get all WaJobs");
        return waJobRepository.findAll();
    }

    /**
     * {@code GET  /wa-jobs/:id} : get the "id" waJob.
     *
     * @param id the id of the waJob to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waJob, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-jobs/{id}")
    public ResponseEntity<WaJob> getWaJob(@PathVariable Long id) {
        log.debug("REST request to get WaJob : {}", id);
        Optional<WaJob> waJob = waJobRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waJob);
    }

    /**
     * {@code DELETE  /wa-jobs/:id} : delete the "id" waJob.
     *
     * @param id the id of the waJob to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-jobs/{id}")
    public ResponseEntity<Void> deleteWaJob(@PathVariable Long id) {
        log.debug("REST request to delete WaJob : {}", id);
        waJobRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
