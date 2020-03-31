package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaSeniority;
import com.sg.hrco.test.repository.WaSeniorityRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaSeniority}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaSeniorityResource {

    private final Logger log = LoggerFactory.getLogger(WaSeniorityResource.class);

    private static final String ENTITY_NAME = "waSeniority";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaSeniorityRepository waSeniorityRepository;

    public WaSeniorityResource(WaSeniorityRepository waSeniorityRepository) {
        this.waSeniorityRepository = waSeniorityRepository;
    }

    /**
     * {@code POST  /wa-seniorities} : Create a new waSeniority.
     *
     * @param waSeniority the waSeniority to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waSeniority, or with status {@code 400 (Bad Request)} if the waSeniority has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-seniorities")
    public ResponseEntity<WaSeniority> createWaSeniority(@Valid @RequestBody WaSeniority waSeniority) throws URISyntaxException {
        log.debug("REST request to save WaSeniority : {}", waSeniority);
        if (waSeniority.getId() != null) {
            throw new BadRequestAlertException("A new waSeniority cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaSeniority result = waSeniorityRepository.save(waSeniority);
        return ResponseEntity.created(new URI("/api/wa-seniorities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-seniorities} : Updates an existing waSeniority.
     *
     * @param waSeniority the waSeniority to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waSeniority,
     * or with status {@code 400 (Bad Request)} if the waSeniority is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waSeniority couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-seniorities")
    public ResponseEntity<WaSeniority> updateWaSeniority(@Valid @RequestBody WaSeniority waSeniority) throws URISyntaxException {
        log.debug("REST request to update WaSeniority : {}", waSeniority);
        if (waSeniority.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaSeniority result = waSeniorityRepository.save(waSeniority);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waSeniority.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-seniorities} : get all the waSeniorities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waSeniorities in body.
     */
    @GetMapping("/wa-seniorities")
    public List<WaSeniority> getAllWaSeniorities() {
        log.debug("REST request to get all WaSeniorities");
        return waSeniorityRepository.findAll();
    }

    /**
     * {@code GET  /wa-seniorities/:id} : get the "id" waSeniority.
     *
     * @param id the id of the waSeniority to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waSeniority, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-seniorities/{id}")
    public ResponseEntity<WaSeniority> getWaSeniority(@PathVariable Long id) {
        log.debug("REST request to get WaSeniority : {}", id);
        Optional<WaSeniority> waSeniority = waSeniorityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waSeniority);
    }

    /**
     * {@code DELETE  /wa-seniorities/:id} : delete the "id" waSeniority.
     *
     * @param id the id of the waSeniority to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-seniorities/{id}")
    public ResponseEntity<Void> deleteWaSeniority(@PathVariable Long id) {
        log.debug("REST request to delete WaSeniority : {}", id);
        waSeniorityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
