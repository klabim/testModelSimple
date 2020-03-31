package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaHomeCompany;
import com.sg.hrco.test.repository.WaHomeCompanyRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaHomeCompany}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaHomeCompanyResource {

    private final Logger log = LoggerFactory.getLogger(WaHomeCompanyResource.class);

    private static final String ENTITY_NAME = "waHomeCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaHomeCompanyRepository waHomeCompanyRepository;

    public WaHomeCompanyResource(WaHomeCompanyRepository waHomeCompanyRepository) {
        this.waHomeCompanyRepository = waHomeCompanyRepository;
    }

    /**
     * {@code POST  /wa-home-companies} : Create a new waHomeCompany.
     *
     * @param waHomeCompany the waHomeCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waHomeCompany, or with status {@code 400 (Bad Request)} if the waHomeCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-home-companies")
    public ResponseEntity<WaHomeCompany> createWaHomeCompany(@Valid @RequestBody WaHomeCompany waHomeCompany) throws URISyntaxException {
        log.debug("REST request to save WaHomeCompany : {}", waHomeCompany);
        if (waHomeCompany.getId() != null) {
            throw new BadRequestAlertException("A new waHomeCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaHomeCompany result = waHomeCompanyRepository.save(waHomeCompany);
        return ResponseEntity.created(new URI("/api/wa-home-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-home-companies} : Updates an existing waHomeCompany.
     *
     * @param waHomeCompany the waHomeCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waHomeCompany,
     * or with status {@code 400 (Bad Request)} if the waHomeCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waHomeCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-home-companies")
    public ResponseEntity<WaHomeCompany> updateWaHomeCompany(@Valid @RequestBody WaHomeCompany waHomeCompany) throws URISyntaxException {
        log.debug("REST request to update WaHomeCompany : {}", waHomeCompany);
        if (waHomeCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaHomeCompany result = waHomeCompanyRepository.save(waHomeCompany);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waHomeCompany.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-home-companies} : get all the waHomeCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waHomeCompanies in body.
     */
    @GetMapping("/wa-home-companies")
    public List<WaHomeCompany> getAllWaHomeCompanies() {
        log.debug("REST request to get all WaHomeCompanies");
        return waHomeCompanyRepository.findAll();
    }

    /**
     * {@code GET  /wa-home-companies/:id} : get the "id" waHomeCompany.
     *
     * @param id the id of the waHomeCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waHomeCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-home-companies/{id}")
    public ResponseEntity<WaHomeCompany> getWaHomeCompany(@PathVariable Long id) {
        log.debug("REST request to get WaHomeCompany : {}", id);
        Optional<WaHomeCompany> waHomeCompany = waHomeCompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waHomeCompany);
    }

    /**
     * {@code DELETE  /wa-home-companies/:id} : delete the "id" waHomeCompany.
     *
     * @param id the id of the waHomeCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-home-companies/{id}")
    public ResponseEntity<Void> deleteWaHomeCompany(@PathVariable Long id) {
        log.debug("REST request to delete WaHomeCompany : {}", id);
        waHomeCompanyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
