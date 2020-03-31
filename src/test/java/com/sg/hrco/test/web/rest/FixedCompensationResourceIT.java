package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.FixedCompensation;
import com.sg.hrco.test.repository.FixedCompensationRepository;

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
 * Integration tests for the {@link FixedCompensationResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class FixedCompensationResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_TYPE_GROUP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_TYPE_GROUP_LABEL = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_BEGINNING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_BEGINNING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FIXED_COMPENSATION_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_FIXED_COMPENSATION_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_AMOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_COMPENSATION_FREQUENCY = "AAAAAAAAAA";
    private static final String UPDATED_COMPENSATION_FREQUENCY = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE = "BBBBBBBBBB";

    @Autowired
    private FixedCompensationRepository fixedCompensationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFixedCompensationMockMvc;

    private FixedCompensation fixedCompensation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FixedCompensation createEntity(EntityManager em) {
        FixedCompensation fixedCompensation = new FixedCompensation()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .fixedCompensationTypeGroupCode(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_CODE)
            .fixedCompensationTypeGroupLabel(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_LABEL)
            .effectiveBeginningDate(DEFAULT_EFFECTIVE_BEGINNING_DATE)
            .fixedCompensationCurrency(DEFAULT_FIXED_COMPENSATION_CURRENCY)
            .fixedCompensationLocalTypeCode(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_CODE)
            .fixedCompensationLocalTypeLabel(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_LABEL)
            .fixedCompensationAmount(DEFAULT_FIXED_COMPENSATION_AMOUNT)
            .compensationFrequency(DEFAULT_COMPENSATION_FREQUENCY)
            .effectiveEndDate(DEFAULT_EFFECTIVE_END_DATE)
            .fixedCompensationEffectiveSequence(DEFAULT_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE);
        return fixedCompensation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static FixedCompensation createUpdatedEntity(EntityManager em) {
        FixedCompensation fixedCompensation = new FixedCompensation()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .fixedCompensationTypeGroupCode(UPDATED_FIXED_COMPENSATION_TYPE_GROUP_CODE)
            .fixedCompensationTypeGroupLabel(UPDATED_FIXED_COMPENSATION_TYPE_GROUP_LABEL)
            .effectiveBeginningDate(UPDATED_EFFECTIVE_BEGINNING_DATE)
            .fixedCompensationCurrency(UPDATED_FIXED_COMPENSATION_CURRENCY)
            .fixedCompensationLocalTypeCode(UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_CODE)
            .fixedCompensationLocalTypeLabel(UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_LABEL)
            .fixedCompensationAmount(UPDATED_FIXED_COMPENSATION_AMOUNT)
            .compensationFrequency(UPDATED_COMPENSATION_FREQUENCY)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .fixedCompensationEffectiveSequence(UPDATED_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE);
        return fixedCompensation;
    }

    @BeforeEach
    public void initTest() {
        fixedCompensation = createEntity(em);
    }

    @Test
    @Transactional
    public void createFixedCompensation() throws Exception {
        int databaseSizeBeforeCreate = fixedCompensationRepository.findAll().size();

        // Create the FixedCompensation
        restFixedCompensationMockMvc.perform(post("/api/fixed-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fixedCompensation)))
            .andExpect(status().isCreated());

        // Validate the FixedCompensation in the database
        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeCreate + 1);
        FixedCompensation testFixedCompensation = fixedCompensationList.get(fixedCompensationList.size() - 1);
        assertThat(testFixedCompensation.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testFixedCompensation.getFixedCompensationTypeGroupCode()).isEqualTo(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_CODE);
        assertThat(testFixedCompensation.getFixedCompensationTypeGroupLabel()).isEqualTo(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_LABEL);
        assertThat(testFixedCompensation.getEffectiveBeginningDate()).isEqualTo(DEFAULT_EFFECTIVE_BEGINNING_DATE);
        assertThat(testFixedCompensation.getFixedCompensationCurrency()).isEqualTo(DEFAULT_FIXED_COMPENSATION_CURRENCY);
        assertThat(testFixedCompensation.getFixedCompensationLocalTypeCode()).isEqualTo(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_CODE);
        assertThat(testFixedCompensation.getFixedCompensationLocalTypeLabel()).isEqualTo(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_LABEL);
        assertThat(testFixedCompensation.getFixedCompensationAmount()).isEqualTo(DEFAULT_FIXED_COMPENSATION_AMOUNT);
        assertThat(testFixedCompensation.getCompensationFrequency()).isEqualTo(DEFAULT_COMPENSATION_FREQUENCY);
        assertThat(testFixedCompensation.getEffectiveEndDate()).isEqualTo(DEFAULT_EFFECTIVE_END_DATE);
        assertThat(testFixedCompensation.getFixedCompensationEffectiveSequence()).isEqualTo(DEFAULT_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE);
    }

    @Test
    @Transactional
    public void createFixedCompensationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fixedCompensationRepository.findAll().size();

        // Create the FixedCompensation with an existing ID
        fixedCompensation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFixedCompensationMockMvc.perform(post("/api/fixed-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fixedCompensation)))
            .andExpect(status().isBadRequest());

        // Validate the FixedCompensation in the database
        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkFixedCompensationTypeGroupCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = fixedCompensationRepository.findAll().size();
        // set the field null
        fixedCompensation.setFixedCompensationTypeGroupCode(null);

        // Create the FixedCompensation, which fails.

        restFixedCompensationMockMvc.perform(post("/api/fixed-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fixedCompensation)))
            .andExpect(status().isBadRequest());

        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectiveBeginningDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = fixedCompensationRepository.findAll().size();
        // set the field null
        fixedCompensation.setEffectiveBeginningDate(null);

        // Create the FixedCompensation, which fails.

        restFixedCompensationMockMvc.perform(post("/api/fixed-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fixedCompensation)))
            .andExpect(status().isBadRequest());

        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllFixedCompensations() throws Exception {
        // Initialize the database
        fixedCompensationRepository.saveAndFlush(fixedCompensation);

        // Get all the fixedCompensationList
        restFixedCompensationMockMvc.perform(get("/api/fixed-compensations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fixedCompensation.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].fixedCompensationTypeGroupCode").value(hasItem(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_CODE)))
            .andExpect(jsonPath("$.[*].fixedCompensationTypeGroupLabel").value(hasItem(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_LABEL)))
            .andExpect(jsonPath("$.[*].effectiveBeginningDate").value(hasItem(DEFAULT_EFFECTIVE_BEGINNING_DATE.toString())))
            .andExpect(jsonPath("$.[*].fixedCompensationCurrency").value(hasItem(DEFAULT_FIXED_COMPENSATION_CURRENCY)))
            .andExpect(jsonPath("$.[*].fixedCompensationLocalTypeCode").value(hasItem(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].fixedCompensationLocalTypeLabel").value(hasItem(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_LABEL)))
            .andExpect(jsonPath("$.[*].fixedCompensationAmount").value(hasItem(DEFAULT_FIXED_COMPENSATION_AMOUNT)))
            .andExpect(jsonPath("$.[*].compensationFrequency").value(hasItem(DEFAULT_COMPENSATION_FREQUENCY)))
            .andExpect(jsonPath("$.[*].effectiveEndDate").value(hasItem(DEFAULT_EFFECTIVE_END_DATE.toString())))
            .andExpect(jsonPath("$.[*].fixedCompensationEffectiveSequence").value(hasItem(DEFAULT_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE)));
    }
    
    @Test
    @Transactional
    public void getFixedCompensation() throws Exception {
        // Initialize the database
        fixedCompensationRepository.saveAndFlush(fixedCompensation);

        // Get the fixedCompensation
        restFixedCompensationMockMvc.perform(get("/api/fixed-compensations/{id}", fixedCompensation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fixedCompensation.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.fixedCompensationTypeGroupCode").value(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_CODE))
            .andExpect(jsonPath("$.fixedCompensationTypeGroupLabel").value(DEFAULT_FIXED_COMPENSATION_TYPE_GROUP_LABEL))
            .andExpect(jsonPath("$.effectiveBeginningDate").value(DEFAULT_EFFECTIVE_BEGINNING_DATE.toString()))
            .andExpect(jsonPath("$.fixedCompensationCurrency").value(DEFAULT_FIXED_COMPENSATION_CURRENCY))
            .andExpect(jsonPath("$.fixedCompensationLocalTypeCode").value(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_CODE))
            .andExpect(jsonPath("$.fixedCompensationLocalTypeLabel").value(DEFAULT_FIXED_COMPENSATION_LOCAL_TYPE_LABEL))
            .andExpect(jsonPath("$.fixedCompensationAmount").value(DEFAULT_FIXED_COMPENSATION_AMOUNT))
            .andExpect(jsonPath("$.compensationFrequency").value(DEFAULT_COMPENSATION_FREQUENCY))
            .andExpect(jsonPath("$.effectiveEndDate").value(DEFAULT_EFFECTIVE_END_DATE.toString()))
            .andExpect(jsonPath("$.fixedCompensationEffectiveSequence").value(DEFAULT_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE));
    }

    @Test
    @Transactional
    public void getNonExistingFixedCompensation() throws Exception {
        // Get the fixedCompensation
        restFixedCompensationMockMvc.perform(get("/api/fixed-compensations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFixedCompensation() throws Exception {
        // Initialize the database
        fixedCompensationRepository.saveAndFlush(fixedCompensation);

        int databaseSizeBeforeUpdate = fixedCompensationRepository.findAll().size();

        // Update the fixedCompensation
        FixedCompensation updatedFixedCompensation = fixedCompensationRepository.findById(fixedCompensation.getId()).get();
        // Disconnect from session so that the updates on updatedFixedCompensation are not directly saved in db
        em.detach(updatedFixedCompensation);
        updatedFixedCompensation
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .fixedCompensationTypeGroupCode(UPDATED_FIXED_COMPENSATION_TYPE_GROUP_CODE)
            .fixedCompensationTypeGroupLabel(UPDATED_FIXED_COMPENSATION_TYPE_GROUP_LABEL)
            .effectiveBeginningDate(UPDATED_EFFECTIVE_BEGINNING_DATE)
            .fixedCompensationCurrency(UPDATED_FIXED_COMPENSATION_CURRENCY)
            .fixedCompensationLocalTypeCode(UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_CODE)
            .fixedCompensationLocalTypeLabel(UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_LABEL)
            .fixedCompensationAmount(UPDATED_FIXED_COMPENSATION_AMOUNT)
            .compensationFrequency(UPDATED_COMPENSATION_FREQUENCY)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE)
            .fixedCompensationEffectiveSequence(UPDATED_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE);

        restFixedCompensationMockMvc.perform(put("/api/fixed-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedFixedCompensation)))
            .andExpect(status().isOk());

        // Validate the FixedCompensation in the database
        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeUpdate);
        FixedCompensation testFixedCompensation = fixedCompensationList.get(fixedCompensationList.size() - 1);
        assertThat(testFixedCompensation.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testFixedCompensation.getFixedCompensationTypeGroupCode()).isEqualTo(UPDATED_FIXED_COMPENSATION_TYPE_GROUP_CODE);
        assertThat(testFixedCompensation.getFixedCompensationTypeGroupLabel()).isEqualTo(UPDATED_FIXED_COMPENSATION_TYPE_GROUP_LABEL);
        assertThat(testFixedCompensation.getEffectiveBeginningDate()).isEqualTo(UPDATED_EFFECTIVE_BEGINNING_DATE);
        assertThat(testFixedCompensation.getFixedCompensationCurrency()).isEqualTo(UPDATED_FIXED_COMPENSATION_CURRENCY);
        assertThat(testFixedCompensation.getFixedCompensationLocalTypeCode()).isEqualTo(UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_CODE);
        assertThat(testFixedCompensation.getFixedCompensationLocalTypeLabel()).isEqualTo(UPDATED_FIXED_COMPENSATION_LOCAL_TYPE_LABEL);
        assertThat(testFixedCompensation.getFixedCompensationAmount()).isEqualTo(UPDATED_FIXED_COMPENSATION_AMOUNT);
        assertThat(testFixedCompensation.getCompensationFrequency()).isEqualTo(UPDATED_COMPENSATION_FREQUENCY);
        assertThat(testFixedCompensation.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
        assertThat(testFixedCompensation.getFixedCompensationEffectiveSequence()).isEqualTo(UPDATED_FIXED_COMPENSATION_EFFECTIVE_SEQUENCE);
    }

    @Test
    @Transactional
    public void updateNonExistingFixedCompensation() throws Exception {
        int databaseSizeBeforeUpdate = fixedCompensationRepository.findAll().size();

        // Create the FixedCompensation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFixedCompensationMockMvc.perform(put("/api/fixed-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fixedCompensation)))
            .andExpect(status().isBadRequest());

        // Validate the FixedCompensation in the database
        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFixedCompensation() throws Exception {
        // Initialize the database
        fixedCompensationRepository.saveAndFlush(fixedCompensation);

        int databaseSizeBeforeDelete = fixedCompensationRepository.findAll().size();

        // Delete the fixedCompensation
        restFixedCompensationMockMvc.perform(delete("/api/fixed-compensations/{id}", fixedCompensation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<FixedCompensation> fixedCompensationList = fixedCompensationRepository.findAll();
        assertThat(fixedCompensationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
