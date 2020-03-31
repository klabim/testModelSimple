package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaClassification;
import com.sg.hrco.test.repository.WaClassificationRepository;

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
 * Integration tests for the {@link WaClassificationResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaClassificationResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    private static final String DEFAULT_SEQUENCE = "AAAAAAAAAA";
    private static final String UPDATED_SEQUENCE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_CLASSIFICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_CLASSIFICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_CLASSIFICATION_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_CLASSIFICATION_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_CLASSIFICATION_CODE = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_CLASSIFICATION_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_GROUP_CLASSIFICATION_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_GROUP_CLASSIFICATION_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSIONAL_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSIONAL_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PROFESSIONAL_CATEGORY_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_PROFESSIONAL_CATEGORY_LABEL = "BBBBBBBBBB";

    @Autowired
    private WaClassificationRepository waClassificationRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaClassificationMockMvc;

    private WaClassification waClassification;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaClassification createEntity(EntityManager em) {
        WaClassification waClassification = new WaClassification()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE)
            .sequence(DEFAULT_SEQUENCE)
            .localClassificationCode(DEFAULT_LOCAL_CLASSIFICATION_CODE)
            .localClassificationLabel(DEFAULT_LOCAL_CLASSIFICATION_LABEL)
            .groupClassificationCode(DEFAULT_GROUP_CLASSIFICATION_CODE)
            .groupClassificationLabel(DEFAULT_GROUP_CLASSIFICATION_LABEL)
            .professionalCategoryCode(DEFAULT_PROFESSIONAL_CATEGORY_CODE)
            .professionalCategoryLabel(DEFAULT_PROFESSIONAL_CATEGORY_LABEL);
        return waClassification;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaClassification createUpdatedEntity(EntityManager em) {
        WaClassification waClassification = new WaClassification()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .localClassificationCode(UPDATED_LOCAL_CLASSIFICATION_CODE)
            .localClassificationLabel(UPDATED_LOCAL_CLASSIFICATION_LABEL)
            .groupClassificationCode(UPDATED_GROUP_CLASSIFICATION_CODE)
            .groupClassificationLabel(UPDATED_GROUP_CLASSIFICATION_LABEL)
            .professionalCategoryCode(UPDATED_PROFESSIONAL_CATEGORY_CODE)
            .professionalCategoryLabel(UPDATED_PROFESSIONAL_CATEGORY_LABEL);
        return waClassification;
    }

    @BeforeEach
    public void initTest() {
        waClassification = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaClassification() throws Exception {
        int databaseSizeBeforeCreate = waClassificationRepository.findAll().size();

        // Create the WaClassification
        restWaClassificationMockMvc.perform(post("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waClassification)))
            .andExpect(status().isCreated());

        // Validate the WaClassification in the database
        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeCreate + 1);
        WaClassification testWaClassification = waClassificationList.get(waClassificationList.size() - 1);
        assertThat(testWaClassification.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaClassification.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
        assertThat(testWaClassification.getSequence()).isEqualTo(DEFAULT_SEQUENCE);
        assertThat(testWaClassification.getLocalClassificationCode()).isEqualTo(DEFAULT_LOCAL_CLASSIFICATION_CODE);
        assertThat(testWaClassification.getLocalClassificationLabel()).isEqualTo(DEFAULT_LOCAL_CLASSIFICATION_LABEL);
        assertThat(testWaClassification.getGroupClassificationCode()).isEqualTo(DEFAULT_GROUP_CLASSIFICATION_CODE);
        assertThat(testWaClassification.getGroupClassificationLabel()).isEqualTo(DEFAULT_GROUP_CLASSIFICATION_LABEL);
        assertThat(testWaClassification.getProfessionalCategoryCode()).isEqualTo(DEFAULT_PROFESSIONAL_CATEGORY_CODE);
        assertThat(testWaClassification.getProfessionalCategoryLabel()).isEqualTo(DEFAULT_PROFESSIONAL_CATEGORY_LABEL);
    }

    @Test
    @Transactional
    public void createWaClassificationWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waClassificationRepository.findAll().size();

        // Create the WaClassification with an existing ID
        waClassification.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaClassificationMockMvc.perform(post("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waClassification)))
            .andExpect(status().isBadRequest());

        // Validate the WaClassification in the database
        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waClassificationRepository.findAll().size();
        // set the field null
        waClassification.setEffectiveDate(null);

        // Create the WaClassification, which fails.

        restWaClassificationMockMvc.perform(post("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waClassification)))
            .andExpect(status().isBadRequest());

        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkSequenceIsRequired() throws Exception {
        int databaseSizeBeforeTest = waClassificationRepository.findAll().size();
        // set the field null
        waClassification.setSequence(null);

        // Create the WaClassification, which fails.

        restWaClassificationMockMvc.perform(post("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waClassification)))
            .andExpect(status().isBadRequest());

        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalClassificationCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waClassificationRepository.findAll().size();
        // set the field null
        waClassification.setLocalClassificationCode(null);

        // Create the WaClassification, which fails.

        restWaClassificationMockMvc.perform(post("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waClassification)))
            .andExpect(status().isBadRequest());

        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaClassifications() throws Exception {
        // Initialize the database
        waClassificationRepository.saveAndFlush(waClassification);

        // Get all the waClassificationList
        restWaClassificationMockMvc.perform(get("/api/wa-classifications?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waClassification.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())))
            .andExpect(jsonPath("$.[*].sequence").value(hasItem(DEFAULT_SEQUENCE)))
            .andExpect(jsonPath("$.[*].localClassificationCode").value(hasItem(DEFAULT_LOCAL_CLASSIFICATION_CODE)))
            .andExpect(jsonPath("$.[*].localClassificationLabel").value(hasItem(DEFAULT_LOCAL_CLASSIFICATION_LABEL)))
            .andExpect(jsonPath("$.[*].groupClassificationCode").value(hasItem(DEFAULT_GROUP_CLASSIFICATION_CODE)))
            .andExpect(jsonPath("$.[*].groupClassificationLabel").value(hasItem(DEFAULT_GROUP_CLASSIFICATION_LABEL)))
            .andExpect(jsonPath("$.[*].professionalCategoryCode").value(hasItem(DEFAULT_PROFESSIONAL_CATEGORY_CODE)))
            .andExpect(jsonPath("$.[*].professionalCategoryLabel").value(hasItem(DEFAULT_PROFESSIONAL_CATEGORY_LABEL)));
    }
    
    @Test
    @Transactional
    public void getWaClassification() throws Exception {
        // Initialize the database
        waClassificationRepository.saveAndFlush(waClassification);

        // Get the waClassification
        restWaClassificationMockMvc.perform(get("/api/wa-classifications/{id}", waClassification.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waClassification.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()))
            .andExpect(jsonPath("$.sequence").value(DEFAULT_SEQUENCE))
            .andExpect(jsonPath("$.localClassificationCode").value(DEFAULT_LOCAL_CLASSIFICATION_CODE))
            .andExpect(jsonPath("$.localClassificationLabel").value(DEFAULT_LOCAL_CLASSIFICATION_LABEL))
            .andExpect(jsonPath("$.groupClassificationCode").value(DEFAULT_GROUP_CLASSIFICATION_CODE))
            .andExpect(jsonPath("$.groupClassificationLabel").value(DEFAULT_GROUP_CLASSIFICATION_LABEL))
            .andExpect(jsonPath("$.professionalCategoryCode").value(DEFAULT_PROFESSIONAL_CATEGORY_CODE))
            .andExpect(jsonPath("$.professionalCategoryLabel").value(DEFAULT_PROFESSIONAL_CATEGORY_LABEL));
    }

    @Test
    @Transactional
    public void getNonExistingWaClassification() throws Exception {
        // Get the waClassification
        restWaClassificationMockMvc.perform(get("/api/wa-classifications/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaClassification() throws Exception {
        // Initialize the database
        waClassificationRepository.saveAndFlush(waClassification);

        int databaseSizeBeforeUpdate = waClassificationRepository.findAll().size();

        // Update the waClassification
        WaClassification updatedWaClassification = waClassificationRepository.findById(waClassification.getId()).get();
        // Disconnect from session so that the updates on updatedWaClassification are not directly saved in db
        em.detach(updatedWaClassification);
        updatedWaClassification
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .effectiveDate(UPDATED_EFFECTIVE_DATE)
            .sequence(UPDATED_SEQUENCE)
            .localClassificationCode(UPDATED_LOCAL_CLASSIFICATION_CODE)
            .localClassificationLabel(UPDATED_LOCAL_CLASSIFICATION_LABEL)
            .groupClassificationCode(UPDATED_GROUP_CLASSIFICATION_CODE)
            .groupClassificationLabel(UPDATED_GROUP_CLASSIFICATION_LABEL)
            .professionalCategoryCode(UPDATED_PROFESSIONAL_CATEGORY_CODE)
            .professionalCategoryLabel(UPDATED_PROFESSIONAL_CATEGORY_LABEL);

        restWaClassificationMockMvc.perform(put("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaClassification)))
            .andExpect(status().isOk());

        // Validate the WaClassification in the database
        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeUpdate);
        WaClassification testWaClassification = waClassificationList.get(waClassificationList.size() - 1);
        assertThat(testWaClassification.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaClassification.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
        assertThat(testWaClassification.getSequence()).isEqualTo(UPDATED_SEQUENCE);
        assertThat(testWaClassification.getLocalClassificationCode()).isEqualTo(UPDATED_LOCAL_CLASSIFICATION_CODE);
        assertThat(testWaClassification.getLocalClassificationLabel()).isEqualTo(UPDATED_LOCAL_CLASSIFICATION_LABEL);
        assertThat(testWaClassification.getGroupClassificationCode()).isEqualTo(UPDATED_GROUP_CLASSIFICATION_CODE);
        assertThat(testWaClassification.getGroupClassificationLabel()).isEqualTo(UPDATED_GROUP_CLASSIFICATION_LABEL);
        assertThat(testWaClassification.getProfessionalCategoryCode()).isEqualTo(UPDATED_PROFESSIONAL_CATEGORY_CODE);
        assertThat(testWaClassification.getProfessionalCategoryLabel()).isEqualTo(UPDATED_PROFESSIONAL_CATEGORY_LABEL);
    }

    @Test
    @Transactional
    public void updateNonExistingWaClassification() throws Exception {
        int databaseSizeBeforeUpdate = waClassificationRepository.findAll().size();

        // Create the WaClassification

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaClassificationMockMvc.perform(put("/api/wa-classifications")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waClassification)))
            .andExpect(status().isBadRequest());

        // Validate the WaClassification in the database
        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaClassification() throws Exception {
        // Initialize the database
        waClassificationRepository.saveAndFlush(waClassification);

        int databaseSizeBeforeDelete = waClassificationRepository.findAll().size();

        // Delete the waClassification
        restWaClassificationMockMvc.perform(delete("/api/wa-classifications/{id}", waClassification.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaClassification> waClassificationList = waClassificationRepository.findAll();
        assertThat(waClassificationList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
