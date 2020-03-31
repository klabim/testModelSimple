package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.domain.WaEmployee;
import com.sg.hrco.test.repository.WaEmployeeRepository;
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
 * REST controller for managing {@link com.sg.hrco.test.domain.WaEmployee}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class WaEmployeeResource {

    private final Logger log = LoggerFactory.getLogger(WaEmployeeResource.class);

    private static final String ENTITY_NAME = "waEmployee";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final WaEmployeeRepository waEmployeeRepository;

    public WaEmployeeResource(WaEmployeeRepository waEmployeeRepository) {
        this.waEmployeeRepository = waEmployeeRepository;
    }

    /**
     * {@code POST  /wa-employees} : Create a new waEmployee.
     *
     * @param waEmployee the waEmployee to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new waEmployee, or with status {@code 400 (Bad Request)} if the waEmployee has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/wa-employees")
    public ResponseEntity<WaEmployee> createWaEmployee(@Valid @RequestBody WaEmployee waEmployee) throws URISyntaxException {
        log.debug("REST request to save WaEmployee : {}", waEmployee);
        if (waEmployee.getId() != null) {
            throw new BadRequestAlertException("A new waEmployee cannot already have an ID", ENTITY_NAME, "idexists");
        }
        WaEmployee result = waEmployeeRepository.save(waEmployee);
        return ResponseEntity.created(new URI("/api/wa-employees/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /wa-employees} : Updates an existing waEmployee.
     *
     * @param waEmployee the waEmployee to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated waEmployee,
     * or with status {@code 400 (Bad Request)} if the waEmployee is not valid,
     * or with status {@code 500 (Internal Server Error)} if the waEmployee couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/wa-employees")
    public ResponseEntity<WaEmployee> updateWaEmployee(@Valid @RequestBody WaEmployee waEmployee) throws URISyntaxException {
        log.debug("REST request to update WaEmployee : {}", waEmployee);
        if (waEmployee.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        WaEmployee result = waEmployeeRepository.save(waEmployee);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, waEmployee.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /wa-employees} : get all the waEmployees.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of waEmployees in body.
     */
    @GetMapping("/wa-employees")
    public List<WaEmployee> getAllWaEmployees() {
        log.debug("REST request to get all WaEmployees");
        return waEmployeeRepository.findAll();
    }

    /**
     * {@code GET  /wa-employees/:id} : get the "id" waEmployee.
     *
     * @param id the id of the waEmployee to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the waEmployee, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/wa-employees/{id}")
    public ResponseEntity<WaEmployee> getWaEmployee(@PathVariable Long id) {
        log.debug("REST request to get WaEmployee : {}", id);
        Optional<WaEmployee> waEmployee = waEmployeeRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(waEmployee);
    }

    /**
     * {@code DELETE  /wa-employees/:id} : delete the "id" waEmployee.
     *
     * @param id the id of the waEmployee to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/wa-employees/{id}")
    public ResponseEntity<Void> deleteWaEmployee(@PathVariable Long id) {
        log.debug("REST request to delete WaEmployee : {}", id);
        waEmployeeRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
