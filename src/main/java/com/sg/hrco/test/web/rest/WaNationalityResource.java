package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaNationality;
import com.sg.hrco.test.repository.WaNationalityRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaNationality}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaNationalityResource {

    private final Logger log = LoggerFactory.getLogger(WaNationalityResource.class);

    private static final String ENTITY_NAME = "waNationality";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaNationalityRepository waNationalityRepository;

    public WaNationalityResource(WaNationalityRepository waNationalityRepository) {
        this.waNationalityRepository = waNationalityRepository;
    }

    /**
     * {@code POST  /wa-nationalities} : Create a new waNationality.
     *
     * @param waNationality the waNationality to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waNationality, or with status {@code 400 (Bad Request)} if the waNationality has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-nationalities")
    public ResponseEntity<WaNationality> createWaNationality(@Valid @RequestBody WaNationality waNationality) throws URISyntaxException {
        log.debug("REST request to save WaNationality : {}", waNationality);
        if (waNationality.getId() != null) {
            throw new BadRequestAlertException("A new waNationality cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaNationality result = waNationalityRepository.save(waNationality);
        return ResponseEntity.created(new URI("/api/wa-nationalities/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-nationalities} : Updates an existing waNationality.
     *
     * @param waNationality the waNationality to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waNationality,
     * or with status {@code 400 (Bad Request)} if the waNationality is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waNationality couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-nationalities")
    public ResponseEntity<WaNationality> updateWaNationality(@Valid @RequestBody WaNationality waNationality) throws URISyntaxException {
        log.debug("REST request to update WaNationality : {}", waNationality);
        if (waNationality.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaNationality result = waNationalityRepository.save(waNationality);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waNationality.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-nationalities} : get all the waNationalities.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waNationalities in body.
     */
    @GetMapping("/wa-nationalities")
    public List<WaNationality> getAllWaNationalities() {
        log.debug("REST request to get all WaNationalities");
        return waNationalityRepository.findAll();
    }

    /**
     * {@code GET  /wa-nationalities/:id} : get the "id" waNationality.
     *
     * @param id the id of the waNationality to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waNationality, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-nationalities/{id}")
    public ResponseEntity<WaNationality> getWaNationality(@PathVariable Long id) {
        log.debug("REST request to get WaNationality : {}", id);
        Optional<WaNationality> waNationality = waNationalityRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waNationality);
    }

    /**
     * {@code DELETE  /wa-nationalities/:id} : delete the "id" waNationality.
     *
     * @param id the id of the waNationality to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-nationalities/{id}")
    public ResponseEntity<Void> deleteWaNationality(@PathVariable Long id) {
        log.debug("REST request to delete WaNationality : {}", id);
        waNationalityRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
