package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaHomeCompany;
import com.sg.hrco.test.repository.WaHomeCompanyRepository;

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
 * Integration tests for the {@link WaHomeCompanyResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaHomeCompanyResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_ASSIGNMENT_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_ASSIGNMENT_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_TYPE_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_TYPE_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HOME_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_HOME_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_HOME_COMPANY_CODE = "BBBBBBBBBB";

    @Autowired
    private WaHomeCompanyRepository waHomeCompanyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaHomeCompanyMockMvc;

    private WaHomeCompany waHomeCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaHomeCompany createEntity(EntityManager em) {
        WaHomeCompany waHomeCompany = new WaHomeCompany()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .effectiveAssignmentDate(DEFAULT_EFFECTIVE_ASSIGNMENT_DATE)
            .sequence(DEFAULT_SEQUENCE)
            .assignmentType(DEFAULT_ASSIGNMENT_TYPE)
            .assignmentTypeLabel(DEFAULT_ASSIGNMENT_TYPE_LABEL)
            .homeCompanyCode(DEFAULT_HOME_COMPANY_CODE)
            .groupHomeCompanyCode(DEFAULT_GROUP_HOME_COMPANY_CODE);
        return waHomeCompany;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaHomeCompany createUpdatedEntity(EntityManager em) {
        WaHomeCompany waHomeCompany = new WaHomeCompany()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveAssignmentDate(UPDATED_EFFECTIVE_ASSIGNMENT_DATE)
            .sequence(UPDATED_SEQUENCE)
            .assignmentType(UPDATED_ASSIGNMENT_TYPE)
            .assignmentTypeLabel(UPDATED_ASSIGNMENT_TYPE_LABEL)
            .homeCompanyCode(UPDATED_HOME_COMPANY_CODE)
            .groupHomeCompanyCode(UPDATED_GROUP_HOME_COMPANY_CODE);
        return waHomeCompany;
    }

    @BeforeEach
    public void initTest() {
        waHomeCompany = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaHomeCompany() throws Exception {
        int databaseSizeBeforeCreate = waHomeCompanyRepository.findAll().size();

        // Create the WaHomeCompany
        restWaHomeCompanyMockMvc.perform(post("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isCreated());

        // Validate the WaHomeCompany in the database
        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        WaHomeCompany testWaHomeCompany = waHomeCompanyList.get(waHomeCompanyList.size() - 1);
        assertThat(testWaHomeCompany.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaHomeCompany.getEffectiveAssignmentDate()).isEqualTo(DEFAULT_EFFECTIVE_ASSIGNMENT_DATE);
        assertThat(testWaHomeCompany.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testWaHomeCompany.getAssignmentType()).isEqualTo(DEFAULT_ASSIGNMENT_TYPE);
        assertThat(testWaHomeCompany.getAssignmentTypeLabel()).isEqualTo(DEFAULT_ASSIGNMENT_TYPE_LABEL);
        assertThat(testWaHomeCompany.getHomeCompanyCode()).isEqualTo(DEFAULT_HOME_COMPANY_CODE);
        assertThat(testWaHomeCompany.getGroupHomeCompanyCode()).isEqualTo(DEFAULT_GROUP_HOME_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void createWaHomeCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waHomeCompanyRepository.findAll().size();

        // Create the WaHomeCompany with an existing ID
        waHomeCompany.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaHomeCompanyMockMvc.perform(post("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isBadRequest());

        // Validate the WaHomeCompany in the database
        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectiveAssignmentDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHomeCompanyRepository.findAll().size();
        // set the field null
        waHomeCompany.setEffectiveAssignmentDate(null);

        // Create the WaHomeCompany, which fails.

        restWaHomeCompanyMockMvc.perform(post("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isBadRequest());

        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSequenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHomeCompanyRepository.findAll().size();
        // set the field null
        waHomeCompany.setSequence(null);

        // Create the WaHomeCompany, which fails.

        restWaHomeCompanyMockMvc.perform(post("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isBadRequest());

        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAssignmentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHomeCompanyRepository.findAll().size();
        // set the field null
        waHomeCompany.setAssignmentType(null);

        // Create the WaHomeCompany, which fails.

        restWaHomeCompanyMockMvc.perform(post("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isBadRequest());

        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHomeCompanyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHomeCompanyRepository.findAll().size();
        // set the field null
        waHomeCompany.setHomeCompanyCode(null);

        // Create the WaHomeCompany, which fails.

        restWaHomeCompanyMockMvc.perform(post("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isBadRequest());

        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaHomeCompanies() throws Exception {
        // Initialize the database
        waHomeCompanyRepository.saveAndFlush(waHomeCompany);

        // Get all the waHomeCompanyList
        restWaHomeCompanyMockMvc.perform(get("/api/wa-home-companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waHomeCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].effectiveAssignmentDate").value(hasItem(DEFAULT_EFFECTIVE_ASSIGNMENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
            .andExpect(jsonPath("$.[*].assignmentType").value(hasItem(DEFAULT_ASSIGNMENT_TYPE)))
            .andExpect(jsonPath("$.[*].assignmentTypeLabel").value(hasItem(DEFAULT_ASSIGNMENT_TYPE_LABEL)))
            .andExpect(jsonPath("$.[*].homeCompanyCode").value(hasItem(DEFAULT_HOME_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].groupHomeCompanyCode").value(hasItem(DEFAULT_GROUP_HOME_COMPANY_CODE)));
    }
    
    @Test
    @Transactional
    public void getWaHomeCompany() throws Exception {
        // Initialize the database
        waHomeCompanyRepository.saveAndFlush(waHomeCompany);

        // Get the waHomeCompany
        restWaHomeCompanyMockMvc.perform(get("/api/wa-home-companies/{id}", waHomeCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waHomeCompany.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.effectiveAssignmentDate").value(DEFAULT_EFFECTIVE_ASSIGNMENT_DATE.toString()))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.assignmentType").value(DEFAULT_ASSIGNMENT_TYPE))
            .andExpect(jsonPath("$.assignmentTypeLabel").value(DEFAULT_ASSIGNMENT_TYPE_LABEL))
            .andExpect(jsonPath("$.homeCompanyCode").value(DEFAULT_HOME_COMPANY_CODE))
            .andExpect(jsonPath("$.groupHomeCompanyCode").value(DEFAULT_GROUP_HOME_COMPANY_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingWaHomeCompany() throws Exception {
        // Get the waHomeCompany
        restWaHomeCompanyMockMvc.perform(get("/api/wa-home-companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaHomeCompany() throws Exception {
        // Initialize the database
        waHomeCompanyRepository.saveAndFlush(waHomeCompany);

        int databaseSizeBeforeUpdate = waHomeCompanyRepository.findAll().size();

        // Update the waHomeCompany
        WaHomeCompany updatedWaHomeCompany = waHomeCompanyRepository.findById(waHomeCompany.getId()).get();
        // Disconnect from session so that the updates on updatedWaHomeCompany are not directly saved in db
        em.detach(updatedWaHomeCompany);
        updatedWaHomeCompany
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveAssignmentDate(UPDATED_EFFECTIVE_ASSIGNMENT_DATE)
            .sequence(UPDATED_SEQUENCE)
            .assignmentType(UPDATED_ASSIGNMENT_TYPE)
            .assignmentTypeLabel(UPDATED_ASSIGNMENT_TYPE_LABEL)
            .homeCompanyCode(UPDATED_HOME_COMPANY_CODE)
            .groupHomeCompanyCode(UPDATED_GROUP_HOME_COMPANY_CODE);

        restWaHomeCompanyMockMvc.perform(put("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaHomeCompany)))
            .andExpect(status().isOk());

        // Validate the WaHomeCompany in the database
        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeUpdate);
        WaHomeCompany testWaHomeCompany = waHomeCompanyList.get(waHomeCompanyList.size() - 1);
        assertThat(testWaHomeCompany.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaHomeCompany.getEffectiveAssignmentDate()).isEqualTo(UPDATED_EFFECTIVE_ASSIGNMENT_DATE);
        assertThat(testWaHomeCompany.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testWaHomeCompany.getAssignmentType()).isEqualTo(UPDATED_ASSIGNMENT_TYPE);
        assertThat(testWaHomeCompany.getAssignmentTypeLabel()).isEqualTo(UPDATED_ASSIGNMENT_TYPE_LABEL);
        assertThat(testWaHomeCompany.getHomeCompanyCode()).isEqualTo(UPDATED_HOME_COMPANY_CODE);
        assertThat(testWaHomeCompany.getGroupHomeCompanyCode()).isEqualTo(UPDATED_GROUP_HOME_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaHomeCompany() throws Exception {
        int databaseSizeBeforeUpdate = waHomeCompanyRepository.findAll().size();

        // Create the WaHomeCompany

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaHomeCompanyMockMvc.perform(put("/api/wa-home-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHomeCompany)))
            .andExpect(status().isBadRequest());

        // Validate the WaHomeCompany in the database
        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaHomeCompany() throws Exception {
        // Initialize the database
        waHomeCompanyRepository.saveAndFlush(waHomeCompany);

        int databaseSizeBeforeDelete = waHomeCompanyRepository.findAll().size();

        // Delete the waHomeCompany
        restWaHomeCompanyMockMvc.perform(delete("/api/wa-home-companies/{id}", waHomeCompany.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaHomeCompany> waHomeCompanyList = waHomeCompanyRepository.findAll();
        assertThat(waHomeCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
