package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaNationality;
import com.sg.hrco.test.repository.WaNationalityRepository;

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
 * Integration tests for the {@link WaNationalityResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaNationalityResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_NATIONALITY_COUNTRY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_NATIONALITY_COUNTRY_CODE = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private WaNationalityRepository waNationalityRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaNationalityMockMvc;

    private WaNationality waNationality;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaNationality createEntity(EntityManager em) {
        WaNationality waNationality = new WaNationality()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .nationalityCountryCode(DEFAULT_NATIONALITY_COUNTRY_CODE)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE);
        return waNationality;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaNationality createUpdatedEntity(EntityManager em) {
        WaNationality waNationality = new WaNationality()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .nationalityCountryCode(UPDATED_NATIONALITY_COUNTRY_CODE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE);
        return waNationality;
    }

    @BeforeEach
    public void initTest() {
        waNationality = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaNationality() throws Exception {
        int databaseSizeBeforeCreate = waNationalityRepository.findAll().size();

        // Create the WaNationality
        restWaNationalityMockMvc.perform(post("/api/wa-nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waNationality)))
            .andExpect(status().isCreated());

        // Validate the WaNationality in the database
        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeCreate + 1);
        WaNationality testWaNationality = waNationalityList.get(waNationalityList.size() - 1);
        assertThat(testWaNationality.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaNationality.getNationalityCountryCode()).isEqualTo(DEFAULT_NATIONALITY_COUNTRY_CODE);
        assertThat(testWaNationality.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
    }

    @Test
    @Transactional
    public void createWaNationalityWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waNationalityRepository.findAll().size();

        // Create the WaNationality with an existing ID
        waNationality.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaNationalityMockMvc.perform(post("/api/wa-nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waNationality)))
            .andExpect(status().isBadRequest());

        // Validate the WaNationality in the database
        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkNationalityCountryCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waNationalityRepository.findAll().size();
        // set the field null
        waNationality.setNationalityCountryCode(null);

        // Create the WaNationality, which fails.

        restWaNationalityMockMvc.perform(post("/api/wa-nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waNationality)))
            .andExpect(status().isBadRequest());

        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waNationalityRepository.findAll().size();
        // set the field null
        waNationality.setEffectiveDate(null);

        // Create the WaNationality, which fails.

        restWaNationalityMockMvc.perform(post("/api/wa-nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waNationality)))
            .andExpect(status().isBadRequest());

        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaNationalities() throws Exception {
        // Initialize the database
        waNationalityRepository.saveAndFlush(waNationality);

        // Get all the waNationalityList
        restWaNationalityMockMvc.perform(get("/api/wa-nationalities?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waNationality.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].nationalityCountryCode").value(hasItem(DEFAULT_NATIONALITY_COUNTRY_CODE)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getWaNationality() throws Exception {
        // Initialize the database
        waNationalityRepository.saveAndFlush(waNationality);

        // Get the waNationality
        restWaNationalityMockMvc.perform(get("/api/wa-nationalities/{id}", waNationality.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waNationality.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.nationalityCountryCode").value(DEFAULT_NATIONALITY_COUNTRY_CODE))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWaNationality() throws Exception {
        // Get the waNationality
        restWaNationalityMockMvc.perform(get("/api/wa-nationalities/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaNationality() throws Exception {
        // Initialize the database
        waNationalityRepository.saveAndFlush(waNationality);

        int databaseSizeBeforeUpdate = waNationalityRepository.findAll().size();

        // Update the waNationality
        WaNationality updatedWaNationality = waNationalityRepository.findById(waNationality.getId()).get();
        // Disconnect from session so that the updates on updatedWaNationality are not directly saved in db
        em.detach(updatedWaNationality);
        updatedWaNationality
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .nationalityCountryCode(UPDATED_NATIONALITY_COUNTRY_CODE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE);

        restWaNationalityMockMvc.perform(put("/api/wa-nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaNationality)))
            .andExpect(status().isOk());

        // Validate the WaNationality in the database
        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeUpdate);
        WaNationality testWaNationality = waNationalityList.get(waNationalityList.size() - 1);
        assertThat(testWaNationality.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaNationality.getNationalityCountryCode()).isEqualTo(UPDATED_NATIONALITY_COUNTRY_CODE);
        assertThat(testWaNationality.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaNationality() throws Exception {
        int databaseSizeBeforeUpdate = waNationalityRepository.findAll().size();

        // Create the WaNationality

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaNationalityMockMvc.perform(put("/api/wa-nationalities")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waNationality)))
            .andExpect(status().isBadRequest());

        // Validate the WaNationality in the database
        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaNationality() throws Exception {
        // Initialize the database
        waNationalityRepository.saveAndFlush(waNationality);

        int databaseSizeBeforeDelete = waNationalityRepository.findAll().size();

        // Delete the waNationality
        restWaNationalityMockMvc.perform(delete("/api/wa-nationalities/{id}", waNationality.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaNationality> waNationalityList = waNationalityRepository.findAll();
        assertThat(waNationalityList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
