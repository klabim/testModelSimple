package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.ManagerialLink;
import com.sg.hrco.test.repository.ManagerialLinkRepository;

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
 * Integration tests for the {@link ManagerialLinkResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class ManagerialLinkResourceIT {

    private static final String DEFAULT_MANAGERIAL_LINK_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_MANAGERIAL_LINK_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_LOCAL_ID_DIRECT_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_DIRECT_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_GGI_DIRECT_LINE_MANAGER = "AAAAAAAAAA";
    private static final String UPDATED_GGI_DIRECT_LINE_MANAGER = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER_LAST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER_LAST_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_MANAGER_FIRST_NAME = "AAAAAAAAAA";
    private static final String UPDATED_MANAGER_FIRST_NAME = "BBBBBBBBBB";

    @Autowired
    private ManagerialLinkRepository managerialLinkRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restManagerialLinkMockMvc;

    private ManagerialLink managerialLink;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManagerialLink createEntity(EntityManager em) {
        ManagerialLink managerialLink = new ManagerialLink()
            .managerialLinkType(DEFAULT_MANAGERIAL_LINK_TYPE)
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .localIDDirectManager(DEFAULT_LOCAL_ID_DIRECT_MANAGER)
            .ggiDirectLineManager(DEFAULT_GGI_DIRECT_LINE_MANAGER)
            .managerLastName(DEFAULT_MANAGER_LAST_NAME)
            .managerFirstName(DEFAULT_MANAGER_FIRST_NAME);
        return managerialLink;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ManagerialLink createUpdatedEntity(EntityManager em) {
        ManagerialLink managerialLink = new ManagerialLink()
            .managerialLinkType(UPDATED_MANAGERIAL_LINK_TYPE)
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .localIDDirectManager(UPDATED_LOCAL_ID_DIRECT_MANAGER)
            .ggiDirectLineManager(UPDATED_GGI_DIRECT_LINE_MANAGER)
            .managerLastName(UPDATED_MANAGER_LAST_NAME)
            .managerFirstName(UPDATED_MANAGER_FIRST_NAME);
        return managerialLink;
    }

    @BeforeEach
    public void initTest() {
        managerialLink = createEntity(em);
    }

    @Test
    @Transactional
    public void createManagerialLink() throws Exception {
        int databaseSizeBeforeCreate = managerialLinkRepository.findAll().size();

        // Create the ManagerialLink
        restManagerialLinkMockMvc.perform(post("/api/managerial-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerialLink)))
            .andExpect(status().isCreated());

        // Validate the ManagerialLink in the database
        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeCreate + 1);
        ManagerialLink testManagerialLink = managerialLinkList.get(managerialLinkList.size() - 1);
        assertThat(testManagerialLink.getManagerialLinkType()).isEqualTo(DEFAULT_MANAGERIAL_LINK_TYPE);
        assertThat(testManagerialLink.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testManagerialLink.getLocalIDDirectManager()).isEqualTo(DEFAULT_LOCAL_ID_DIRECT_MANAGER);
        assertThat(testManagerialLink.getGgiDirectLineManager()).isEqualTo(DEFAULT_GGI_DIRECT_LINE_MANAGER);
        assertThat(testManagerialLink.getManagerLastName()).isEqualTo(DEFAULT_MANAGER_LAST_NAME);
        assertThat(testManagerialLink.getManagerFirstName()).isEqualTo(DEFAULT_MANAGER_FIRST_NAME);
    }

    @Test
    @Transactional
    public void createManagerialLinkWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = managerialLinkRepository.findAll().size();

        // Create the ManagerialLink with an existing ID
        managerialLink.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restManagerialLinkMockMvc.perform(post("/api/managerial-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerialLink)))
            .andExpect(status().isBadRequest());

        // Validate the ManagerialLink in the database
        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkLocalIdNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerialLinkRepository.findAll().size();
        // set the field null
        managerialLink.setLocalIdNumber(null);

        // Create the ManagerialLink, which fails.

        restManagerialLinkMockMvc.perform(post("/api/managerial-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerialLink)))
            .andExpect(status().isBadRequest());

        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkLocalIDDirectManagerIsRequired() throws Exception {
        int databaseSizeBeforeTest = managerialLinkRepository.findAll().size();
        // set the field null
        managerialLink.setLocalIDDirectManager(null);

        // Create the ManagerialLink, which fails.

        restManagerialLinkMockMvc.perform(post("/api/managerial-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerialLink)))
            .andExpect(status().isBadRequest());

        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllManagerialLinks() throws Exception {
        // Initialize the database
        managerialLinkRepository.saveAndFlush(managerialLink);

        // Get all the managerialLinkList
        restManagerialLinkMockMvc.perform(get("/api/managerial-links?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(managerialLink.getId().intValue())))
            .andExpect(jsonPath("$.[*].managerialLinkType").value(hasItem(DEFAULT_MANAGERIAL_LINK_TYPE)))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].localIDDirectManager").value(hasItem(DEFAULT_LOCAL_ID_DIRECT_MANAGER)))
            .andExpect(jsonPath("$.[*].ggiDirectLineManager").value(hasItem(DEFAULT_GGI_DIRECT_LINE_MANAGER)))
            .andExpect(jsonPath("$.[*].managerLastName").value(hasItem(DEFAULT_MANAGER_LAST_NAME)))
            .andExpect(jsonPath("$.[*].managerFirstName").value(hasItem(DEFAULT_MANAGER_FIRST_NAME)));
    }
    
    @Test
    @Transactional
    public void getManagerialLink() throws Exception {
        // Initialize the database
        managerialLinkRepository.saveAndFlush(managerialLink);

        // Get the managerialLink
        restManagerialLinkMockMvc.perform(get("/api/managerial-links/{id}", managerialLink.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(managerialLink.getId().intValue()))
            .andExpect(jsonPath("$.managerialLinkType").value(DEFAULT_MANAGERIAL_LINK_TYPE))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.localIDDirectManager").value(DEFAULT_LOCAL_ID_DIRECT_MANAGER))
            .andExpect(jsonPath("$.ggiDirectLineManager").value(DEFAULT_GGI_DIRECT_LINE_MANAGER))
            .andExpect(jsonPath("$.managerLastName").value(DEFAULT_MANAGER_LAST_NAME))
            .andExpect(jsonPath("$.managerFirstName").value(DEFAULT_MANAGER_FIRST_NAME));
    }

    @Test
    @Transactional
    public void getNonExistingManagerialLink() throws Exception {
        // Get the managerialLink
        restManagerialLinkMockMvc.perform(get("/api/managerial-links/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateManagerialLink() throws Exception {
        // Initialize the database
        managerialLinkRepository.saveAndFlush(managerialLink);

        int databaseSizeBeforeUpdate = managerialLinkRepository.findAll().size();

        // Update the managerialLink
        ManagerialLink updatedManagerialLink = managerialLinkRepository.findById(managerialLink.getId()).get();
        // Disconnect from session so that the updates on updatedManagerialLink are not directly saved in db
        em.detach(updatedManagerialLink);
        updatedManagerialLink
            .managerialLinkType(UPDATED_MANAGERIAL_LINK_TYPE)
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .localIDDirectManager(UPDATED_LOCAL_ID_DIRECT_MANAGER)
            .ggiDirectLineManager(UPDATED_GGI_DIRECT_LINE_MANAGER)
            .managerLastName(UPDATED_MANAGER_LAST_NAME)
            .managerFirstName(UPDATED_MANAGER_FIRST_NAME);

        restManagerialLinkMockMvc.perform(put("/api/managerial-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedManagerialLink)))
            .andExpect(status().isOk());

        // Validate the ManagerialLink in the database
        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeUpdate);
        ManagerialLink testManagerialLink = managerialLinkList.get(managerialLinkList.size() - 1);
        assertThat(testManagerialLink.getManagerialLinkType()).isEqualTo(UPDATED_MANAGERIAL_LINK_TYPE);
        assertThat(testManagerialLink.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testManagerialLink.getLocalIDDirectManager()).isEqualTo(UPDATED_LOCAL_ID_DIRECT_MANAGER);
        assertThat(testManagerialLink.getGgiDirectLineManager()).isEqualTo(UPDATED_GGI_DIRECT_LINE_MANAGER);
        assertThat(testManagerialLink.getManagerLastName()).isEqualTo(UPDATED_MANAGER_LAST_NAME);
        assertThat(testManagerialLink.getManagerFirstName()).isEqualTo(UPDATED_MANAGER_FIRST_NAME);
    }

    @Test
    @Transactional
    public void updateNonExistingManagerialLink() throws Exception {
        int databaseSizeBeforeUpdate = managerialLinkRepository.findAll().size();

        // Create the ManagerialLink

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restManagerialLinkMockMvc.perform(put("/api/managerial-links")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(managerialLink)))
            .andExpect(status().isBadRequest());

        // Validate the ManagerialLink in the database
        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteManagerialLink() throws Exception {
        // Initialize the database
        managerialLinkRepository.saveAndFlush(managerialLink);

        int databaseSizeBeforeDelete = managerialLinkRepository.findAll().size();

        // Delete the managerialLink
        restManagerialLinkMockMvc.perform(delete("/api/managerial-links/{id}", managerialLink.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ManagerialLink> managerialLinkList = managerialLinkRepository.findAll();
        assertThat(managerialLinkList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
