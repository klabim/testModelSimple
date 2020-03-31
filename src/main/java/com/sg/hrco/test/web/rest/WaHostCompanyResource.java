package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaHostCompany;
import com.sg.hrco.test.repository.WaHostCompanyRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaHostCompany}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaHostCompanyResource {

    private final Logger log = LoggerFactory.getLogger(WaHostCompanyResource.class);

    private static final String ENTITY_NAME = "waHostCompany";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaHostCompanyRepository waHostCompanyRepository;

    public WaHostCompanyResource(WaHostCompanyRepository waHostCompanyRepository) {
        this.waHostCompanyRepository = waHostCompanyRepository;
    }

    /**
     * {@code POST  /wa-host-companies} : Create a new waHostCompany.
     *
     * @param waHostCompany the waHostCompany to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waHostCompany, or with status {@code 400 (Bad Request)} if the waHostCompany has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-host-companies")
    public ResponseEntity<WaHostCompany> createWaHostCompany(@Valid @RequestBody WaHostCompany waHostCompany) throws URISyntaxException {
        log.debug("REST request to save WaHostCompany : {}", waHostCompany);
        if (waHostCompany.getId() != null) {
            throw new BadRequestAlertException("A new waHostCompany cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaHostCompany result = waHostCompanyRepository.save(waHostCompany);
        return ResponseEntity.created(new URI("/api/wa-host-companies/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-host-companies} : Updates an existing waHostCompany.
     *
     * @param waHostCompany the waHostCompany to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waHostCompany,
     * or with status {@code 400 (Bad Request)} if the waHostCompany is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waHostCompany couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-host-companies")
    public ResponseEntity<WaHostCompany> updateWaHostCompany(@Valid @RequestBody WaHostCompany waHostCompany) throws URISyntaxException {
        log.debug("REST request to update WaHostCompany : {}", waHostCompany);
        if (waHostCompany.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaHostCompany result = waHostCompanyRepository.save(waHostCompany);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waHostCompany.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-host-companies} : get all the waHostCompanies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waHostCompanies in body.
     */
    @GetMapping("/wa-host-companies")
    public List<WaHostCompany> getAllWaHostCompanies() {
        log.debug("REST request to get all WaHostCompanies");
        return waHostCompanyRepository.findAll();
    }

    /**
     * {@code GET  /wa-host-companies/:id} : get the "id" waHostCompany.
     *
     * @param id the id of the waHostCompany to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waHostCompany, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-host-companies/{id}")
    public ResponseEntity<WaHostCompany> getWaHostCompany(@PathVariable Long id) {
        log.debug("REST request to get WaHostCompany : {}", id);
        Optional<WaHostCompany> waHostCompany = waHostCompanyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waHostCompany);
    }

    /**
     * {@code DELETE  /wa-host-companies/:id} : delete the "id" waHostCompany.
     *
     * @param id the id of the waHostCompany to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-host-companies/{id}")
    public ResponseEntity<Void> deleteWaHostCompany(@PathVariable Long id) {
        log.debug("REST request to delete WaHostCompany : {}", id);
        waHostCompanyRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
