package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaPersonalAddress;
import com.sg.hrco.test.repository.WaPersonalAddressRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaPersonalAddress}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaPersonalAddressResource {

    private final Logger log = LoggerFactory.getLogger(WaPersonalAddressResource.class);

    private static final String ENTITY_NAME = "waPersonalAddress";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaPersonalAddressRepository waPersonalAddressRepository;

    public WaPersonalAddressResource(WaPersonalAddressRepository waPersonalAddressRepository) {
        this.waPersonalAddressRepository = waPersonalAddressRepository;
    }

    /**
     * {@code POST  /wa-personal-addresses} : Create a new waPersonalAddress.
     *
     * @param waPersonalAddress the waPersonalAddress to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waPersonalAddress, or with status {@code 400 (Bad Request)} if the waPersonalAddress has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-personal-addresses")
    public ResponseEntity<WaPersonalAddress> createWaPersonalAddress(@Valid @RequestBody WaPersonalAddress waPersonalAddress) throws URISyntaxException {
        log.debug("REST request to save WaPersonalAddress : {}", waPersonalAddress);
        if (waPersonalAddress.getId() != null) {
            throw new BadRequestAlertException("A new waPersonalAddress cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaPersonalAddress result = waPersonalAddressRepository.save(waPersonalAddress);
        return ResponseEntity.created(new URI("/api/wa-personal-addresses/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-personal-addresses} : Updates an existing waPersonalAddress.
     *
     * @param waPersonalAddress the waPersonalAddress to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waPersonalAddress,
     * or with status {@code 400 (Bad Request)} if the waPersonalAddress is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waPersonalAddress couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-personal-addresses")
    public ResponseEntity<WaPersonalAddress> updateWaPersonalAddress(@Valid @RequestBody WaPersonalAddress waPersonalAddress) throws URISyntaxException {
        log.debug("REST request to update WaPersonalAddress : {}", waPersonalAddress);
        if (waPersonalAddress.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaPersonalAddress result = waPersonalAddressRepository.save(waPersonalAddress);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waPersonalAddress.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-personal-addresses} : get all the waPersonalAddresses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waPersonalAddresses in body.
     */
    @GetMapping("/wa-personal-addresses")
    public List<WaPersonalAddress> getAllWaPersonalAddresses() {
        log.debug("REST request to get all WaPersonalAddresses");
        return waPersonalAddressRepository.findAll();
    }

    /**
     * {@code GET  /wa-personal-addresses/:id} : get the "id" waPersonalAddress.
     *
     * @param id the id of the waPersonalAddress to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waPersonalAddress, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-personal-addresses/{id}")
    public ResponseEntity<WaPersonalAddress> getWaPersonalAddress(@PathVariable Long id) {
        log.debug("REST request to get WaPersonalAddress : {}", id);
        Optional<WaPersonalAddress> waPersonalAddress = waPersonalAddressRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waPersonalAddress);
    }

    /**
     * {@code DELETE  /wa-personal-addresses/:id} : delete the "id" waPersonalAddress.
     *
     * @param id the id of the waPersonalAddress to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-personal-addresses/{id}")
    public ResponseEntity<Void> deleteWaPersonalAddress(@PathVariable Long id) {
        log.debug("REST request to delete WaPersonalAddress : {}", id);
        waPersonalAddressRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
