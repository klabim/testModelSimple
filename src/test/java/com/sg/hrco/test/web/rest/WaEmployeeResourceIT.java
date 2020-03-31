package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaEmployee;
import com.sg.hrco.test.repository.WaEmployeeRepository;

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
 * Integration tests for the {@link WaEmployeeResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaEmployeeResourceIT {

    private static final String DEFAULT_GGI = "AAAAAAAAAA";
    private static final String UPDATED_GGI = "BBBBBBBBBB";

    private static final String DEFAULT_HOME_HOST_INDICATOR = "AAAAAAAAAA";
    private static final String UPDATED_HOME_HOST_INDICATOR = "BBBBBBBBBB";

    private static final String DEFAULT_LEGAL_GROUP_COMPANY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LEGAL_GROUP_COMPANY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PREFIX = "AAAAAAAAAA";
    private static final String UPDATED_PREFIX = "BBBBBBBBBB";

    private static final String DEFAULT_LATIN_BIRTH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_BIRTH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_BIRTH_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_BIRTH_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LATIN_COMMON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_COMMON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_COMMON_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_COMMON_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LATIN_NAME_COMPLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_NAME_COMPLEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_NAME_COMPLEMENT = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_NAME_COMPLEMENT = "BBBBBBBBBB";

    private static final String DEFAULT_LATIN_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LATIN_FIRST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_FIRST_NAME = "BBBBBBBBBB";

    private static final Instant DEFAULT_EMPLOYEE_BIRTH_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EMPLOYEE_BIRTH_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_COUNTRY_OF_BIRTH = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_BIRTH = "BBBBBBBBBB";

    private static final String DEFAULT_BIRTH_DEPARTMENT = "AAAAAAAAAA";
    private static final String UPDATED_BIRTH_DEPARTMENT = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_SENSITIVE_EMPLOYEE_FLAG = "AAAAAAAAAA";
    private static final String UPDATED_SENSITIVE_EMPLOYEE_FLAG = "BBBBBBBBBB";

    @Autowired
    private WaEmployeeRepository waEmployeeRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaEmployeeMockMvc;

    private WaEmployee waEmployee;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaEmployee createEntity(EntityManager em) {
        WaEmployee waEmployee = new WaEmployee()
            .ggi(DEFAULT_GGI)
            .homeHostIndicator(DEFAULT_HOME_HOST_INDICATOR)
            .legalGroupCompanyCode(DEFAULT_LEGAL_GROUP_COMPANY_CODE)
            .prefix(DEFAULT_PREFIX)
            .latinBirthName(DEFAULT_LATIN_BIRTH_NAME)
            .localBirthName(DEFAULT_LOCAL_BIRTH_NAME)
            .latinCommonName(DEFAULT_LATIN_COMMON_NAME)
            .localCommonName(DEFAULT_LOCAL_COMMON_NAME)
            .latinNameComplement(DEFAULT_LATIN_NAME_COMPLEMENT)
            .localNameComplement(DEFAULT_LOCAL_NAME_COMPLEMENT)
            .latinFirstName(DEFAULT_LATIN_FIRST_NAME)
            .localFirstName(DEFAULT_LOCAL_FIRST_NAME)
            .employeeBirthDate(DEFAULT_EMPLOYEE_BIRTH_DATE)
            .countryOfBirth(DEFAULT_COUNTRY_OF_BIRTH)
            .birthDepartment(DEFAULT_BIRTH_DEPARTMENT)
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .sensitiveEmployeeFlag(DEFAULT_SENSITIVE_EMPLOYEE_FLAG);
        return waEmployee;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaEmployee createUpdatedEntity(EntityManager em) {
        WaEmployee waEmployee = new WaEmployee()
            .ggi(UPDATED_GGI)
            .homeHostIndicator(UPDATED_HOME_HOST_INDICATOR)
            .legalGroupCompanyCode(UPDATED_LEGAL_GROUP_COMPANY_CODE)
            .prefix(UPDATED_PREFIX)
            .latinBirthName(UPDATED_LATIN_BIRTH_NAME)
            .localBirthName(UPDATED_LOCAL_BIRTH_NAME)
            .latinCommonName(UPDATED_LATIN_COMMON_NAME)
            .localCommonName(UPDATED_LOCAL_COMMON_NAME)
            .latinNameComplement(UPDATED_LATIN_NAME_COMPLEMENT)
            .localNameComplement(UPDATED_LOCAL_NAME_COMPLEMENT)
            .latinFirstName(UPDATED_LATIN_FIRST_NAME)
            .localFirstName(UPDATED_LOCAL_FIRST_NAME)
            .employeeBirthDate(UPDATED_EMPLOYEE_BIRTH_DATE)
            .countryOfBirth(UPDATED_COUNTRY_OF_BIRTH)
            .birthDepartment(UPDATED_BIRTH_DEPARTMENT)
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .sensitiveEmployeeFlag(UPDATED_SENSITIVE_EMPLOYEE_FLAG);
        return waEmployee;
    }

    @BeforeEach
    public void initTest() {
        waEmployee = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaEmployee() throws Exception {
        int databaseSizeBeforeCreate = waEmployeeRepository.findAll().size();

        // Create the WaEmployee
        restWaEmployeeMockMvc.perform(post("/api/wa-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waEmployee)))
            .andExpect(status().isCreated());

        // Validate the WaEmployee in the database
        List<WaEmployee> waEmployeeList = waEmployeeRepository.findAll();
        assertThat(waEmployeeList).hasSize(databaseSizeBeforeCreate + 1);
        WaEmployee testWaEmployee = waEmployeeList.get(waEmployeeList.size() - 1);
        assertThat(testWaEmployee.getGgi()).isEqualTo(DEFAULT_GGI);
        assertThat(testWaEmployee.getHomeHostIndicator()).isEqualTo(DEFAULT_HOME_HOST_INDICATOR);
        assertThat(testWaEmployee.getLegalGroupCompanyCode()).isEqualTo(DEFAULT_LEGAL_GROUP_COMPANY_CODE);
        assertThat(testWaEmployee.getPrefix()).isEqualTo(DEFAULT_PREFIX);
        assertThat(testWaEmployee.getLatinBirthName()).isEqualTo(DEFAULT_LATIN_BIRTH_NAME);
        assertThat(testWaEmployee.getLocalBirthName()).isEqualTo(DEFAULT_LOCAL_BIRTH_NAME);
        assertThat(testWaEmployee.getLatinCommonName()).isEqualTo(DEFAULT_LATIN_COMMON_NAME);
        assertThat(testWaEmployee.getLocalCommonName()).isEqualTo(DEFAULT_LOCAL_COMMON_NAME);
        assertThat(testWaEmployee.getLatinNameComplement()).isEqualTo(DEFAULT_LATIN_NAME_COMPLEMENT);
        assertThat(testWaEmployee.getLocalNameComplement()).isEqualTo(DEFAULT_LOCAL_NAME_COMPLEMENT);
        assertThat(testWaEmployee.getLatinFirstName()).isEqualTo(DEFAULT_LATIN_FIRST_NAME);
        assertThat(testWaEmployee.getLocalFirstName()).isEqualTo(DEFAULT_LOCAL_FIRST_NAME);
        assertThat(testWaEmployee.getEmployeeBirthDate()).isEqualTo(DEFAULT_EMPLOYEE_BIRTH_DATE);
        assertThat(testWaEmployee.getCountryOfBirth()).isEqualTo(DEFAULT_COUNTRY_OF_BIRTH);
        assertThat(testWaEmployee.getBirthDepartment()).isEqualTo(DEFAULT_BIRTH_DEPARTMENT);
        assertThat(testWaEmployee.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaEmployee.getSensitiveEmployeeFlag()).isEqualTo(DEFAULT_SENSITIVE_EMPLOYEE_FLAG);
    }

    @Test
    @Transactional
    public void createWaEmployeeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waEmployeeRepository.findAll().size();

        // Create the WaEmployee with an existing ID
        waEmployee.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaEmployeeMockMvc.perform(post("/api/wa-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waEmployee)))
            .andExpect(status().isBadRequest());

        // Validate the WaEmployee in the database
        List<WaEmployee> waEmployeeList = waEmployeeRepository.findAll();
        assertThat(waEmployeeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLocalIdNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = waEmployeeRepository.findAll().size();
        // set the field null
        waEmployee.setLocalIdNumber(null);

        // Create the WaEmployee, which fails.

        restWaEmployeeMockMvc.perform(post("/api/wa-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waEmployee)))
            .andExpect(status().isBadRequest());

        List<WaEmployee> waEmployeeList = waEmployeeRepository.findAll();
        assertThat(waEmployeeList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaEmployees() throws Exception {
        // Initialize the database
        waEmployeeRepository.saveAndFlush(waEmployee);

        // Get all the waEmployeeList
        restWaEmployeeMockMvc.perform(get("/api/wa-employees?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waEmployee.getId().intValue())))
            .andExpect(jsonPath("$.[*].ggi").value(hasItem(DEFAULT_GGI)))
            .andExpect(jsonPath("$.[*].homeHostIndicator").value(hasItem(DEFAULT_HOME_HOST_INDICATOR)))
            .andExpect(jsonPath("$.[*].legalGroupCompanyCode").value(hasItem(DEFAULT_LEGAL_GROUP_COMPANY_CODE)))
            .andExpect(jsonPath("$.[*].prefix").value(hasItem(DEFAULT_PREFIX)))
            .andExpect(jsonPath("$.[*].latinBirthName").value(hasItem(DEFAULT_LATIN_BIRTH_NAME)))
            .andExpect(jsonPath("$.[*].localBirthName").value(hasItem(DEFAULT_LOCAL_BIRTH_NAME)))
            .andExpect(jsonPath("$.[*].latinCommonName").value(hasItem(DEFAULT_LATIN_COMMON_NAME)))
            .andExpect(jsonPath("$.[*].localCommonName").value(hasItem(DEFAULT_LOCAL_COMMON_NAME)))
            .andExpect(jsonPath("$.[*].latinNameComplement").value(hasItem(DEFAULT_LATIN_NAME_COMPLEMENT)))
            .andExpect(jsonPath("$.[*].localNameComplement").value(hasItem(DEFAULT_LOCAL_NAME_COMPLEMENT)))
            .andExpect(jsonPath("$.[*].latinFirstName").value(hasItem(DEFAULT_LATIN_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].localFirstName").value(hasItem(DEFAULT_LOCAL_FIRST_NAME)))
            .andExpect(jsonPath("$.[*].employeeBirthDate").value(hasItem(DEFAULT_EMPLOYEE_BIRTH_DATE.toString())))
            .andExpect(jsonPath("$.[*].countryOfBirth").value(hasItem(DEFAULT_COUNTRY_OF_BIRTH)))
            .andExpect(jsonPath("$.[*].birthDepartment").value(hasItem(DEFAULT_BIRTH_DEPARTMENT)))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].sensitiveEmployeeFlag").value(hasItem(DEFAULT_SENSITIVE_EMPLOYEE_FLAG)));
    }
    
    @Test
    @Transactional
    public void getWaEmployee() throws Exception {
        // Initialize the database
        waEmployeeRepository.saveAndFlush(waEmployee);

        // Get the waEmployee
        restWaEmployeeMockMvc.perform(get("/api/wa-employees/{id}", waEmployee.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waEmployee.getId().intValue()))
            .andExpect(jsonPath("$.ggi").value(DEFAULT_GGI))
            .andExpect(jsonPath("$.homeHostIndicator").value(DEFAULT_HOME_HOST_INDICATOR))
            .andExpect(jsonPath("$.legalGroupCompanyCode").value(DEFAULT_LEGAL_GROUP_COMPANY_CODE))
            .andExpect(jsonPath("$.prefix").value(DEFAULT_PREFIX))
            .andExpect(jsonPath("$.latinBirthName").value(DEFAULT_LATIN_BIRTH_NAME))
            .andExpect(jsonPath("$.localBirthName").value(DEFAULT_LOCAL_BIRTH_NAME))
            .andExpect(jsonPath("$.latinCommonName").value(DEFAULT_LATIN_COMMON_NAME))
            .andExpect(jsonPath("$.localCommonName").value(DEFAULT_LOCAL_COMMON_NAME))
            .andExpect(jsonPath("$.latinNameComplement").value(DEFAULT_LATIN_NAME_COMPLEMENT))
            .andExpect(jsonPath("$.localNameComplement").value(DEFAULT_LOCAL_NAME_COMPLEMENT))
            .andExpect(jsonPath("$.latinFirstName").value(DEFAULT_LATIN_FIRST_NAME))
            .andExpect(jsonPath("$.localFirstName").value(DEFAULT_LOCAL_FIRST_NAME))
            .andExpect(jsonPath("$.employeeBirthDate").value(DEFAULT_EMPLOYEE_BIRTH_DATE.toString()))
            .andExpect(jsonPath("$.countryOfBirth").value(DEFAULT_COUNTRY_OF_BIRTH))
            .andExpect(jsonPath("$.birthDepartment").value(DEFAULT_BIRTH_DEPARTMENT))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.sensitiveEmployeeFlag").value(DEFAULT_SENSITIVE_EMPLOYEE_FLAG));
    }

    @Test
    @Transactional
    public void getNonExistingWaEmployee() throws Exception {
        // Get the waEmployee
        restWaEmployeeMockMvc.perform(get("/api/wa-employees/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaEmployee() throws Exception {
        // Initialize the database
        waEmployeeRepository.saveAndFlush(waEmployee);

        int databaseSizeBeforeUpdate = waEmployeeRepository.findAll().size();

        // Update the waEmployee
        WaEmployee updatedWaEmployee = waEmployeeRepository.findById(waEmployee.getId()).get();
        // Disconnect from session so that the updates on updatedWaEmployee are not directly saved in db
        em.detach(updatedWaEmployee);
        updatedWaEmployee
            .ggi(UPDATED_GGI)
            .homeHostIndicator(UPDATED_HOME_HOST_INDICATOR)
            .legalGroupCompanyCode(UPDATED_LEGAL_GROUP_COMPANY_CODE)
            .prefix(UPDATED_PREFIX)
            .latinBirthName(UPDATED_LATIN_BIRTH_NAME)
            .localBirthName(UPDATED_LOCAL_BIRTH_NAME)
            .latinCommonName(UPDATED_LATIN_COMMON_NAME)
            .localCommonName(UPDATED_LOCAL_COMMON_NAME)
            .latinNameComplement(UPDATED_LATIN_NAME_COMPLEMENT)
            .localNameComplement(UPDATED_LOCAL_NAME_COMPLEMENT)
            .latinFirstName(UPDATED_LATIN_FIRST_NAME)
            .localFirstName(UPDATED_LOCAL_FIRST_NAME)
            .employeeBirthDate(UPDATED_EMPLOYEE_BIRTH_DATE)
            .countryOfBirth(UPDATED_COUNTRY_OF_BIRTH)
            .birthDepartment(UPDATED_BIRTH_DEPARTMENT)
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .sensitiveEmployeeFlag(UPDATED_SENSITIVE_EMPLOYEE_FLAG);

        restWaEmployeeMockMvc.perform(put("/api/wa-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaEmployee)))
            .andExpect(status().isOk());

        // Validate the WaEmployee in the database
        List<WaEmployee> waEmployeeList = waEmployeeRepository.findAll();
        assertThat(waEmployeeList).hasSize(databaseSizeBeforeUpdate);
        WaEmployee testWaEmployee = waEmployeeList.get(waEmployeeList.size() - 1);
        assertThat(testWaEmployee.getGgi()).isEqualTo(UPDATED_GGI);
        assertThat(testWaEmployee.getHomeHostIndicator()).isEqualTo(UPDATED_HOME_HOST_INDICATOR);
        assertThat(testWaEmployee.getLegalGroupCompanyCode()).isEqualTo(UPDATED_LEGAL_GROUP_COMPANY_CODE);
        assertThat(testWaEmployee.getPrefix()).isEqualTo(UPDATED_PREFIX);
        assertThat(testWaEmployee.getLatinBirthName()).isEqualTo(UPDATED_LATIN_BIRTH_NAME);
        assertThat(testWaEmployee.getLocalBirthName()).isEqualTo(UPDATED_LOCAL_BIRTH_NAME);
        assertThat(testWaEmployee.getLatinCommonName()).isEqualTo(UPDATED_LATIN_COMMON_NAME);
        assertThat(testWaEmployee.getLocalCommonName()).isEqualTo(UPDATED_LOCAL_COMMON_NAME);
        assertThat(testWaEmployee.getLatinNameComplement()).isEqualTo(UPDATED_LATIN_NAME_COMPLEMENT);
        assertThat(testWaEmployee.getLocalNameComplement()).isEqualTo(UPDATED_LOCAL_NAME_COMPLEMENT);
        assertThat(testWaEmployee.getLatinFirstName()).isEqualTo(UPDATED_LATIN_FIRST_NAME);
        assertThat(testWaEmployee.getLocalFirstName()).isEqualTo(UPDATED_LOCAL_FIRST_NAME);
        assertThat(testWaEmployee.getEmployeeBirthDate()).isEqualTo(UPDATED_EMPLOYEE_BIRTH_DATE);
        assertThat(testWaEmployee.getCountryOfBirth()).isEqualTo(UPDATED_COUNTRY_OF_BIRTH);
        assertThat(testWaEmployee.getBirthDepartment()).isEqualTo(UPDATED_BIRTH_DEPARTMENT);
        assertThat(testWaEmployee.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaEmployee.getSensitiveEmployeeFlag()).isEqualTo(UPDATED_SENSITIVE_EMPLOYEE_FLAG);
    }

    @Test
    @Transactional
    public void updateNonExistingWaEmployee() throws Exception {
        int databaseSizeBeforeUpdate = waEmployeeRepository.findAll().size();

        // Create the WaEmployee

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaEmployeeMockMvc.perform(put("/api/wa-employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waEmployee)))
            .andExpect(status().isBadRequest());

        // Validate the WaEmployee in the database
        List<WaEmployee> waEmployeeList = waEmployeeRepository.findAll();
        assertThat(waEmployeeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaEmployee() throws Exception {
        // Initialize the database
        waEmployeeRepository.saveAndFlush(waEmployee);

        int databaseSizeBeforeDelete = waEmployeeRepository.findAll().size();

        // Delete the waEmployee
        restWaEmployeeMockMvc.perform(delete("/api/wa-employees/{id}", waEmployee.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaEmployee> waEmployeeList = waEmployeeRepository.findAll();
        assertThat(waEmployeeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
