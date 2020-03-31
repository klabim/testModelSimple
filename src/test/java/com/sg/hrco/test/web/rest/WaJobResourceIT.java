package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaJob;
import com.sg.hrco.test.repository.WaJobRepository;

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
 * Integration tests for the {@link WaJobResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaJobResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_POSITION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_POSITION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_POSITION_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_POSITION_LEVEL = "BBBBBBBBBB";

    private static final Instant DEFAULT_POSITION_ENTRY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_POSITION_ENTRY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_JOB_CODE_LOCAL_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_CODE_LOCAL_VALUE = "BBBBBBBBBB";

    private static final String DEFAULT_JOB_CODE_GROUPE_VALUE = "AAAAAAAAAA";
    private static final String UPDATED_JOB_CODE_GROUPE_VALUE = "BBBBBBBBBB";

    private static final Instant DEFAULT_JOB_ENTRY_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_JOB_ENTRY_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_COLLECTIVE_AGREEMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_COLLECTIVE_AGREEMENT_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_ORG_RELATIONSHIP = "AAAAAAAAAA";
    private static final String UPDATED_ORG_RELATIONSHIP = "BBBBBBBBBB";

    private static final String DEFAULT_PRESCRIPTEUR = "AAAAAAAAAA";
    private static final String UPDATED_PRESCRIPTEUR = "BBBBBBBBBB";

    @Autowired
    private WaJobRepository waJobRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaJobMockMvc;

    private WaJob waJob;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaJob createEntity(EntityManager em) {
        WaJob waJob = new WaJob()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .sequence(DEFAULT_SEQUENCE)
            .localPositionCode(DEFAULT_LOCAL_POSITION_CODE)
            .localPositionLevel(DEFAULT_LOCAL_POSITION_LEVEL)
            .positionEntryDate(DEFAULT_POSITION_ENTRY_DATE)
            .jobCodeLocalValue(DEFAULT_JOB_CODE_LOCAL_VALUE)
            .jobCodeGroupeValue(DEFAULT_JOB_CODE_GROUPE_VALUE)
            .jobEntryDate(DEFAULT_JOB_ENTRY_DATE)
            .localCollectiveAgreementCode(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_CODE)
            .localCollectiveAgreementLabel(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_LABEL)
            .orgRelationship(DEFAULT_ORG_RELATIONSHIP)
            .prescripteur(DEFAULT_PRESCRIPTEUR);
        return waJob;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaJob createUpdatedEntity(EntityManager em) {
        WaJob waJob = new WaJob()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .localPositionCode(UPDATED_LOCAL_POSITION_CODE)
            .localPositionLevel(UPDATED_LOCAL_POSITION_LEVEL)
            .positionEntryDate(UPDATED_POSITION_ENTRY_DATE)
            .jobCodeLocalValue(UPDATED_JOB_CODE_LOCAL_VALUE)
            .jobCodeGroupeValue(UPDATED_JOB_CODE_GROUPE_VALUE)
            .jobEntryDate(UPDATED_JOB_ENTRY_DATE)
            .localCollectiveAgreementCode(UPDATED_LOCAL_COLLECTIVE_AGREEMENT_CODE)
            .localCollectiveAgreementLabel(UPDATED_LOCAL_COLLECTIVE_AGREEMENT_LABEL)
            .orgRelationship(UPDATED_ORG_RELATIONSHIP)
            .prescripteur(UPDATED_PRESCRIPTEUR);
        return waJob;
    }

    @BeforeEach
    public void initTest() {
        waJob = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaJob() throws Exception {
        int databaseSizeBeforeCreate = waJobRepository.findAll().size();

        // Create the WaJob
        restWaJobMockMvc.perform(post("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waJob)))
            .andExpect(status().isCreated());

        // Validate the WaJob in the database
        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeCreate + 1);
        WaJob testWaJob = waJobList.get(waJobList.size() - 1);
        assertThat(testWaJob.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaJob.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testWaJob.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testWaJob.getLocalPositionCode()).isEqualTo(DEFAULT_LOCAL_POSITION_CODE);
        assertThat(testWaJob.getLocalPositionLevel()).isEqualTo(DEFAULT_LOCAL_POSITION_LEVEL);
        assertThat(testWaJob.getPositionEntryDate()).isEqualTo(DEFAULT_POSITION_ENTRY_DATE);
        assertThat(testWaJob.getJobCodeLocalValue()).isEqualTo(DEFAULT_JOB_CODE_LOCAL_VALUE);
        assertThat(testWaJob.getJobCodeGroupeValue()).isEqualTo(DEFAULT_JOB_CODE_GROUPE_VALUE);
        assertThat(testWaJob.getJobEntryDate()).isEqualTo(DEFAULT_JOB_ENTRY_DATE);
        assertThat(testWaJob.getLocalCollectiveAgreementCode()).isEqualTo(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_CODE);
        assertThat(testWaJob.getLocalCollectiveAgreementLabel()).isEqualTo(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_LABEL);
        assertThat(testWaJob.getOrgRelationship()).isEqualTo(DEFAULT_ORG_RELATIONSHIP);
        assertThat(testWaJob.getPrescripteur()).isEqualTo(DEFAULT_PRESCRIPTEUR);
    }

    @Test
    @Transactional
    public void createWaJobWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waJobRepository.findAll().size();

        // Create the WaJob with an existing ID
        waJob.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaJobMockMvc.perform(post("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waJob)))
            .andExpect(status().isBadRequest());

        // Validate the WaJob in the database
        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waJobRepository.findAll().size();
        // set the field null
        waJob.setEffectiveDate(null);

        // Create the WaJob, which fails.

        restWaJobMockMvc.perform(post("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waJob)))
            .andExpect(status().isBadRequest());

        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSequenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = waJobRepository.findAll().size();
        // set the field null
        waJob.setSequence(null);

        // Create the WaJob, which fails.

        restWaJobMockMvc.perform(post("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waJob)))
            .andExpect(status().isBadRequest());

        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalPositionCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waJobRepository.findAll().size();
        // set the field null
        waJob.setLocalPositionCode(null);

        // Create the WaJob, which fails.

        restWaJobMockMvc.perform(post("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waJob)))
            .andExpect(status().isBadRequest());

        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaJobs() throws Exception {
        // Initialize the database
        waJobRepository.saveAndFlush(waJob);

        // Get all the waJobList
        restWaJobMockMvc.perform(get("/api/wa-jobs?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waJob.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
            .andExpect(jsonPath("$.[*].localPositionCode").value(hasItem(DEFAULT_LOCAL_POSITION_CODE)))
            .andExpect(jsonPath("$.[*].localPositionLevel").value(hasItem(DEFAULT_LOCAL_POSITION_LEVEL)))
            .andExpect(jsonPath("$.[*].positionEntryDate").value(hasItem(DEFAULT_POSITION_ENTRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].jobCodeLocalValue").value(hasItem(DEFAULT_JOB_CODE_LOCAL_VALUE)))
            .andExpect(jsonPath("$.[*].jobCodeGroupeValue").value(hasItem(DEFAULT_JOB_CODE_GROUPE_VALUE)))
            .andExpect(jsonPath("$.[*].jobEntryDate").value(hasItem(DEFAULT_JOB_ENTRY_DATE.toString())))
            .andExpect(jsonPath("$.[*].localCollectiveAgreementCode").value(hasItem(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_CODE)))
            .andExpect(jsonPath("$.[*].localCollectiveAgreementLabel").value(hasItem(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_LABEL)))
            .andExpect(jsonPath("$.[*].orgRelationship").value(hasItem(DEFAULT_ORG_RELATIONSHIP)))
            .andExpect(jsonPath("$.[*].prescripteur").value(hasItem(DEFAULT_PRESCRIPTEUR)));
    }
    
    @Test
    @Transactional
    public void getWaJob() throws Exception {
        // Initialize the database
        waJobRepository.saveAndFlush(waJob);

        // Get the waJob
        restWaJobMockMvc.perform(get("/api/wa-jobs/{id}", waJob.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waJob.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.localPositionCode").value(DEFAULT_LOCAL_POSITION_CODE))
            .andExpect(jsonPath("$.localPositionLevel").value(DEFAULT_LOCAL_POSITION_LEVEL))
            .andExpect(jsonPath("$.positionEntryDate").value(DEFAULT_POSITION_ENTRY_DATE.toString()))
            .andExpect(jsonPath("$.jobCodeLocalValue").value(DEFAULT_JOB_CODE_LOCAL_VALUE))
            .andExpect(jsonPath("$.jobCodeGroupeValue").value(DEFAULT_JOB_CODE_GROUPE_VALUE))
            .andExpect(jsonPath("$.jobEntryDate").value(DEFAULT_JOB_ENTRY_DATE.toString()))
            .andExpect(jsonPath("$.localCollectiveAgreementCode").value(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_CODE))
            .andExpect(jsonPath("$.localCollectiveAgreementLabel").value(DEFAULT_LOCAL_COLLECTIVE_AGREEMENT_LABEL))
            .andExpect(jsonPath("$.orgRelationship").value(DEFAULT_ORG_RELATIONSHIP))
            .andExpect(jsonPath("$.prescripteur").value(DEFAULT_PRESCRIPTEUR));
    }

    @Test
    @Transactional
    public void getNonExistingWaJob() throws Exception {
        // Get the waJob
        restWaJobMockMvc.perform(get("/api/wa-jobs/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaJob() throws Exception {
        // Initialize the database
        waJobRepository.saveAndFlush(waJob);

        int databaseSizeBeforeUpdate = waJobRepository.findAll().size();

        // Update the waJob
        WaJob updatedWaJob = waJobRepository.findById(waJob.getId()).get();
        // Disconnect from session so that the updates on updatedWaJob are not directly saved in db
        em.detach(updatedWaJob);
        updatedWaJob
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .localPositionCode(UPDATED_LOCAL_POSITION_CODE)
            .localPositionLevel(UPDATED_LOCAL_POSITION_LEVEL)
            .positionEntryDate(UPDATED_POSITION_ENTRY_DATE)
            .jobCodeLocalValue(UPDATED_JOB_CODE_LOCAL_VALUE)
            .jobCodeGroupeValue(UPDATED_JOB_CODE_GROUPE_VALUE)
            .jobEntryDate(UPDATED_JOB_ENTRY_DATE)
            .localCollectiveAgreementCode(UPDATED_LOCAL_COLLECTIVE_AGREEMENT_CODE)
            .localCollectiveAgreementLabel(UPDATED_LOCAL_COLLECTIVE_AGREEMENT_LABEL)
            .orgRelationship(UPDATED_ORG_RELATIONSHIP)
            .prescripteur(UPDATED_PRESCRIPTEUR);

        restWaJobMockMvc.perform(put("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaJob)))
            .andExpect(status().isOk());

        // Validate the WaJob in the database
        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeUpdate);
        WaJob testWaJob = waJobList.get(waJobList.size() - 1);
        assertThat(testWaJob.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaJob.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testWaJob.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testWaJob.getLocalPositionCode()).isEqualTo(UPDATED_LOCAL_POSITION_CODE);
        assertThat(testWaJob.getLocalPositionLevel()).isEqualTo(UPDATED_LOCAL_POSITION_LEVEL);
        assertThat(testWaJob.getPositionEntryDate()).isEqualTo(UPDATED_POSITION_ENTRY_DATE);
        assertThat(testWaJob.getJobCodeLocalValue()).isEqualTo(UPDATED_JOB_CODE_LOCAL_VALUE);
        assertThat(testWaJob.getJobCodeGroupeValue()).isEqualTo(UPDATED_JOB_CODE_GROUPE_VALUE);
        assertThat(testWaJob.getJobEntryDate()).isEqualTo(UPDATED_JOB_ENTRY_DATE);
        assertThat(testWaJob.getLocalCollectiveAgreementCode()).isEqualTo(UPDATED_LOCAL_COLLECTIVE_AGREEMENT_CODE);
        assertThat(testWaJob.getLocalCollectiveAgreementLabel()).isEqualTo(UPDATED_LOCAL_COLLECTIVE_AGREEMENT_LABEL);
        assertThat(testWaJob.getOrgRelationship()).isEqualTo(UPDATED_ORG_RELATIONSHIP);
        assertThat(testWaJob.getPrescripteur()).isEqualTo(UPDATED_PRESCRIPTEUR);
    }

    @Test
    @Transactional
    public void updateNonExistingWaJob() throws Exception {
        int databaseSizeBeforeUpdate = waJobRepository.findAll().size();

        // Create the WaJob

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaJobMockMvc.perform(put("/api/wa-jobs")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waJob)))
            .andExpect(status().isBadRequest());

        // Validate the WaJob in the database
        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaJob() throws Exception {
        // Initialize the database
        waJobRepository.saveAndFlush(waJob);

        int databaseSizeBeforeDelete = waJobRepository.findAll().size();

        // Delete the waJob
        restWaJobMockMvc.perform(delete("/api/wa-jobs/{id}", waJob.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaJob> waJobList = waJobRepository.findAll();
        assertThat(waJobList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
