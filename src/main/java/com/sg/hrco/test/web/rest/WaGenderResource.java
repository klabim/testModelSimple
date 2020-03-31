package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaGender;
import com.sg.hrco.test.repository.WaGenderRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaGender}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaGenderResource {

    private final Logger log = LoggerFactory.getLogger(WaGenderResource.class);

    private static final String ENTITY_NAME = "waGender";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaGenderRepository waGenderRepository;

    public WaGenderResource(WaGenderRepository waGenderRepository) {
        this.waGenderRepository = waGenderRepository;
    }

    /**
     * {@code POST  /wa-genders} : Create a new waGender.
     *
     * @param waGender the waGender to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waGender, or with status {@code 400 (Bad Request)} if the waGender has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-genders")
    public ResponseEntity<WaGender> createWaGender(@Valid @RequestBody WaGender waGender) throws URISyntaxException {
        log.debug("REST request to save WaGender : {}", waGender);
        if (waGender.getId() != null) {
            throw new BadRequestAlertException("A new waGender cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaGender result = waGenderRepository.save(waGender);
        return ResponseEntity.created(new URI("/api/wa-genders/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-genders} : Updates an existing waGender.
     *
     * @param waGender the waGender to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waGender,
     * or with status {@code 400 (Bad Request)} if the waGender is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waGender couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-genders")
    public ResponseEntity<WaGender> updateWaGender(@Valid @RequestBody WaGender waGender) throws URISyntaxException {
        log.debug("REST request to update WaGender : {}", waGender);
        if (waGender.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaGender result = waGenderRepository.save(waGender);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waGender.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-genders} : get all the waGenders.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waGenders in body.
     */
    @GetMapping("/wa-genders")
    public List<WaGender> getAllWaGenders() {
        log.debug("REST request to get all WaGenders");
        return waGenderRepository.findAll();
    }

    /**
     * {@code GET  /wa-genders/:id} : get the "id" waGender.
     *
     * @param id the id of the waGender to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waGender, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-genders/{id}")
    public ResponseEntity<WaGender> getWaGender(@PathVariable Long id) {
        log.debug("REST request to get WaGender : {}", id);
        Optional<WaGender> waGender = waGenderRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waGender);
    }

    /**
     * {@code DELETE  /wa-genders/:id} : delete the "id" waGender.
     *
     * @param id the id of the waGender to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-genders/{id}")
    public ResponseEntity<Void> deleteWaGender(@PathVariable Long id) {
        log.debug("REST request to delete WaGender : {}", id);
        waGenderRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
