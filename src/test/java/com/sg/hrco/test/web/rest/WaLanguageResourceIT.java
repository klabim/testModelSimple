package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaLanguage;
import com.sg.hrco.test.repository.WaLanguageRepository;

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
 * Integration tests for the {@link WaLanguageResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaLanguageResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_SPEAK_PROFICIENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_SPEAK_PROFICIENCY_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_READ_PROFICIENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_READ_PROFICIENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_READ_PROFICIENCY_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_READ_PROFICIENCY_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_WRITE_PROFICIENCY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_WRITE_PROFICIENCY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_WRITE_PROFICIENCY_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_WRITE_PROFICIENCY_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_NATIVE_LANGUAGE = "AAAAAAAAAA";
    private static final String UPDATED_NATIVE_LANGUAGE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_LEVEL_LOCAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_LEVEL_LOCAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_LEVEL_LOCAL_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_LEVEL_LOCAL_LABEL = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_LEVEL_GROUP_CODE = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_LEVEL_GROUP_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_LANGUAGE_LEVEL_GROUP_LABEL = "AAAAAAAAAA";
    private static final String UPDATED_LANGUAGE_LEVEL_GROUP_LABEL = "BBBBBBBBBB";

    @Autowired
    private WaLanguageRepository waLanguageRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaLanguageMockMvc;

    private WaLanguage waLanguage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaLanguage createEntity(EntityManager em) {
        WaLanguage waLanguage = new WaLanguage()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .languageCode(DEFAULT_LANGUAGE_CODE)
            .languageLabel(DEFAULT_LANGUAGE_LABEL)
            .languageSpeakProficiencyCode(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_CODE)
            .languageSpeakProficiencyLabel(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_LABEL)
            .languageReadProficiencyCode(DEFAULT_LANGUAGE_READ_PROFICIENCY_CODE)
            .languageReadProficiencyLabel(DEFAULT_LANGUAGE_READ_PROFICIENCY_LABEL)
            .languageWriteProficiencyCode(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_CODE)
            .languageWriteProficiencyLabel(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_LABEL)
            .nativeLanguage(DEFAULT_NATIVE_LANGUAGE)
            .languageLevelLocalCode(DEFAULT_LANGUAGE_LEVEL_LOCAL_CODE)
            .languageLevelLocalLabel(DEFAULT_LANGUAGE_LEVEL_LOCAL_LABEL)
            .languageLevelGroupCode(DEFAULT_LANGUAGE_LEVEL_GROUP_CODE)
            .languageLevelGroupLabel(DEFAULT_LANGUAGE_LEVEL_GROUP_LABEL);
        return waLanguage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaLanguage createUpdatedEntity(EntityManager em) {
        WaLanguage waLanguage = new WaLanguage()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .languageCode(UPDATED_LANGUAGE_CODE)
            .languageLabel(UPDATED_LANGUAGE_LABEL)
            .languageSpeakProficiencyCode(UPDATED_LANGUAGE_SPEAK_PROFICIENCY_CODE)
            .languageSpeakProficiencyLabel(UPDATED_LANGUAGE_SPEAK_PROFICIENCY_LABEL)
            .languageReadProficiencyCode(UPDATED_LANGUAGE_READ_PROFICIENCY_CODE)
            .languageReadProficiencyLabel(UPDATED_LANGUAGE_READ_PROFICIENCY_LABEL)
            .languageWriteProficiencyCode(UPDATED_LANGUAGE_WRITE_PROFICIENCY_CODE)
            .languageWriteProficiencyLabel(UPDATED_LANGUAGE_WRITE_PROFICIENCY_LABEL)
            .nativeLanguage(UPDATED_NATIVE_LANGUAGE)
            .languageLevelLocalCode(UPDATED_LANGUAGE_LEVEL_LOCAL_CODE)
            .languageLevelLocalLabel(UPDATED_LANGUAGE_LEVEL_LOCAL_LABEL)
            .languageLevelGroupCode(UPDATED_LANGUAGE_LEVEL_GROUP_CODE)
            .languageLevelGroupLabel(UPDATED_LANGUAGE_LEVEL_GROUP_LABEL);
        return waLanguage;
    }

    @BeforeEach
    public void initTest() {
        waLanguage = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaLanguage() throws Exception {
        int databaseSizeBeforeCreate = waLanguageRepository.findAll().size();

        // Create the WaLanguage
        restWaLanguageMockMvc.perform(post("/api/wa-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waLanguage)))
            .andExpect(status().isCreated());

        // Validate the WaLanguage in the database
        List<WaLanguage> waLanguageList = waLanguageRepository.findAll();
        assertThat(waLanguageList).hasSize(databaseSizeBeforeCreate + 1);
        WaLanguage testWaLanguage = waLanguageList.get(waLanguageList.size() - 1);
        assertThat(testWaLanguage.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaLanguage.getLanguageCode()).isEqualTo(DEFAULT_LANGUAGE_CODE);
        assertThat(testWaLanguage.getLanguageLabel()).isEqualTo(DEFAULT_LANGUAGE_LABEL);
        assertThat(testWaLanguage.getLanguageSpeakProficiencyCode()).isEqualTo(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_CODE);
        assertThat(testWaLanguage.getLanguageSpeakProficiencyLabel()).isEqualTo(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_LABEL);
        assertThat(testWaLanguage.getLanguageReadProficiencyCode()).isEqualTo(DEFAULT_LANGUAGE_READ_PROFICIENCY_CODE);
        assertThat(testWaLanguage.getLanguageReadProficiencyLabel()).isEqualTo(DEFAULT_LANGUAGE_READ_PROFICIENCY_LABEL);
        assertThat(testWaLanguage.getLanguageWriteProficiencyCode()).isEqualTo(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_CODE);
        assertThat(testWaLanguage.getLanguageWriteProficiencyLabel()).isEqualTo(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_LABEL);
        assertThat(testWaLanguage.getNativeLanguage()).isEqualTo(DEFAULT_NATIVE_LANGUAGE);
        assertThat(testWaLanguage.getLanguageLevelLocalCode()).isEqualTo(DEFAULT_LANGUAGE_LEVEL_LOCAL_CODE);
        assertThat(testWaLanguage.getLanguageLevelLocalLabel()).isEqualTo(DEFAULT_LANGUAGE_LEVEL_LOCAL_LABEL);
        assertThat(testWaLanguage.getLanguageLevelGroupCode()).isEqualTo(DEFAULT_LANGUAGE_LEVEL_GROUP_CODE);
        assertThat(testWaLanguage.getLanguageLevelGroupLabel()).isEqualTo(DEFAULT_LANGUAGE_LEVEL_GROUP_LABEL);
    }

    @Test
    @Transactional
    public void createWaLanguageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waLanguageRepository.findAll().size();

        // Create the WaLanguage with an existing ID
        waLanguage.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaLanguageMockMvc.perform(post("/api/wa-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waLanguage)))
            .andExpect(status().isBadRequest());

        // Validate the WaLanguage in the database
        List<WaLanguage> waLanguageList = waLanguageRepository.findAll();
        assertThat(waLanguageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLanguageCodeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waLanguageRepository.findAll().size();
        // set the field null
        waLanguage.setLanguageCode(null);

        // Create the WaLanguage, which fails.

        restWaLanguageMockMvc.perform(post("/api/wa-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waLanguage)))
            .andExpect(status().isBadRequest());

        List<WaLanguage> waLanguageList = waLanguageRepository.findAll();
        assertThat(waLanguageList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaLanguages() throws Exception {
        // Initialize the database
        waLanguageRepository.saveAndFlush(waLanguage);

        // Get all the waLanguageList
        restWaLanguageMockMvc.perform(get("/api/wa-languages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waLanguage.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].languageCode").value(hasItem(DEFAULT_LANGUAGE_CODE)))
            .andExpect(jsonPath("$.[*].languageLabel").value(hasItem(DEFAULT_LANGUAGE_LABEL)))
            .andExpect(jsonPath("$.[*].languageSpeakProficiencyCode").value(hasItem(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_CODE)))
            .andExpect(jsonPath("$.[*].languageSpeakProficiencyLabel").value(hasItem(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_LABEL)))
            .andExpect(jsonPath("$.[*].languageReadProficiencyCode").value(hasItem(DEFAULT_LANGUAGE_READ_PROFICIENCY_CODE)))
            .andExpect(jsonPath("$.[*].languageReadProficiencyLabel").value(hasItem(DEFAULT_LANGUAGE_READ_PROFICIENCY_LABEL)))
            .andExpect(jsonPath("$.[*].languageWriteProficiencyCode").value(hasItem(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_CODE)))
            .andExpect(jsonPath("$.[*].languageWriteProficiencyLabel").value(hasItem(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_LABEL)))
            .andExpect(jsonPath("$.[*].nativeLanguage").value(hasItem(DEFAULT_NATIVE_LANGUAGE)))
            .andExpect(jsonPath("$.[*].languageLevelLocalCode").value(hasItem(DEFAULT_LANGUAGE_LEVEL_LOCAL_CODE)))
            .andExpect(jsonPath("$.[*].languageLevelLocalLabel").value(hasItem(DEFAULT_LANGUAGE_LEVEL_LOCAL_LABEL)))
            .andExpect(jsonPath("$.[*].languageLevelGroupCode").value(hasItem(DEFAULT_LANGUAGE_LEVEL_GROUP_CODE)))
            .andExpect(jsonPath("$.[*].languageLevelGroupLabel").value(hasItem(DEFAULT_LANGUAGE_LEVEL_GROUP_LABEL)));
    }
    
    @Test
    @Transactional
    public void getWaLanguage() throws Exception {
        // Initialize the database
        waLanguageRepository.saveAndFlush(waLanguage);

        // Get the waLanguage
        restWaLanguageMockMvc.perform(get("/api/wa-languages/{id}", waLanguage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waLanguage.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.languageCode").value(DEFAULT_LANGUAGE_CODE))
            .andExpect(jsonPath("$.languageLabel").value(DEFAULT_LANGUAGE_LABEL))
            .andExpect(jsonPath("$.languageSpeakProficiencyCode").value(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_CODE))
            .andExpect(jsonPath("$.languageSpeakProficiencyLabel").value(DEFAULT_LANGUAGE_SPEAK_PROFICIENCY_LABEL))
            .andExpect(jsonPath("$.languageReadProficiencyCode").value(DEFAULT_LANGUAGE_READ_PROFICIENCY_CODE))
            .andExpect(jsonPath("$.languageReadProficiencyLabel").value(DEFAULT_LANGUAGE_READ_PROFICIENCY_LABEL))
            .andExpect(jsonPath("$.languageWriteProficiencyCode").value(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_CODE))
            .andExpect(jsonPath("$.languageWriteProficiencyLabel").value(DEFAULT_LANGUAGE_WRITE_PROFICIENCY_LABEL))
            .andExpect(jsonPath("$.nativeLanguage").value(DEFAULT_NATIVE_LANGUAGE))
            .andExpect(jsonPath("$.languageLevelLocalCode").value(DEFAULT_LANGUAGE_LEVEL_LOCAL_CODE))
            .andExpect(jsonPath("$.languageLevelLocalLabel").value(DEFAULT_LANGUAGE_LEVEL_LOCAL_LABEL))
            .andExpect(jsonPath("$.languageLevelGroupCode").value(DEFAULT_LANGUAGE_LEVEL_GROUP_CODE))
            .andExpect(jsonPath("$.languageLevelGroupLabel").value(DEFAULT_LANGUAGE_LEVEL_GROUP_LABEL));
    }

    @Test
    @Transactional
    public void getNonExistingWaLanguage() throws Exception {
        // Get the waLanguage
        restWaLanguageMockMvc.perform(get("/api/wa-languages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaLanguage() throws Exception {
        // Initialize the database
        waLanguageRepository.saveAndFlush(waLanguage);

        int databaseSizeBeforeUpdate = waLanguageRepository.findAll().size();

        // Update the waLanguage
        WaLanguage updatedWaLanguage = waLanguageRepository.findById(waLanguage.getId()).get();
        // Disconnect from session so that the updates on updatedWaLanguage are not directly saved in db
        em.detach(updatedWaLanguage);
        updatedWaLanguage
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .languageCode(UPDATED_LANGUAGE_CODE)
            .languageLabel(UPDATED_LANGUAGE_LABEL)
            .languageSpeakProficiencyCode(UPDATED_LANGUAGE_SPEAK_PROFICIENCY_CODE)
            .languageSpeakProficiencyLabel(UPDATED_LANGUAGE_SPEAK_PROFICIENCY_LABEL)
            .languageReadProficiencyCode(UPDATED_LANGUAGE_READ_PROFICIENCY_CODE)
            .languageReadProficiencyLabel(UPDATED_LANGUAGE_READ_PROFICIENCY_LABEL)
            .languageWriteProficiencyCode(UPDATED_LANGUAGE_WRITE_PROFICIENCY_CODE)
            .languageWriteProficiencyLabel(UPDATED_LANGUAGE_WRITE_PROFICIENCY_LABEL)
            .nativeLanguage(UPDATED_NATIVE_LANGUAGE)
            .languageLevelLocalCode(UPDATED_LANGUAGE_LEVEL_LOCAL_CODE)
            .languageLevelLocalLabel(UPDATED_LANGUAGE_LEVEL_LOCAL_LABEL)
            .languageLevelGroupCode(UPDATED_LANGUAGE_LEVEL_GROUP_CODE)
            .languageLevelGroupLabel(UPDATED_LANGUAGE_LEVEL_GROUP_LABEL);

        restWaLanguageMockMvc.perform(put("/api/wa-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaLanguage)))
            .andExpect(status().isOk());

        // Validate the WaLanguage in the database
        List<WaLanguage> waLanguageList = waLanguageRepository.findAll();
        assertThat(waLanguageList).hasSize(databaseSizeBeforeUpdate);
        WaLanguage testWaLanguage = waLanguageList.get(waLanguageList.size() - 1);
        assertThat(testWaLanguage.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaLanguage.getLanguageCode()).isEqualTo(UPDATED_LANGUAGE_CODE);
        assertThat(testWaLanguage.getLanguageLabel()).isEqualTo(UPDATED_LANGUAGE_LABEL);
        assertThat(testWaLanguage.getLanguageSpeakProficiencyCode()).isEqualTo(UPDATED_LANGUAGE_SPEAK_PROFICIENCY_CODE);
        assertThat(testWaLanguage.getLanguageSpeakProficiencyLabel()).isEqualTo(UPDATED_LANGUAGE_SPEAK_PROFICIENCY_LABEL);
        assertThat(testWaLanguage.getLanguageReadProficiencyCode()).isEqualTo(UPDATED_LANGUAGE_READ_PROFICIENCY_CODE);
        assertThat(testWaLanguage.getLanguageReadProficiencyLabel()).isEqualTo(UPDATED_LANGUAGE_READ_PROFICIENCY_LABEL);
        assertThat(testWaLanguage.getLanguageWriteProficiencyCode()).isEqualTo(UPDATED_LANGUAGE_WRITE_PROFICIENCY_CODE);
        assertThat(testWaLanguage.getLanguageWriteProficiencyLabel()).isEqualTo(UPDATED_LANGUAGE_WRITE_PROFICIENCY_LABEL);
        assertThat(testWaLanguage.getNativeLanguage()).isEqualTo(UPDATED_NATIVE_LANGUAGE);
        assertThat(testWaLanguage.getLanguageLevelLocalCode()).isEqualTo(UPDATED_LANGUAGE_LEVEL_LOCAL_CODE);
        assertThat(testWaLanguage.getLanguageLevelLocalLabel()).isEqualTo(UPDATED_LANGUAGE_LEVEL_LOCAL_LABEL);
        assertThat(testWaLanguage.getLanguageLevelGroupCode()).isEqualTo(UPDATED_LANGUAGE_LEVEL_GROUP_CODE);
        assertThat(testWaLanguage.getLanguageLevelGroupLabel()).isEqualTo(UPDATED_LANGUAGE_LEVEL_GROUP_LABEL);
    }

    @Test
    @Transactional
    public void updateNonExistingWaLanguage() throws Exception {
        int databaseSizeBeforeUpdate = waLanguageRepository.findAll().size();

        // Create the WaLanguage

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaLanguageMockMvc.perform(put("/api/wa-languages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waLanguage)))
            .andExpect(status().isBadRequest());

        // Validate the WaLanguage in the database
        List<WaLanguage> waLanguageList = waLanguageRepository.findAll();
        assertThat(waLanguageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaLanguage() throws Exception {
        // Initialize the database
        waLanguageRepository.saveAndFlush(waLanguage);

        int databaseSizeBeforeDelete = waLanguageRepository.findAll().size();

        // Delete the waLanguage
        restWaLanguageMockMvc.perform(delete("/api/wa-languages/{id}", waLanguage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaLanguage> waLanguageList = waLanguageRepository.findAll();
        assertThat(waLanguageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
