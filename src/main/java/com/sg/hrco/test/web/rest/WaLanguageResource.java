package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaLanguage;
import com.sg.hrco.test.repository.WaLanguageRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaLanguage}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaLanguageResource {

    private final Logger log = LoggerFactory.getLogger(WaLanguageResource.class);

    private static final String ENTITY_NAME = "waLanguage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaLanguageRepository waLanguageRepository;

    public WaLanguageResource(WaLanguageRepository waLanguageRepository) {
        this.waLanguageRepository = waLanguageRepository;
    }

    /**
     * {@code POST  /wa-languages} : Create a new waLanguage.
     *
     * @param waLanguage the waLanguage to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waLanguage, or with status {@code 400 (Bad Request)} if the waLanguage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-languages")
    public ResponseEntity<WaLanguage> createWaLanguage(@Valid @RequestBody WaLanguage waLanguage) throws URISyntaxException {
        log.debug("REST request to save WaLanguage : {}", waLanguage);
        if (waLanguage.getId() != null) {
            throw new BadRequestAlertException("A new waLanguage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaLanguage result = waLanguageRepository.save(waLanguage);
        return ResponseEntity.created(new URI("/api/wa-languages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-languages} : Updates an existing waLanguage.
     *
     * @param waLanguage the waLanguage to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waLanguage,
     * or with status {@code 400 (Bad Request)} if the waLanguage is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waLanguage couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-languages")
    public ResponseEntity<WaLanguage> updateWaLanguage(@Valid @RequestBody WaLanguage waLanguage) throws URISyntaxException {
        log.debug("REST request to update WaLanguage : {}", waLanguage);
        if (waLanguage.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaLanguage result = waLanguageRepository.save(waLanguage);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waLanguage.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-languages} : get all the waLanguages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waLanguages in body.
     */
    @GetMapping("/wa-languages")
    public List<WaLanguage> getAllWaLanguages() {
        log.debug("REST request to get all WaLanguages");
        return waLanguageRepository.findAll();
    }

    /**
     * {@code GET  /wa-languages/:id} : get the "id" waLanguage.
     *
     * @param id the id of the waLanguage to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waLanguage, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-languages/{id}")
    public ResponseEntity<WaLanguage> getWaLanguage(@PathVariable Long id) {
        log.debug("REST request to get WaLanguage : {}", id);
        Optional<WaLanguage> waLanguage = waLanguageRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waLanguage);
    }

    /**
     * {@code DELETE  /wa-languages/:id} : delete the "id" waLanguage.
     *
     * @param id the id of the waLanguage to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-languages/{id}")
    public ResponseEntity<Void> deleteWaLanguage(@PathVariable Long id) {
        log.debug("REST request to delete WaLanguage : {}", id);
        waLanguageRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
