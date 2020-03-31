package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaManagementHris;
import com.sg.hrco.test.repository.WaManagementHrisRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link WaManagementHrisResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaManagementHrisResourceIT {

    private static final String DEFAULT_MANAGEMENT_HRIS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MANAGEMENT_HRIS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGEMENT_HRIS_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_MANAGEMENT_HRIS_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_ENTITY_MANAGEMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ENTITY_MANAGEMENT_CODE = "BBBBBBBBBB";

    @Autowired
    private WaManagementHrisRepository waManagementHrisRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaManagementHrisMockMvc;

    private WaManagementHris waManagementHris;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaManagementHris createEntity(EntityManager em) {
        WaManagementHris waManagementHris = new WaManagementHris()
            .managementHrisCode(DEFAULT_MANAGEMENT_HRIS_CODE)
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .managementHrisLabel(DEFAULT_MANAGEMENT_HRIS_LABEL)
            .entityManagementCode(DEFAULT_ENTITY_MANAGEMENT_CODE);
        return waManagementHris;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaManagementHris createUpdatedEntity(EntityManager em) {
        WaManagementHris waManagementHris = new WaManagementHris()
            .managementHrisCode(UPDATED_MANAGEMENT_HRIS_CODE)
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .managementHrisLabel(UPDATED_MANAGEMENT_HRIS_LABEL)
            .entityManagementCode(UPDATED_ENTITY_MANAGEMENT_CODE);
        return waManagementHris;
    }

    @BeforeEach
    public void initTest() {
        waManagementHris = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaManagementHris() throws Exception {
        int databaseSizeBeforeCreate = waManagementHrisRepository.findAll().size();

        // Create the WaManagementHris
        restWaManagementHrisMockMvc.perform(post("/api/wa-management-hrises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waManagementHris)))
            .andExpect(status().isCreated());

        // Validate the WaManagementHris in the database
        List<WaManagementHris> waManagementHrisList = waManagementHrisRepository.findAll();
        assertThat(waManagementHrisList).hasSize(databaseSizeBeforeCreate + 1);
        WaManagementHris testWaManagementHris = waManagementHrisList.get(waManagementHrisList.size() - 1);
        assertThat(testWaManagementHris.getManagementHrisCode()).isEqualTo(DEFAULT_MANAGEMENT_HRIS_CODE);
        assertThat(testWaManagementHris.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaManagementHris.getManagementHrisLabel()).isEqualTo(DEFAULT_MANAGEMENT_HRIS_LABEL);
        assertThat(testWaManagementHris.getEntityManagementCode()).isEqualTo(DEFAULT_ENTITY_MANAGEMENT_CODE);
    }

    @Test
    @Transactional
    public void createWaManagementHrisWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waManagementHrisRepository.findAll().size();

        // Create the WaManagementHris with an existing ID
        waManagementHris.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaManagementHrisMockMvc.perform(post("/api/wa-management-hrises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waManagementHris)))
            .andExpect(status().isBadRequest());

        // Validate the WaManagementHris in the database
        List<WaManagementHris> waManagementHrisList = waManagementHrisRepository.findAll();
        assertThat(waManagementHrisList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLocalIdNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = waManagementHrisRepository.findAll().size();
        // set the field null
        waManagementHris.setLocalIdNumber(null);

        // Create the WaManagementHris, which fails.

        restWaManagementHrisMockMvc.perform(post("/api/wa-management-hrises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waManagementHris)))
            .andExpect(status().isBadRequest());

        List<WaManagementHris> waManagementHrisList = waManagementHrisRepository.findAll();
        assertThat(waManagementHrisList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaManagementHrises() throws Exception {
        // Initialize the database
        waManagementHrisRepository.saveAndFlush(waManagementHris);

        // Get all the waManagementHrisList
        restWaManagementHrisMockMvc.perform(get("/api/wa-management-hrises?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waManagementHris.getId().intValue())))
            .andExpect(jsonPath("$.[*].managementHrisCode").value(hasItem(DEFAULT_MANAGEMENT_HRIS_CODE)))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].managementHrisLabel").value(hasItem(DEFAULT_MANAGEMENT_HRIS_LABEL)))
            .andExpect(jsonPath("$.[*].entityManagementCode").value(hasItem(DEFAULT_ENTITY_MANAGEMENT_CODE)));
    }
    
    @Test
    @Transactional
    public void getWaManagementHris() throws Exception {
        // Initialize the database
        waManagementHrisRepository.saveAndFlush(waManagementHris);

        // Get the waManagementHris
        restWaManagementHrisMockMvc.perform(get("/api/wa-management-hrises/{id}", waManagementHris.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waManagementHris.getId().intValue()))
            .andExpect(jsonPath("$.managementHrisCode").value(DEFAULT_MANAGEMENT_HRIS_CODE))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.managementHrisLabel").value(DEFAULT_MANAGEMENT_HRIS_LABEL))
            .andExpect(jsonPath("$.entityManagementCode").value(DEFAULT_ENTITY_MANAGEMENT_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingWaManagementHris() throws Exception {
        // Get the waManagementHris
        restWaManagementHrisMockMvc.perform(get("/api/wa-management-hrises/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaManagementHris() throws Exception {
        // Initialize the database
        waManagementHrisRepository.saveAndFlush(waManagementHris);

        int databaseSizeBeforeUpdate = waManagementHrisRepository.findAll().size();

        // Update the waManagementHris
        WaManagementHris updatedWaManagementHris = waManagementHrisRepository.findById(waManagementHris.getId()).get();
        // Disconnect from session so that the updates on updatedWaManagementHris are not directly saved in db
        em.detach(updatedWaManagementHris);
        updatedWaManagementHris
            .managementHrisCode(UPDATED_MANAGEMENT_HRIS_CODE)
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .managementHrisLabel(UPDATED_MANAGEMENT_HRIS_LABEL)
            .entityManagementCode(UPDATED_ENTITY_MANAGEMENT_CODE);

        restWaManagementHrisMockMvc.perform(put("/api/wa-management-hrises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaManagementHris)))
            .andExpect(status().isOk());

        // Validate the WaManagementHris in the database
        List<WaManagementHris> waManagementHrisList = waManagementHrisRepository.findAll();
        assertThat(waManagementHrisList).hasSize(databaseSizeBeforeUpdate);
        WaManagementHris testWaManagementHris = waManagementHrisList.get(waManagementHrisList.size() - 1);
        assertThat(testWaManagementHris.getManagementHrisCode()).isEqualTo(UPDATED_MANAGEMENT_HRIS_CODE);
        assertThat(testWaManagementHris.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaManagementHris.getManagementHrisLabel()).isEqualTo(UPDATED_MANAGEMENT_HRIS_LABEL);
        assertThat(testWaManagementHris.getEntityManagementCode()).isEqualTo(UPDATED_ENTITY_MANAGEMENT_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaManagementHris() throws Exception {
        int databaseSizeBeforeUpdate = waManagementHrisRepository.findAll().size();

        // Create the WaManagementHris

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaManagementHrisMockMvc.perform(put("/api/wa-management-hrises")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waManagementHris)))
            .andExpect(status().isBadRequest());

        // Validate the WaManagementHris in the database
        List<WaManagementHris> waManagementHrisList = waManagementHrisRepository.findAll();
        assertThat(waManagementHrisList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaManagementHris() throws Exception {
        // Initialize the database
        waManagementHrisRepository.saveAndFlush(waManagementHris);

        int databaseSizeBeforeDelete = waManagementHrisRepository.findAll().size();

        // Delete the waManagementHris
        restWaManagementHrisMockMvc.perform(delete("/api/wa-management-hrises/{id}", waManagementHris.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaManagementHris> waManagementHrisList = waManagementHrisRepository.findAll();
        assertThat(waManagementHrisList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
