package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaAssignment;
import com.sg.hrco.test.repository.WaAssignmentRepository;

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
 * Integration tests for the {@link WaAssignmentResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaAssignmentResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_GROUP_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_GROUP_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ESTABLISHMENT = "AAAAAAAAAA";
    private static final String UPDATED_ESTABLISHMENT = "BBBBBBBBBB";

    private static final String DEFAULT_POLE_ACTIVITY_GROUP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_POLE_ACTIVITY_GROUP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_SUB_POLE_ACTIVITY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_SUB_POLE_ACTIVITY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_SUB_POLE_ACTIVITY_TITLE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_SUB_POLE_ACTIVITY_TITLE = "BBBBBBBBBB";

    private static final String DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_BUDGET_FUNCTIONAL_ID_SAKKARAH = "AAAAAAAAAA";
    private static final String UPDATED_BUDGET_FUNCTIONAL_ID_SAKKARAH = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_ADMIN_FUNCTIONAL_ID_SAKKARAH = "AAAAAAAAAA";
    private static final String UPDATED_ADMIN_FUNCTIONAL_ID_SAKKARAH = "BBBBBBBBBB";

    private static final String DEFAULT_DETACHMENT_GROUP_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_DETACHMENT_GROUP_COMPANY = "BBBBBBBBBB";

    private static final String DEFAULT_DETACHMENT_LEGAL_GROUP_COMPANY = "AAAAAAAAAA";
    private static final String UPDATED_DETACHMENT_LEGAL_GROUP_COMPANY = "BBBBBBBBBB";

    @Autowired
    private WaAssignmentRepository waAssignmentRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaAssignmentMockMvc;

    private WaAssignment waAssignment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaAssignment createEntity(EntityManager em) {
        WaAssignment waAssignment = new WaAssignment()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .sequence(DEFAULT_SEQUENCE)
            .localCompanyCode(DEFAULT_LOCAL_COMPANY_CODE)
            .legalGroupCompanyCode(DEFAULT_LEGAL_GROUP_COMPANY_CODE)
            .establishment(DEFAULT_ESTABLISHMENT)
            .poleActivityGroupCode(DEFAULT_POLE_ACTIVITY_GROUP_CODE)
            .groupSubPoleActivityCode(DEFAULT_GROUP_SUB_POLE_ACTIVITY_CODE)
            .groupSubPoleActivityTitle(DEFAULT_GROUP_SUB_POLE_ACTIVITY_TITLE)
            .budgetaryAffectationDepartmentCode(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_CODE)
            .budgetaryAffectationDepartmentLabel(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL)
            .budgetFunctionalIdSakkarah(DEFAULT_BUDGET_FUNCTIONAL_ID_SAKKARAH)
            .administrativeDepartmentLocalCode(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE)
            .administrativeDepartmentLocalLabel(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL)
            .adminFunctionalIdSakkarah(DEFAULT_ADMIN_FUNCTIONAL_ID_SAKKARAH)
            .detachmentGroupCompany(DEFAULT_DETACHMENT_GROUP_COMPANY)
            .detachmentLegalGroupCompany(DEFAULT_DETACHMENT_LEGAL_GROUP_COMPANY);
        return waAssignment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaAssignment createUpdatedEntity(EntityManager em) {
        WaAssignment waAssignment = new WaAssignment()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .localCompanyCode(UPDATED_LOCAL_COMPANY_CODE)
            .legalGroupCompanyCode(UPDATED_LEGAL_GROUP_COMPANY_CODE)
            .establishment(UPDATED_ESTABLISHMENT)
            .poleActivityGroupCode(UPDATED_POLE_ACTIVITY_GROUP_CODE)
            .groupSubPoleActivityCode(UPDATED_GROUP_SUB_POLE_ACTIVITY_CODE)
            .groupSubPoleActivityTitle(UPDATED_GROUP_SUB_POLE_ACTIVITY_TITLE)
            .budgetaryAffectationDepartmentCode(UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_CODE)
            .budgetaryAffectationDepartmentLabel(UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL)
            .budgetFunctionalIdSakkarah(UPDATED_BUDGET_FUNCTIONAL_ID_SAKKARAH)
            .administrativeDepartmentLocalCode(UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE)
            .administrativeDepartmentLocalLabel(UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL)
            .adminFunctionalIdSakkarah(UPDATED_ADMIN_FUNCTIONAL_ID_SAKKARAH)
            .detachmentGroupCompany(UPDATED_DETACHMENT_GROUP_COMPANY)
            .detachmentLegalGroupCompany(UPDATED_DETACHMENT_LEGAL_GROUP_COMPANY);
        return waAssignment;
    }

    @BeforeEach
    public void initTest() {
        waAssignment = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaAssignment() throws Exception {
        int databaseSizeBeforeCreate = waAssignmentRepository.findAll().size();

        // Create the WaAssignment
        restWaAssignmentMockMvc.perform(post("/api/wa-assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waAssignment)))
            .andExpect(status().isCreated());

        // Validate the WaAssignment in the database
        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeCreate + 1);
        WaAssignment testWaAssignment = waAssignmentList.get(waAssignmentList.size() - 1);
        assertThat(testWaAssignment.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaAssignment.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testWaAssignment.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testWaAssignment.getLocalCompanyCode()).isEqualTo(DEFAULT_LOCAL_COMPANY_CODE);
        assertThat(testWaAssignment.getLegalGroupCompanyCode()).isEqualTo(DEFAULT_LEGAL_GROUP_COMPANY_CODE);
        assertThat(testWaAssignment.getEstablishment()).isEqualTo(DEFAULT_ESTABLISHMENT);
        assertThat(testWaAssignment.getPoleActivityGroupCode()).isEqualTo(DEFAULT_POLE_ACTIVITY_GROUP_CODE);
        assertThat(testWaAssignment.getGroupSubPoleActivityCode()).isEqualTo(DEFAULT_GROUP_SUB_POLE_ACTIVITY_CODE);
        assertThat(testWaAssignment.getGroupSubPoleActivityTitle()).isEqualTo(DEFAULT_GROUP_SUB_POLE_ACTIVITY_TITLE);
        assertThat(testWaAssignment.getBudgetaryAffectationDepartmentCode()).isEqualTo(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_CODE);
        assertThat(testWaAssignment.getBudgetaryAffectationDepartmentLabel()).isEqualTo(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL);
        assertThat(testWaAssignment.getBudgetFunctionalIdSakkarah()).isEqualTo(DEFAULT_BUDGET_FUNCTIONAL_ID_SAKKARAH);
        assertThat(testWaAssignment.getAdministrativeDepartmentLocalCode()).isEqualTo(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE);
        assertThat(testWaAssignment.getAdministrativeDepartmentLocalLabel()).isEqualTo(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL);
        assertThat(testWaAssignment.getAdminFunctionalIdSakkarah()).isEqualTo(DEFAULT_ADMIN_FUNCTIONAL_ID_SAKKARAH);
        assertThat(testWaAssignment.getDetachmentGroupCompany()).isEqualTo(DEFAULT_DETACHMENT_GROUP_COMPANY);
        assertThat(testWaAssignment.getDetachmentLegalGroupCompany()).isEqualTo(DEFAULT_DETACHMENT_LEGAL_GROUP_COMPANY);
    }

    @Test
    @Transactional
    public void createWaAssignmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waAssignmentRepository.findAll().size();

        // Create the WaAssignment with an existing ID
        waAssignment.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaAssignmentMockMvc.perform(post("/api/wa-assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waAssignment)))
            .andExpect(status().isBadRequest());

        // Validate the WaAssignment in the database
        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waAssignmentRepository.findAll().size();
        // set the field null
        waAssignment.setEffectiveDate(null);

        // Create the WaAssignment, which fails.

        restWaAssignmentMockMvc.perform(post("/api/wa-assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waAssignment)))
            .andExpect(status().isBadRequest());

        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSequenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = waAssignmentRepository.findAll().size();
        // set the field null
        waAssignment.setSequence(null);

        // Create the WaAssignment, which fails.

        restWaAssignmentMockMvc.perform(post("/api/wa-assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waAssignment)))
            .andExpect(status().isBadRequest());

        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaAssignments() throws Exception {
        // Initialize the database
        waAssignmentRepository.saveAndFlush(waAssignment);

        // Get all the waAssignmentList
        restWaAssignmentMockMvc.perform(get("/api/wa-assignments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waAssignment.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
            .andExpect(jsonPath("$.[*].localCompanyCode").value(hasItem(DEFAULT_LOCAL_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].legalGroupCompanyCode").value(hasItem(DEFAULT_LEGAL_GROUP_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].establishment").value(hasItem(DEFAULT_ESTABLISHMENT)))
            .andExpect(jsonPath("$.[*].poleActivityGroupCode").value(hasItem(DEFAULT_POLE_ACTIVITY_GROUP_CODE)))
            .andExpect(jsonPath("$.[*].groupSubPoleActivityCode").value(hasItem(DEFAULT_GROUP_SUB_POLE_ACTIVITY_CODE)))
            .andExpect(jsonPath("$.[*].groupSubPoleActivityTitle").value(hasItem(DEFAULT_GROUP_SUB_POLE_ACTIVITY_TITLE)))
            .andExpect(jsonPath("$.[*].budgetaryAffectationDepartmentCode").value(hasItem(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_CODE)))
            .andExpect(jsonPath("$.[*].budgetaryAffectationDepartmentLabel").value(hasItem(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL)))
            .andExpect(jsonPath("$.[*].budgetFunctionalIdSakkarah").value(hasItem(DEFAULT_BUDGET_FUNCTIONAL_ID_SAKKARAH)))
            .andExpect(jsonPath("$.[*].administrativeDepartmentLocalCode").value(hasItem(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE)))
            .andExpect(jsonPath("$.[*].administrativeDepartmentLocalLabel").value(hasItem(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL)))
            .andExpect(jsonPath("$.[*].adminFunctionalIdSakkarah").value(hasItem(DEFAULT_ADMIN_FUNCTIONAL_ID_SAKKARAH)))
            .andExpect(jsonPath("$.[*].detachmentGroupCompany").value(hasItem(DEFAULT_DETACHMENT_GROUP_COMPANY)))
            .andExpect(jsonPath("$.[*].detachmentLegalGroupCompany").value(hasItem(DEFAULT_DETACHMENT_LEGAL_GROUP_COMPANY)));
    }
    
    @Test
    @Transactional
    public void getWaAssignment() throws Exception {
        // Initialize the database
        waAssignmentRepository.saveAndFlush(waAssignment);

        // Get the waAssignment
        restWaAssignmentMockMvc.perform(get("/api/wa-assignments/{id}", waAssignment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waAssignment.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.localCompanyCode").value(DEFAULT_LOCAL_COMPANY_CODE))
            .andExpect(jsonPath("$.legalGroupCompanyCode").value(DEFAULT_LEGAL_GROUP_COMPANY_CODE))
            .andExpect(jsonPath("$.establishment").value(DEFAULT_ESTABLISHMENT))
            .andExpect(jsonPath("$.poleActivityGroupCode").value(DEFAULT_POLE_ACTIVITY_GROUP_CODE))
            .andExpect(jsonPath("$.groupSubPoleActivityCode").value(DEFAULT_GROUP_SUB_POLE_ACTIVITY_CODE))
            .andExpect(jsonPath("$.groupSubPoleActivityTitle").value(DEFAULT_GROUP_SUB_POLE_ACTIVITY_TITLE))
            .andExpect(jsonPath("$.budgetaryAffectationDepartmentCode").value(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_CODE))
            .andExpect(jsonPath("$.budgetaryAffectationDepartmentLabel").value(DEFAULT_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL))
            .andExpect(jsonPath("$.budgetFunctionalIdSakkarah").value(DEFAULT_BUDGET_FUNCTIONAL_ID_SAKKARAH))
            .andExpect(jsonPath("$.administrativeDepartmentLocalCode").value(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE))
            .andExpect(jsonPath("$.administrativeDepartmentLocalLabel").value(DEFAULT_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL))
            .andExpect(jsonPath("$.adminFunctionalIdSakkarah").value(DEFAULT_ADMIN_FUNCTIONAL_ID_SAKKARAH))
            .andExpect(jsonPath("$.detachmentGroupCompany").value(DEFAULT_DETACHMENT_GROUP_COMPANY))
            .andExpect(jsonPath("$.detachmentLegalGroupCompany").value(DEFAULT_DETACHMENT_LEGAL_GROUP_COMPANY));
    }

    @Test
    @Transactional
    public void getNonExistingWaAssignment() throws Exception {
        // Get the waAssignment
        restWaAssignmentMockMvc.perform(get("/api/wa-assignments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaAssignment() throws Exception {
        // Initialize the database
        waAssignmentRepository.saveAndFlush(waAssignment);

        int databaseSizeBeforeUpdate = waAssignmentRepository.findAll().size();

        // Update the waAssignment
        WaAssignment updatedWaAssignment = waAssignmentRepository.findById(waAssignment.getId()).get();
        // Disconnect from session so that the updates on updatedWaAssignment are not directly saved in db
        em.detach(updatedWaAssignment);
        updatedWaAssignment
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .localCompanyCode(UPDATED_LOCAL_COMPANY_CODE)
            .legalGroupCompanyCode(UPDATED_LEGAL_GROUP_COMPANY_CODE)
            .establishment(UPDATED_ESTABLISHMENT)
            .poleActivityGroupCode(UPDATED_POLE_ACTIVITY_GROUP_CODE)
            .groupSubPoleActivityCode(UPDATED_GROUP_SUB_POLE_ACTIVITY_CODE)
            .groupSubPoleActivityTitle(UPDATED_GROUP_SUB_POLE_ACTIVITY_TITLE)
            .budgetaryAffectationDepartmentCode(UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_CODE)
            .budgetaryAffectationDepartmentLabel(UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL)
            .budgetFunctionalIdSakkarah(UPDATED_BUDGET_FUNCTIONAL_ID_SAKKARAH)
            .administrativeDepartmentLocalCode(UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE)
            .administrativeDepartmentLocalLabel(UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL)
            .adminFunctionalIdSakkarah(UPDATED_ADMIN_FUNCTIONAL_ID_SAKKARAH)
            .detachmentGroupCompany(UPDATED_DETACHMENT_GROUP_COMPANY)
            .detachmentLegalGroupCompany(UPDATED_DETACHMENT_LEGAL_GROUP_COMPANY);

        restWaAssignmentMockMvc.perform(put("/api/wa-assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaAssignment)))
            .andExpect(status().isOk());

        // Validate the WaAssignment in the database
        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeUpdate);
        WaAssignment testWaAssignment = waAssignmentList.get(waAssignmentList.size() - 1);
        assertThat(testWaAssignment.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaAssignment.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testWaAssignment.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testWaAssignment.getLocalCompanyCode()).isEqualTo(UPDATED_LOCAL_COMPANY_CODE);
        assertThat(testWaAssignment.getLegalGroupCompanyCode()).isEqualTo(UPDATED_LEGAL_GROUP_COMPANY_CODE);
        assertThat(testWaAssignment.getEstablishment()).isEqualTo(UPDATED_ESTABLISHMENT);
        assertThat(testWaAssignment.getPoleActivityGroupCode()).isEqualTo(UPDATED_POLE_ACTIVITY_GROUP_CODE);
        assertThat(testWaAssignment.getGroupSubPoleActivityCode()).isEqualTo(UPDATED_GROUP_SUB_POLE_ACTIVITY_CODE);
        assertThat(testWaAssignment.getGroupSubPoleActivityTitle()).isEqualTo(UPDATED_GROUP_SUB_POLE_ACTIVITY_TITLE);
        assertThat(testWaAssignment.getBudgetaryAffectationDepartmentCode()).isEqualTo(UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_CODE);
        assertThat(testWaAssignment.getBudgetaryAffectationDepartmentLabel()).isEqualTo(UPDATED_BUDGETARY_AFFECTATION_DEPARTMENT_LABEL);
        assertThat(testWaAssignment.getBudgetFunctionalIdSakkarah()).isEqualTo(UPDATED_BUDGET_FUNCTIONAL_ID_SAKKARAH);
        assertThat(testWaAssignment.getAdministrativeDepartmentLocalCode()).isEqualTo(UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_CODE);
        assertThat(testWaAssignment.getAdministrativeDepartmentLocalLabel()).isEqualTo(UPDATED_ADMINISTRATIVE_DEPARTMENT_LOCAL_LABEL);
        assertThat(testWaAssignment.getAdminFunctionalIdSakkarah()).isEqualTo(UPDATED_ADMIN_FUNCTIONAL_ID_SAKKARAH);
        assertThat(testWaAssignment.getDetachmentGroupCompany()).isEqualTo(UPDATED_DETACHMENT_GROUP_COMPANY);
        assertThat(testWaAssignment.getDetachmentLegalGroupCompany()).isEqualTo(UPDATED_DETACHMENT_LEGAL_GROUP_COMPANY);
    }

    @Test
    @Transactional
    public void updateNonExistingWaAssignment() throws Exception {
        int databaseSizeBeforeUpdate = waAssignmentRepository.findAll().size();

        // Create the WaAssignment

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaAssignmentMockMvc.perform(put("/api/wa-assignments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waAssignment)))
            .andExpect(status().isBadRequest());

        // Validate the WaAssignment in the database
        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaAssignment() throws Exception {
        // Initialize the database
        waAssignmentRepository.saveAndFlush(waAssignment);

        int databaseSizeBeforeDelete = waAssignmentRepository.findAll().size();

        // Delete the waAssignment
        restWaAssignmentMockMvc.perform(delete("/api/wa-assignments/{id}", waAssignment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaAssignment> waAssignmentList = waAssignmentRepository.findAll();
        assertThat(waAssignmentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
