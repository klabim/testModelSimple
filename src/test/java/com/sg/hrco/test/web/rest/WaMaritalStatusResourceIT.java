package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaMaritalStatus;
import com.sg.hrco.test.repository.WaMaritalStatusRepository;

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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link WaMaritalStatusResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaMaritalStatusResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_MARITAL_STATUS_CODE = "AAAAAAAAAA";
    private static final String UPDATED_MARITAL_STATUS_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_MARITAL_STATUS_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_MARITAL_STATUS_LABEL = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private WaMaritalStatusRepository waMaritalStatusRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaMaritalStatusMockMvc;

    private WaMaritalStatus waMaritalStatus;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaMaritalStatus createEntity(EntityManager em) {
        WaMaritalStatus waMaritalStatus = new WaMaritalStatus()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .maritalStatusCode(DEFAULT_MARITAL_STATUS_CODE)
            .maritalStatusLabel(DEFAULT_MARITAL_STATUS_LABEL)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE);
        return waMaritalStatus;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaMaritalStatus createUpdatedEntity(EntityManager em) {
        WaMaritalStatus waMaritalStatus = new WaMaritalStatus()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .maritalStatusCode(UPDATED_MARITAL_STATUS_CODE)
            .maritalStatusLabel(UPDATED_MARITAL_STATUS_LABEL)
            .effectiveDate(UPDATED_EFFECTIVE_DATE);
        return waMaritalStatus;
    }

    @BeforeEach
    public void initTest() {
        waMaritalStatus = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaMaritalStatus() throws Exception {
        int databaseSizeBeforeCreate = waMaritalStatusRepository.findAll().size();

        // Create the WaMaritalStatus
        restWaMaritalStatusMockMvc.perform(post("/api/wa-marital-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waMaritalStatus)))
            .andExpect(status().isCreated());

        // Validate the WaMaritalStatus in the database
        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeCreate + 1);
        WaMaritalStatus testWaMaritalStatus = waMaritalStatusList.get(waMaritalStatusList.size() - 1);
        assertThat(testWaMaritalStatus.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaMaritalStatus.getMaritalStatusCode()).isEqualTo(DEFAULT_MARITAL_STATUS_CODE);
        assertThat(testWaMaritalStatus.getMaritalStatusLabel()).isEqualTo(DEFAULT_MARITAL_STATUS_LABEL);
        assertThat(testWaMaritalStatus.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
    }

    @Test
    @Transactional
    public void createWaMaritalStatusWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waMaritalStatusRepository.findAll().size();

        // Create the WaMaritalStatus with an existing ID
        waMaritalStatus.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaMaritalStatusMockMvc.perform(post("/api/wa-marital-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waMaritalStatus)))
            .andExpect(status().isBadRequest());

        // Validate the WaMaritalStatus in the database
        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkMaritalStatusCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waMaritalStatusRepository.findAll().size();
        // set the field null
        waMaritalStatus.setMaritalStatusCode(null);

        // Create the WaMaritalStatus, which fails.

        restWaMaritalStatusMockMvc.perform(post("/api/wa-marital-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waMaritalStatus)))
            .andExpect(status().isBadRequest());

        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waMaritalStatusRepository.findAll().size();
        // set the field null
        waMaritalStatus.setEffectiveDate(null);

        // Create the WaMaritalStatus, which fails.

        restWaMaritalStatusMockMvc.perform(post("/api/wa-marital-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waMaritalStatus)))
            .andExpect(status().isBadRequest());

        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaMaritalStatuses() throws Exception {
        // Initialize the database
        waMaritalStatusRepository.saveAndFlush(waMaritalStatus);

        // Get all the waMaritalStatusList
        restWaMaritalStatusMockMvc.perform(get("/api/wa-marital-statuses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waMaritalStatus.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].maritalStatusCode").value(hasItem(DEFAULT_MARITAL_STATUS_CODE)))
            .andExpect(jsonPath("$.[*].maritalStatusLabel").value(hasItem(DEFAULT_MARITAL_STATUS_LABEL)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getWaMaritalStatus() throws Exception {
        // Initialize the database
        waMaritalStatusRepository.saveAndFlush(waMaritalStatus);

        // Get the waMaritalStatus
        restWaMaritalStatusMockMvc.perform(get("/api/wa-marital-statuses/{id}", waMaritalStatus.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waMaritalStatus.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.maritalStatusCode").value(DEFAULT_MARITAL_STATUS_CODE))
            .andExpect(jsonPath("$.maritalStatusLabel").value(DEFAULT_MARITAL_STATUS_LABEL))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWaMaritalStatus() throws Exception {
        // Get the waMaritalStatus
        restWaMaritalStatusMockMvc.perform(get("/api/wa-marital-statuses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaMaritalStatus() throws Exception {
        // Initialize the database
        waMaritalStatusRepository.saveAndFlush(waMaritalStatus);

        int databaseSizeBeforeUpdate = waMaritalStatusRepository.findAll().size();

        // Update the waMaritalStatus
        WaMaritalStatus updatedWaMaritalStatus = waMaritalStatusRepository.findById(waMaritalStatus.getId()).get();
        // Disconnect from session so that the updates on updatedWaMaritalStatus are not directly saved in db
        em.detach(updatedWaMaritalStatus);
        updatedWaMaritalStatus
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .maritalStatusCode(UPDATED_MARITAL_STATUS_CODE)
            .maritalStatusLabel(UPDATED_MARITAL_STATUS_LABEL)
            .effectiveDate(UPDATED_EFFECTIVE_DATE);

        restWaMaritalStatusMockMvc.perform(put("/api/wa-marital-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaMaritalStatus)))
            .andExpect(status().isOk());

        // Validate the WaMaritalStatus in the database
        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeUpdate);
        WaMaritalStatus testWaMaritalStatus = waMaritalStatusList.get(waMaritalStatusList.size() - 1);
        assertThat(testWaMaritalStatus.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaMaritalStatus.getMaritalStatusCode()).isEqualTo(UPDATED_MARITAL_STATUS_CODE);
        assertThat(testWaMaritalStatus.getMaritalStatusLabel()).isEqualTo(UPDATED_MARITAL_STATUS_LABEL);
        assertThat(testWaMaritalStatus.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaMaritalStatus() throws Exception {
        int databaseSizeBeforeUpdate = waMaritalStatusRepository.findAll().size();

        // Create the WaMaritalStatus

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaMaritalStatusMockMvc.perform(put("/api/wa-marital-statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waMaritalStatus)))
            .andExpect(status().isBadRequest());

        // Validate the WaMaritalStatus in the database
        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaMaritalStatus() throws Exception {
        // Initialize the database
        waMaritalStatusRepository.saveAndFlush(waMaritalStatus);

        int databaseSizeBeforeDelete = waMaritalStatusRepository.findAll().size();

        // Delete the waMaritalStatus
        restWaMaritalStatusMockMvc.perform(delete("/api/wa-marital-statuses/{id}", waMaritalStatus.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaMaritalStatus> waMaritalStatusList = waMaritalStatusRepository.findAll();
        assertThat(waMaritalStatusList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
