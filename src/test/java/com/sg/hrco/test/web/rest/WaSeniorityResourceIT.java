package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaSeniority;
import com.sg.hrco.test.repository.WaSeniorityRepository;

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
 * Integration tests for the {@link WaSeniorityResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaSeniorityResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_GROUP_SG_DATE_OF_ENTRY = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_GROUP_SG_DATE_OF_ENTRY = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_SG_SENIORITY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_SG_SENIORITY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final Instant DEFAULT_HIRE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_HIRE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_BANKING_SECTOR_SENIORITY = "AAAAAAAAAA";
    private static final String UPDATED_BANKING_SECTOR_SENIORITY = "BBBBBBBBBB";

    private static final Instant DEFAULT_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private WaSeniorityRepository waSeniorityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaSeniorityMockMvc;

    private WaSeniority waSeniority;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaSeniority createEntity(EntityManager em) {
        WaSeniority waSeniority = new WaSeniority()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .groupSgDateOfEntry(DEFAULT_GROUP_SG_DATE_OF_ENTRY)
            .sgSeniorityDate(DEFAULT_SG_SENIORITY_DATE)
            .hireDate(DEFAULT_HIRE_DATE)
            .bankingSectorSeniority(DEFAULT_BANKING_SECTOR_SENIORITY)
            .endingOfTrialPeriodEstimatedDate(DEFAULT_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE);
        return waSeniority;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaSeniority createUpdatedEntity(EntityManager em) {
        WaSeniority waSeniority = new WaSeniority()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .groupSgDateOfEntry(UPDATED_GROUP_SG_DATE_OF_ENTRY)
            .sgSeniorityDate(UPDATED_SG_SENIORITY_DATE)
            .hireDate(UPDATED_HIRE_DATE)
            .bankingSectorSeniority(UPDATED_BANKING_SECTOR_SENIORITY)
            .endingOfTrialPeriodEstimatedDate(UPDATED_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE);
        return waSeniority;
    }

    @BeforeEach
    public void initTest() {
        waSeniority = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaSeniority() throws Exception {
        int databaseSizeBeforeCreate = waSeniorityRepository.findAll().size();

        // Create the WaSeniority
        restWaSeniorityMockMvc.perform(post("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waSeniority)))
            .andExpect(status().isCreated());

        // Validate the WaSeniority in the database
        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeCreate + 1);
        WaSeniority testWaSeniority = waSeniorityList.get(waSeniorityList.size() - 1);
        assertThat(testWaSeniority.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaSeniority.getGroupSgDateOfEntry()).isEqualTo(DEFAULT_GROUP_SG_DATE_OF_ENTRY);
        assertThat(testWaSeniority.getSgSeniorityDate()).isEqualTo(DEFAULT_SG_SENIORITY_DATE);
        assertThat(testWaSeniority.getHireDate()).isEqualTo(DEFAULT_HIRE_DATE);
        assertThat(testWaSeniority.getBankingSectorSeniority()).isEqualTo(DEFAULT_BANKING_SECTOR_SENIORITY);
        assertThat(testWaSeniority.getEndingOfTrialPeriodEstimatedDate()).isEqualTo(DEFAULT_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE);
    }

    @Test
    @Transactional
    public void createWaSeniorityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waSeniorityRepository.findAll().size();

        // Create the WaSeniority with an existing ID
        waSeniority.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaSeniorityMockMvc.perform(post("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waSeniority)))
            .andExpect(status().isBadRequest());

        // Validate the WaSeniority in the database
        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkGroupSgDateOfEntryIsRequired() throws Exception {
        int databaseSizeBeforeTest = waSeniorityRepository.findAll().size();
        // set the field null
        waSeniority.setGroupSgDateOfEntry(null);

        // Create the WaSeniority, which fails.

        restWaSeniorityMockMvc.perform(post("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waSeniority)))
            .andExpect(status().isBadRequest());

        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSgSeniorityDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waSeniorityRepository.findAll().size();
        // set the field null
        waSeniority.setSgSeniorityDate(null);

        // Create the WaSeniority, which fails.

        restWaSeniorityMockMvc.perform(post("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waSeniority)))
            .andExpect(status().isBadRequest());

        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHireDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waSeniorityRepository.findAll().size();
        // set the field null
        waSeniority.setHireDate(null);

        // Create the WaSeniority, which fails.

        restWaSeniorityMockMvc.perform(post("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waSeniority)))
            .andExpect(status().isBadRequest());

        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaSeniorities() throws Exception {
        // Initialize the database
        waSeniorityRepository.saveAndFlush(waSeniority);

        // Get all the waSeniorityList
        restWaSeniorityMockMvc.perform(get("/api/wa-seniorities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waSeniority.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].groupSgDateOfEntry").value(hasItem(DEFAULT_GROUP_SG_DATE_OF_ENTRY.toString())))
            .andExpect(jsonPath("$.[*].sgSeniorityDate").value(hasItem(DEFAULT_SG_SENIORITY_DATE.toString())))
            .andExpect(jsonPath("$.[*].hireDate").value(hasItem(DEFAULT_HIRE_DATE.toString())))
            .andExpect(jsonPath("$.[*].bankingSectorSeniority").value(hasItem(DEFAULT_BANKING_SECTOR_SENIORITY)))
            .andExpect(jsonPath("$.[*].endingOfTrialPeriodEstimatedDate").value(hasItem(DEFAULT_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getWaSeniority() throws Exception {
        // Initialize the database
        waSeniorityRepository.saveAndFlush(waSeniority);

        // Get the waSeniority
        restWaSeniorityMockMvc.perform(get("/api/wa-seniorities/{id}", waSeniority.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waSeniority.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.groupSgDateOfEntry").value(DEFAULT_GROUP_SG_DATE_OF_ENTRY.toString()))
            .andExpect(jsonPath("$.sgSeniorityDate").value(DEFAULT_SG_SENIORITY_DATE.toString()))
            .andExpect(jsonPath("$.hireDate").value(DEFAULT_HIRE_DATE.toString()))
            .andExpect(jsonPath("$.bankingSectorSeniority").value(DEFAULT_BANKING_SECTOR_SENIORITY))
            .andExpect(jsonPath("$.endingOfTrialPeriodEstimatedDate").value(DEFAULT_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWaSeniority() throws Exception {
        // Get the waSeniority
        restWaSeniorityMockMvc.perform(get("/api/wa-seniorities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaSeniority() throws Exception {
        // Initialize the database
        waSeniorityRepository.saveAndFlush(waSeniority);

        int databaseSizeBeforeUpdate = waSeniorityRepository.findAll().size();

        // Update the waSeniority
        WaSeniority updatedWaSeniority = waSeniorityRepository.findById(waSeniority.getId()).get();
        // Disconnect from session so that the updates on updatedWaSeniority are not directly saved in db
        em.detach(updatedWaSeniority);
        updatedWaSeniority
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .groupSgDateOfEntry(UPDATED_GROUP_SG_DATE_OF_ENTRY)
            .sgSeniorityDate(UPDATED_SG_SENIORITY_DATE)
            .hireDate(UPDATED_HIRE_DATE)
            .bankingSectorSeniority(UPDATED_BANKING_SECTOR_SENIORITY)
            .endingOfTrialPeriodEstimatedDate(UPDATED_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE);

        restWaSeniorityMockMvc.perform(put("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaSeniority)))
            .andExpect(status().isOk());

        // Validate the WaSeniority in the database
        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeUpdate);
        WaSeniority testWaSeniority = waSeniorityList.get(waSeniorityList.size() - 1);
        assertThat(testWaSeniority.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaSeniority.getGroupSgDateOfEntry()).isEqualTo(UPDATED_GROUP_SG_DATE_OF_ENTRY);
        assertThat(testWaSeniority.getSgSeniorityDate()).isEqualTo(UPDATED_SG_SENIORITY_DATE);
        assertThat(testWaSeniority.getHireDate()).isEqualTo(UPDATED_HIRE_DATE);
        assertThat(testWaSeniority.getBankingSectorSeniority()).isEqualTo(UPDATED_BANKING_SECTOR_SENIORITY);
        assertThat(testWaSeniority.getEndingOfTrialPeriodEstimatedDate()).isEqualTo(UPDATED_ENDING_OF_TRIAL_PERIOD_ESTIMATED_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaSeniority() throws Exception {
        int databaseSizeBeforeUpdate = waSeniorityRepository.findAll().size();

        // Create the WaSeniority

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaSeniorityMockMvc.perform(put("/api/wa-seniorities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waSeniority)))
            .andExpect(status().isBadRequest());

        // Validate the WaSeniority in the database
        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaSeniority() throws Exception {
        // Initialize the database
        waSeniorityRepository.saveAndFlush(waSeniority);

        int databaseSizeBeforeDelete = waSeniorityRepository.findAll().size();

        // Delete the waSeniority
        restWaSeniorityMockMvc.perform(delete("/api/wa-seniorities/{id}", waSeniority.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaSeniority> waSeniorityList = waSeniorityRepository.findAll();
        assertThat(waSeniorityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
