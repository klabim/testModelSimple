package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.VariableCompensation;
import com.sg.hrco.test.repository.VariableCompensationRepository;

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
 * Integration tests for the {@link VariableCompensationResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class VariableCompensationResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_BEGINNING_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_BEGINNING_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_VARIABLE_COMPENSATION_CURRENCY = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE_COMPENSATION_CURRENCY = "BBBBBBBBBB";

    private static final String DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_VARIABLE_COMPENSATION_AMOUNT = "AAAAAAAAAA";
    private static final String UPDATED_VARIABLE_COMPENSATION_AMOUNT = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_END_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_END_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private VariableCompensationRepository variableCompensationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVariableCompensationMockMvc;

    private VariableCompensation variableCompensation;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VariableCompensation createEntity(EntityManager em) {
        VariableCompensation variableCompensation = new VariableCompensation()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .variableCompensationTypeGroupCode(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_CODE)
            .variableCompensationTypeGroupLabel(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL)
            .effectiveBeginningDate(DEFAULT_EFFECTIVE_BEGINNING_DATE)
            .variableCompensationCurrency(DEFAULT_VARIABLE_COMPENSATION_CURRENCY)
            .variableCompensationLocalTypeCode(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE)
            .variableCompensationLocalTypeLabel(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL)
            .variableCompensationAmount(DEFAULT_VARIABLE_COMPENSATION_AMOUNT)
            .effectiveEndDate(DEFAULT_EFFECTIVE_END_DATE);
        return variableCompensation;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static VariableCompensation createUpdatedEntity(EntityManager em) {
        VariableCompensation variableCompensation = new VariableCompensation()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .variableCompensationTypeGroupCode(UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_CODE)
            .variableCompensationTypeGroupLabel(UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL)
            .effectiveBeginningDate(UPDATED_EFFECTIVE_BEGINNING_DATE)
            .variableCompensationCurrency(UPDATED_VARIABLE_COMPENSATION_CURRENCY)
            .variableCompensationLocalTypeCode(UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE)
            .variableCompensationLocalTypeLabel(UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL)
            .variableCompensationAmount(UPDATED_VARIABLE_COMPENSATION_AMOUNT)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE);
        return variableCompensation;
    }

    @BeforeEach
    public void initTest() {
        variableCompensation = createEntity(em);
    }

    @Test
    @Transactional
    public void createVariableCompensation() throws Exception {
        int databaseSizeBeforeCreate = variableCompensationRepository.findAll().size();

        // Create the VariableCompensation
        restVariableCompensationMockMvc.perform(post("/api/variable-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(variableCompensation)))
            .andExpect(status().isCreated());

        // Validate the VariableCompensation in the database
        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeCreate + 1);
        VariableCompensation testVariableCompensation = variableCompensationList.get(variableCompensationList.size() - 1);
        assertThat(testVariableCompensation.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testVariableCompensation.getVariableCompensationTypeGroupCode()).isEqualTo(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_CODE);
        assertThat(testVariableCompensation.getVariableCompensationTypeGroupLabel()).isEqualTo(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL);
        assertThat(testVariableCompensation.getEffectiveBeginningDate()).isEqualTo(DEFAULT_EFFECTIVE_BEGINNING_DATE);
        assertThat(testVariableCompensation.getVariableCompensationCurrency()).isEqualTo(DEFAULT_VARIABLE_COMPENSATION_CURRENCY);
        assertThat(testVariableCompensation.getVariableCompensationLocalTypeCode()).isEqualTo(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE);
        assertThat(testVariableCompensation.getVariableCompensationLocalTypeLabel()).isEqualTo(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL);
        assertThat(testVariableCompensation.getVariableCompensationAmount()).isEqualTo(DEFAULT_VARIABLE_COMPENSATION_AMOUNT);
        assertThat(testVariableCompensation.getEffectiveEndDate()).isEqualTo(DEFAULT_EFFECTIVE_END_DATE);
    }

    @Test
    @Transactional
    public void createVariableCompensationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = variableCompensationRepository.findAll().size();

        // Create the VariableCompensation with an existing ID
        variableCompensation.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVariableCompensationMockMvc.perform(post("/api/variable-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(variableCompensation)))
            .andExpect(status().isBadRequest());

        // Validate the VariableCompensation in the database
        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkVariableCompensationTypeGroupCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = variableCompensationRepository.findAll().size();
        // set the field null
        variableCompensation.setVariableCompensationTypeGroupCode(null);

        // Create the VariableCompensation, which fails.

        restVariableCompensationMockMvc.perform(post("/api/variable-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(variableCompensation)))
            .andExpect(status().isBadRequest());

        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectiveBeginningDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = variableCompensationRepository.findAll().size();
        // set the field null
        variableCompensation.setEffectiveBeginningDate(null);

        // Create the VariableCompensation, which fails.

        restVariableCompensationMockMvc.perform(post("/api/variable-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(variableCompensation)))
            .andExpect(status().isBadRequest());

        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllVariableCompensations() throws Exception {
        // Initialize the database
        variableCompensationRepository.saveAndFlush(variableCompensation);

        // Get all the variableCompensationList
        restVariableCompensationMockMvc.perform(get("/api/variable-compensations?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(variableCompensation.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].variableCompensationTypeGroupCode").value(hasItem(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_CODE)))
            .andExpect(jsonPath("$.[*].variableCompensationTypeGroupLabel").value(hasItem(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL)))
            .andExpect(jsonPath("$.[*].effectiveBeginningDate").value(hasItem(DEFAULT_EFFECTIVE_BEGINNING_DATE.toString())))
            .andExpect(jsonPath("$.[*].variableCompensationCurrency").value(hasItem(DEFAULT_VARIABLE_COMPENSATION_CURRENCY)))
            .andExpect(jsonPath("$.[*].variableCompensationLocalTypeCode").value(hasItem(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE)))
            .andExpect(jsonPath("$.[*].variableCompensationLocalTypeLabel").value(hasItem(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL)))
            .andExpect(jsonPath("$.[*].variableCompensationAmount").value(hasItem(DEFAULT_VARIABLE_COMPENSATION_AMOUNT)))
            .andExpect(jsonPath("$.[*].effectiveEndDate").value(hasItem(DEFAULT_EFFECTIVE_END_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getVariableCompensation() throws Exception {
        // Initialize the database
        variableCompensationRepository.saveAndFlush(variableCompensation);

        // Get the variableCompensation
        restVariableCompensationMockMvc.perform(get("/api/variable-compensations/{id}", variableCompensation.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(variableCompensation.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.variableCompensationTypeGroupCode").value(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_CODE))
            .andExpect(jsonPath("$.variableCompensationTypeGroupLabel").value(DEFAULT_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL))
            .andExpect(jsonPath("$.effectiveBeginningDate").value(DEFAULT_EFFECTIVE_BEGINNING_DATE.toString()))
            .andExpect(jsonPath("$.variableCompensationCurrency").value(DEFAULT_VARIABLE_COMPENSATION_CURRENCY))
            .andExpect(jsonPath("$.variableCompensationLocalTypeCode").value(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE))
            .andExpect(jsonPath("$.variableCompensationLocalTypeLabel").value(DEFAULT_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL))
            .andExpect(jsonPath("$.variableCompensationAmount").value(DEFAULT_VARIABLE_COMPENSATION_AMOUNT))
            .andExpect(jsonPath("$.effectiveEndDate").value(DEFAULT_EFFECTIVE_END_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingVariableCompensation() throws Exception {
        // Get the variableCompensation
        restVariableCompensationMockMvc.perform(get("/api/variable-compensations/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVariableCompensation() throws Exception {
        // Initialize the database
        variableCompensationRepository.saveAndFlush(variableCompensation);

        int databaseSizeBeforeUpdate = variableCompensationRepository.findAll().size();

        // Update the variableCompensation
        VariableCompensation updatedVariableCompensation = variableCompensationRepository.findById(variableCompensation.getId()).get();
        // Disconnect from session so that the updates on updatedVariableCompensation are not directly saved in db
        em.detach(updatedVariableCompensation);
        updatedVariableCompensation
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .variableCompensationTypeGroupCode(UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_CODE)
            .variableCompensationTypeGroupLabel(UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL)
            .effectiveBeginningDate(UPDATED_EFFECTIVE_BEGINNING_DATE)
            .variableCompensationCurrency(UPDATED_VARIABLE_COMPENSATION_CURRENCY)
            .variableCompensationLocalTypeCode(UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE)
            .variableCompensationLocalTypeLabel(UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL)
            .variableCompensationAmount(UPDATED_VARIABLE_COMPENSATION_AMOUNT)
            .effectiveEndDate(UPDATED_EFFECTIVE_END_DATE);

        restVariableCompensationMockMvc.perform(put("/api/variable-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedVariableCompensation)))
            .andExpect(status().isOk());

        // Validate the VariableCompensation in the database
        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeUpdate);
        VariableCompensation testVariableCompensation = variableCompensationList.get(variableCompensationList.size() - 1);
        assertThat(testVariableCompensation.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testVariableCompensation.getVariableCompensationTypeGroupCode()).isEqualTo(UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_CODE);
        assertThat(testVariableCompensation.getVariableCompensationTypeGroupLabel()).isEqualTo(UPDATED_VARIABLE_COMPENSATION_TYPE_GROUP_LABEL);
        assertThat(testVariableCompensation.getEffectiveBeginningDate()).isEqualTo(UPDATED_EFFECTIVE_BEGINNING_DATE);
        assertThat(testVariableCompensation.getVariableCompensationCurrency()).isEqualTo(UPDATED_VARIABLE_COMPENSATION_CURRENCY);
        assertThat(testVariableCompensation.getVariableCompensationLocalTypeCode()).isEqualTo(UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_CODE);
        assertThat(testVariableCompensation.getVariableCompensationLocalTypeLabel()).isEqualTo(UPDATED_VARIABLE_COMPENSATION_LOCAL_TYPE_LABEL);
        assertThat(testVariableCompensation.getVariableCompensationAmount()).isEqualTo(UPDATED_VARIABLE_COMPENSATION_AMOUNT);
        assertThat(testVariableCompensation.getEffectiveEndDate()).isEqualTo(UPDATED_EFFECTIVE_END_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingVariableCompensation() throws Exception {
        int databaseSizeBeforeUpdate = variableCompensationRepository.findAll().size();

        // Create the VariableCompensation

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVariableCompensationMockMvc.perform(put("/api/variable-compensations")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(variableCompensation)))
            .andExpect(status().isBadRequest());

        // Validate the VariableCompensation in the database
        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVariableCompensation() throws Exception {
        // Initialize the database
        variableCompensationRepository.saveAndFlush(variableCompensation);

        int databaseSizeBeforeDelete = variableCompensationRepository.findAll().size();

        // Delete the variableCompensation
        restVariableCompensationMockMvc.perform(delete("/api/variable-compensations/{id}", variableCompensation.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<VariableCompensation> variableCompensationList = variableCompensationRepository.findAll();
        assertThat(variableCompensationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
