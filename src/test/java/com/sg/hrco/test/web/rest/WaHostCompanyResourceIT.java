package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaHostCompany;
import com.sg.hrco.test.repository.WaHostCompanyRepository;

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
 * Integration tests for the {@link WaHostCompanyResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaHostCompanyResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ASSIGNMENT_TYPE_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_ASSIGNMENT_TYPE_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_HOST_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_HOST_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_HOST_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_HOST_COMPANY_CODE = "BBBBBBBBBB";

    @Autowired
    private WaHostCompanyRepository waHostCompanyRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaHostCompanyMockMvc;

    private WaHostCompany waHostCompany;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaHostCompany createEntity(EntityManager em) {
        WaHostCompany waHostCompany = new WaHostCompany()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .sequence(DEFAULT_SEQUENCE)
            .assignmentType(DEFAULT_ASSIGNMENT_TYPE)
            .assignmentTypeLabel(DEFAULT_ASSIGNMENT_TYPE_LABEL)
            .hostCompanyCode(DEFAULT_HOST_COMPANY_CODE)
            .groupHostCompanyCode(DEFAULT_GROUP_HOST_COMPANY_CODE);
        return waHostCompany;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaHostCompany createUpdatedEntity(EntityManager em) {
        WaHostCompany waHostCompany = new WaHostCompany()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .assignmentType(UPDATED_ASSIGNMENT_TYPE)
            .assignmentTypeLabel(UPDATED_ASSIGNMENT_TYPE_LABEL)
            .hostCompanyCode(UPDATED_HOST_COMPANY_CODE)
            .groupHostCompanyCode(UPDATED_GROUP_HOST_COMPANY_CODE);
        return waHostCompany;
    }

    @BeforeEach
    public void initTest() {
        waHostCompany = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaHostCompany() throws Exception {
        int databaseSizeBeforeCreate = waHostCompanyRepository.findAll().size();

        // Create the WaHostCompany
        restWaHostCompanyMockMvc.perform(post("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isCreated());

        // Validate the WaHostCompany in the database
        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeCreate + 1);
        WaHostCompany testWaHostCompany = waHostCompanyList.get(waHostCompanyList.size() - 1);
        assertThat(testWaHostCompany.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaHostCompany.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testWaHostCompany.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testWaHostCompany.getAssignmentType()).isEqualTo(DEFAULT_ASSIGNMENT_TYPE);
        assertThat(testWaHostCompany.getAssignmentTypeLabel()).isEqualTo(DEFAULT_ASSIGNMENT_TYPE_LABEL);
        assertThat(testWaHostCompany.getHostCompanyCode()).isEqualTo(DEFAULT_HOST_COMPANY_CODE);
        assertThat(testWaHostCompany.getGroupHostCompanyCode()).isEqualTo(DEFAULT_GROUP_HOST_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void createWaHostCompanyWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waHostCompanyRepository.findAll().size();

        // Create the WaHostCompany with an existing ID
        waHostCompany.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaHostCompanyMockMvc.perform(post("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isBadRequest());

        // Validate the WaHostCompany in the database
        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHostCompanyRepository.findAll().size();
        // set the field null
        waHostCompany.setEffectiveDate(null);

        // Create the WaHostCompany, which fails.

        restWaHostCompanyMockMvc.perform(post("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isBadRequest());

        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSequenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHostCompanyRepository.findAll().size();
        // set the field null
        waHostCompany.setSequence(null);

        // Create the WaHostCompany, which fails.

        restWaHostCompanyMockMvc.perform(post("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isBadRequest());

        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkAssignmentTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHostCompanyRepository.findAll().size();
        // set the field null
        waHostCompany.setAssignmentType(null);

        // Create the WaHostCompany, which fails.

        restWaHostCompanyMockMvc.perform(post("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isBadRequest());

        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkHostCompanyCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waHostCompanyRepository.findAll().size();
        // set the field null
        waHostCompany.setHostCompanyCode(null);

        // Create the WaHostCompany, which fails.

        restWaHostCompanyMockMvc.perform(post("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isBadRequest());

        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaHostCompanies() throws Exception {
        // Initialize the database
        waHostCompanyRepository.saveAndFlush(waHostCompany);

        // Get all the waHostCompanyList
        restWaHostCompanyMockMvc.perform(get("/api/wa-host-companies?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waHostCompany.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
            .andExpect(jsonPath("$.[*].assignmentType").value(hasItem(DEFAULT_ASSIGNMENT_TYPE)))
            .andExpect(jsonPath("$.[*].assignmentTypeLabel").value(hasItem(DEFAULT_ASSIGNMENT_TYPE_LABEL)))
            .andExpect(jsonPath("$.[*].hostCompanyCode").value(hasItem(DEFAULT_HOST_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].groupHostCompanyCode").value(hasItem(DEFAULT_GROUP_HOST_COMPANY_CODE)));
    }
    
    @Test
    @Transactional
    public void getWaHostCompany() throws Exception {
        // Initialize the database
        waHostCompanyRepository.saveAndFlush(waHostCompany);

        // Get the waHostCompany
        restWaHostCompanyMockMvc.perform(get("/api/wa-host-companies/{id}", waHostCompany.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waHostCompany.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.assignmentType").value(DEFAULT_ASSIGNMENT_TYPE))
            .andExpect(jsonPath("$.assignmentTypeLabel").value(DEFAULT_ASSIGNMENT_TYPE_LABEL))
            .andExpect(jsonPath("$.hostCompanyCode").value(DEFAULT_HOST_COMPANY_CODE))
            .andExpect(jsonPath("$.groupHostCompanyCode").value(DEFAULT_GROUP_HOST_COMPANY_CODE));
    }

    @Test
    @Transactional
    public void getNonExistingWaHostCompany() throws Exception {
        // Get the waHostCompany
        restWaHostCompanyMockMvc.perform(get("/api/wa-host-companies/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaHostCompany() throws Exception {
        // Initialize the database
        waHostCompanyRepository.saveAndFlush(waHostCompany);

        int databaseSizeBeforeUpdate = waHostCompanyRepository.findAll().size();

        // Update the waHostCompany
        WaHostCompany updatedWaHostCompany = waHostCompanyRepository.findById(waHostCompany.getId()).get();
        // Disconnect from session so that the updates on updatedWaHostCompany are not directly saved in db
        em.detach(updatedWaHostCompany);
        updatedWaHostCompany
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .assignmentType(UPDATED_ASSIGNMENT_TYPE)
            .assignmentTypeLabel(UPDATED_ASSIGNMENT_TYPE_LABEL)
            .hostCompanyCode(UPDATED_HOST_COMPANY_CODE)
            .groupHostCompanyCode(UPDATED_GROUP_HOST_COMPANY_CODE);

        restWaHostCompanyMockMvc.perform(put("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaHostCompany)))
            .andExpect(status().isOk());

        // Validate the WaHostCompany in the database
        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeUpdate);
        WaHostCompany testWaHostCompany = waHostCompanyList.get(waHostCompanyList.size() - 1);
        assertThat(testWaHostCompany.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaHostCompany.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testWaHostCompany.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testWaHostCompany.getAssignmentType()).isEqualTo(UPDATED_ASSIGNMENT_TYPE);
        assertThat(testWaHostCompany.getAssignmentTypeLabel()).isEqualTo(UPDATED_ASSIGNMENT_TYPE_LABEL);
        assertThat(testWaHostCompany.getHostCompanyCode()).isEqualTo(UPDATED_HOST_COMPANY_CODE);
        assertThat(testWaHostCompany.getGroupHostCompanyCode()).isEqualTo(UPDATED_GROUP_HOST_COMPANY_CODE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaHostCompany() throws Exception {
        int databaseSizeBeforeUpdate = waHostCompanyRepository.findAll().size();

        // Create the WaHostCompany

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaHostCompanyMockMvc.perform(put("/api/wa-host-companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waHostCompany)))
            .andExpect(status().isBadRequest());

        // Validate the WaHostCompany in the database
        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaHostCompany() throws Exception {
        // Initialize the database
        waHostCompanyRepository.saveAndFlush(waHostCompany);

        int databaseSizeBeforeDelete = waHostCompanyRepository.findAll().size();

        // Delete the waHostCompany
        restWaHostCompanyMockMvc.perform(delete("/api/wa-host-companies/{id}", waHostCompany.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaHostCompany> waHostCompanyList = waHostCompanyRepository.findAll();
        assertThat(waHostCompanyList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
