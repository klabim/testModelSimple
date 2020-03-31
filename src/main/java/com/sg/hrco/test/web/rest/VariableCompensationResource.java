package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.VariableCompensation;
import com.sg.hrco.test.repository.VariableCompensationRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.VariableCompensation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class VariableCompensationResource {

    private final Logger log = LoggerFactory.getLogger(VariableCompensationResource.class);

    private static final String ENTITY_NAME = "variableCompensation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VariableCompensationRepository variableCompensationRepository;

    public VariableCompensationResource(VariableCompensationRepository variableCompensationRepository) {
        this.variableCompensationRepository = variableCompensationRepository;
    }

    /**
     * {@code POST  /variable-compensations} : Create a new variableCompensation.
     *
     * @param variableCompensation the variableCompensation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new variableCompensation, or with status {@code 400 (Bad Request)} if the variableCompensation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/variable-compensations")
    public ResponseEntity<VariableCompensation> createVariableCompensation(@Valid @RequestBody VariableCompensation variableCompensation) throws URISyntaxException {
        log.debug("REST request to save VariableCompensation : {}", variableCompensation);
        if (variableCompensation.getId() != null) {
            throw new BadRequestAlertException("A new variableCompensation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VariableCompensation result = variableCompensationRepository.save(variableCompensation);
        return ResponseEntity.created(new URI("/api/variable-compensations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /variable-compensations} : Updates an existing variableCompensation.
     *
     * @param variableCompensation the variableCompensation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated variableCompensation,
     * or with status {@code 400 (Bad Request)} if the variableCompensation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the variableCompensation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/variable-compensations")
    public ResponseEntity<VariableCompensation> updateVariableCompensation(@Valid @RequestBody VariableCompensation variableCompensation) throws URISyntaxException {
        log.debug("REST request to update VariableCompensation : {}", variableCompensation);
        if (variableCompensation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VariableCompensation result = variableCompensationRepository.save(variableCompensation);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, variableCompensation.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /variable-compensations} : get all the variableCompensations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of variableCompensations in body.
     */
    @GetMapping("/variable-compensations")
    public List<VariableCompensation> getAllVariableCompensations() {
        log.debug("REST request to get all VariableCompensations");
        return variableCompensationRepository.findAll();
    }

    /**
     * {@code GET  /variable-compensations/:id} : get the "id" variableCompensation.
     *
     * @param id the id of the variableCompensation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the variableCompensation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/variable-compensations/{id}")
    public ResponseEntity<VariableCompensation> getVariableCompensation(@PathVariable Long id) {
        log.debug("REST request to get VariableCompensation : {}", id);
        Optional<VariableCompensation> variableCompensation = variableCompensationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(variableCompensation);
    }

    /**
     * {@code DELETE  /variable-compensations/:id} : delete the "id" variableCompensation.
     *
     * @param id the id of the variableCompensation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/variable-compensations/{id}")
    public ResponseEntity<Void> deleteVariableCompensation(@PathVariable Long id) {
        log.debug("REST request to delete VariableCompensation : {}", id);
        variableCompensationRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
