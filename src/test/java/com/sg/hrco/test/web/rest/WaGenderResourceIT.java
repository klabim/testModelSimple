package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaGender;
import com.sg.hrco.test.repository.WaGenderRepository;

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
 * Integration tests for the {@link WaGenderResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaGenderResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_LOCAL_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_LOCAL_GENDER = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_GENDER = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_GENDER = "BBBBBBBBBB";

    @Autowired
    private WaGenderRepository waGenderRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaGenderMockMvc;

    private WaGender waGender;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaGender createEntity(EntityManager em) {
        WaGender waGender = new WaGender()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .employeeLocalGender(DEFAULT_EMPLOYEE_LOCAL_GENDER)
            .employeeGender(DEFAULT_EMPLOYEE_GENDER);
        return waGender;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaGender createUpdatedEntity(EntityManager em) {
        WaGender waGender = new WaGender()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .employeeLocalGender(UPDATED_EMPLOYEE_LOCAL_GENDER)
            .employeeGender(UPDATED_EMPLOYEE_GENDER);
        return waGender;
    }

    @BeforeEach
    public void initTest() {
        waGender = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaGender() throws Exception {
        int databaseSizeBeforeCreate = waGenderRepository.findAll().size();

        // Create the WaGender
        restWaGenderMockMvc.perform(post("/api/wa-genders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waGender)))
            .andExpect(status().isCreated());

        // Validate the WaGender in the database
        List<WaGender> waGenderList = waGenderRepository.findAll();
        assertThat(waGenderList).hasSize(databaseSizeBeforeCreate + 1);
        WaGender testWaGender = waGenderList.get(waGenderList.size() - 1);
        assertThat(testWaGender.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaGender.getEmployeeLocalGender()).isEqualTo(DEFAULT_EMPLOYEE_LOCAL_GENDER);
        assertThat(testWaGender.getEmployeeGender()).isEqualTo(DEFAULT_EMPLOYEE_GENDER);
    }

    @Test
    @Transactional
    public void createWaGenderWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waGenderRepository.findAll().size();

        // Create the WaGender with an existing ID
        waGender.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaGenderMockMvc.perform(post("/api/wa-genders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waGender)))
            .andExpect(status().isBadRequest());

        // Validate the WaGender in the database
        List<WaGender> waGenderList = waGenderRepository.findAll();
        assertThat(waGenderList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEmployeeLocalGenderIsRequired() throws Exception {
        int databaseSizeBeforeTest = waGenderRepository.findAll().size();
        // set the field null
        waGender.setEmployeeLocalGender(null);

        // Create the WaGender, which fails.

        restWaGenderMockMvc.perform(post("/api/wa-genders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waGender)))
            .andExpect(status().isBadRequest());

        List<WaGender> waGenderList = waGenderRepository.findAll();
        assertThat(waGenderList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaGenders() throws Exception {
        // Initialize the database
        waGenderRepository.saveAndFlush(waGender);

        // Get all the waGenderList
        restWaGenderMockMvc.perform(get("/api/wa-genders?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waGender.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].employeeLocalGender").value(hasItem(DEFAULT_EMPLOYEE_LOCAL_GENDER)))
            .andExpect(jsonPath("$.[*].employeeGender").value(hasItem(DEFAULT_EMPLOYEE_GENDER)));
    }
    
    @Test
    @Transactional
    public void getWaGender() throws Exception {
        // Initialize the database
        waGenderRepository.saveAndFlush(waGender);

        // Get the waGender
        restWaGenderMockMvc.perform(get("/api/wa-genders/{id}", waGender.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waGender.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.employeeLocalGender").value(DEFAULT_EMPLOYEE_LOCAL_GENDER))
            .andExpect(jsonPath("$.employeeGender").value(DEFAULT_EMPLOYEE_GENDER));
    }

    @Test
    @Transactional
    public void getNonExistingWaGender() throws Exception {
        // Get the waGender
        restWaGenderMockMvc.perform(get("/api/wa-genders/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaGender() throws Exception {
        // Initialize the database
        waGenderRepository.saveAndFlush(waGender);

        int databaseSizeBeforeUpdate = waGenderRepository.findAll().size();

        // Update the waGender
        WaGender updatedWaGender = waGenderRepository.findById(waGender.getId()).get();
        // Disconnect from session so that the updates on updatedWaGender are not directly saved in db
        em.detach(updatedWaGender);
        updatedWaGender
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .employeeLocalGender(UPDATED_EMPLOYEE_LOCAL_GENDER)
            .employeeGender(UPDATED_EMPLOYEE_GENDER);

        restWaGenderMockMvc.perform(put("/api/wa-genders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaGender)))
            .andExpect(status().isOk());

        // Validate the WaGender in the database
        List<WaGender> waGenderList = waGenderRepository.findAll();
        assertThat(waGenderList).hasSize(databaseSizeBeforeUpdate);
        WaGender testWaGender = waGenderList.get(waGenderList.size() - 1);
        assertThat(testWaGender.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaGender.getEmployeeLocalGender()).isEqualTo(UPDATED_EMPLOYEE_LOCAL_GENDER);
        assertThat(testWaGender.getEmployeeGender()).isEqualTo(UPDATED_EMPLOYEE_GENDER);
    }

    @Test
    @Transactional
    public void updateNonExistingWaGender() throws Exception {
        int databaseSizeBeforeUpdate = waGenderRepository.findAll().size();

        // Create the WaGender

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaGenderMockMvc.perform(put("/api/wa-genders")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waGender)))
            .andExpect(status().isBadRequest());

        // Validate the WaGender in the database
        List<WaGender> waGenderList = waGenderRepository.findAll();
        assertThat(waGenderList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaGender() throws Exception {
        // Initialize the database
        waGenderRepository.saveAndFlush(waGender);

        int databaseSizeBeforeDelete = waGenderRepository.findAll().size();

        // Delete the waGender
        restWaGenderMockMvc.perform(delete("/api/wa-genders/{id}", waGender.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaGender> waGenderList = waGenderRepository.findAll();
        assertThat(waGenderList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
