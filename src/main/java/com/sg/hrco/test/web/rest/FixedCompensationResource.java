package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.FixedCompensation;
import com.sg.hrco.test.repository.FixedCompensationRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.FixedCompensation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class FixedCompensationResource {

    private final Logger log = LoggerFactory.getLogger(FixedCompensationResource.class);

    private static final String ENTITY_NAME = "fixedCompensation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FixedCompensationRepository fixedCompensationRepository;

    public FixedCompensationResource(FixedCompensationRepository fixedCompensationRepository) {
        this.fixedCompensationRepository = fixedCompensationRepository;
    }

    /**
     * {@code POST  /fixed-compensations} : Create a new fixedCompensation.
     *
     * @param fixedCompensation the fixedCompensation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fixedCompensation, or with status {@code 400 (Bad Request)} if the fixedCompensation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fixed-compensations")
    public ResponseEntity<FixedCompensation> createFixedCompensation(@Valid @RequestBody FixedCompensation fixedCompensation) throws URISyntaxException {
        log.debug("REST request to save FixedCompensation : {}", fixedCompensation);
        if (fixedCompensation.getId() != null) {
            throw new BadRequestAlertException("A new fixedCompensation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FixedCompensation result = fixedCompensationRepository.save(fixedCompensation);
        return ResponseEntity.created(new URI("/api/fixed-compensations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fixed-compensations} : Updates an existing fixedCompensation.
     *
     * @param fixedCompensation the fixedCompensation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fixedCompensation,
     * or with status {@code 400 (Bad Request)} if the fixedCompensation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fixedCompensation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fixed-compensations")
    public ResponseEntity<FixedCompensation> updateFixedCompensation(@Valid @RequestBody FixedCompensation fixedCompensation) throws URISyntaxException {
        log.debug("REST request to update FixedCompensation : {}", fixedCompensation);
        if (fixedCompensation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FixedCompensation result = fixedCompensationRepository.save(fixedCompensation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, fixedCompensation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fixed-compensations} : get all the fixedCompensations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fixedCompensations in body.
     */
    @GetMapping("/fixed-compensations")
    public List<FixedCompensation> getAllFixedCompensations() {
        log.debug("REST request to get all FixedCompensations");
        return fixedCompensationRepository.findAll();
    }

    /**
     * {@code GET  /fixed-compensations/:id} : get the "id" fixedCompensation.
     *
     * @param id the id of the fixedCompensation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fixedCompensation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fixed-compensations/{id}")
    public ResponseEntity<FixedCompensation> getFixedCompensation(@PathVariable Long id) {
        log.debug("REST request to get FixedCompensation : {}", id);
        Optional<FixedCompensation> fixedCompensation = fixedCompensationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(fixedCompensation);
    }

    /**
     * {@code DELETE  /fixed-compensations/:id} : delete the "id" fixedCompensation.
     *
     * @param id the id of the fixedCompensation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fixed-compensations/{id}")
    public ResponseEntity<Void> deleteFixedCompensation(@PathVariable Long id) {
        log.debug("REST request to delete FixedCompensation : {}", id);
        fixedCompensationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
