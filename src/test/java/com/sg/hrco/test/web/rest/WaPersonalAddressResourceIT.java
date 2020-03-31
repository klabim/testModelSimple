package com.sg.hrco.test.web.rest;

import com.sg.hrco.test.TestModelSimpleApp;
import com.sg.hrco.test.domain.WaPersonalAddress;
import com.sg.hrco.test.repository.WaPersonalAddressRepository;

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
 * Integration tests for the {@link WaPersonalAddressResource} REST controller.
 */
@SpringBootTest(classes = TestModelSimpleApp.class)

@AutoConfigureMockMvc
@WithMockUser
public class WaPersonalAddressResourceIT {

    private static final String DEFAULT_LOCAL_ID_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_LOCAL_ID_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PERSONAL_ADDRESS_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_PERSONAL_ADDRESS_TYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_ADDRESS_1 = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_ADDRESS_1 = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_ADDRESS_2 = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_ADDRESS_2 = "BBBBBBBBBB";

    private static final String DEFAULT_PHYSICAL_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PHYSICAL_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_ADDITIONAL_POSTAL_DELIVERY = "AAAAAAAAAA";
    private static final String UPDATED_ADDITIONAL_POSTAL_DELIVERY = "BBBBBBBBBB";

    private static final String DEFAULT_ZIP_CODE_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_ZIP_CODE_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EMPLOYEE_CITY_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_EMPLOYEE_CITY_OF_RESIDENCE = "BBBBBBBBBB";

    private static final String DEFAULT_COUNTRY_OF_RESIDENCE = "AAAAAAAAAA";
    private static final String UPDATED_COUNTRY_OF_RESIDENCE = "BBBBBBBBBB";

    private static final Instant DEFAULT_EFFECTIVE_DATE = Instant.ofEpochMilli(0L);
    private static final Instant UPDATED_EFFECTIVE_DATE = Instant.now().truncatedTo(ChronoUnit.MILLIS);

    @Autowired
    private WaPersonalAddressRepository waPersonalAddressRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restWaPersonalAddressMockMvc;

    private WaPersonalAddress waPersonalAddress;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaPersonalAddress createEntity(EntityManager em) {
        WaPersonalAddress waPersonalAddress = new WaPersonalAddress()
            .localIdNumber(DEFAULT_LOCAL_ID_NUMBER)
            .personalAddressType(DEFAULT_PERSONAL_ADDRESS_TYPE)
            .additionalAddress1(DEFAULT_ADDITIONAL_ADDRESS_1)
            .additionalAddress2(DEFAULT_ADDITIONAL_ADDRESS_2)
            .physicalAddress(DEFAULT_PHYSICAL_ADDRESS)
            .additionalPostalDelivery(DEFAULT_ADDITIONAL_POSTAL_DELIVERY)
            .zipCodePostalCode(DEFAULT_ZIP_CODE_POSTAL_CODE)
            .employeeCityOfResidence(DEFAULT_EMPLOYEE_CITY_OF_RESIDENCE)
            .countryOfResidence(DEFAULT_COUNTRY_OF_RESIDENCE)
            .effectiveDate(DEFAULT_EFFECTIVE_DATE);
        return waPersonalAddress;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static WaPersonalAddress createUpdatedEntity(EntityManager em) {
        WaPersonalAddress waPersonalAddress = new WaPersonalAddress()
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .personalAddressType(UPDATED_PERSONAL_ADDRESS_TYPE)
            .additionalAddress1(UPDATED_ADDITIONAL_ADDRESS_1)
            .additionalAddress2(UPDATED_ADDITIONAL_ADDRESS_2)
            .physicalAddress(UPDATED_PHYSICAL_ADDRESS)
            .additionalPostalDelivery(UPDATED_ADDITIONAL_POSTAL_DELIVERY)
            .zipCodePostalCode(UPDATED_ZIP_CODE_POSTAL_CODE)
            .employeeCityOfResidence(UPDATED_EMPLOYEE_CITY_OF_RESIDENCE)
            .countryOfResidence(UPDATED_COUNTRY_OF_RESIDENCE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE);
        return waPersonalAddress;
    }

    @BeforeEach
    public void initTest() {
        waPersonalAddress = createEntity(em);
    }

    @Test
    @Transactional
    public void createWaPersonalAddress() throws Exception {
        int databaseSizeBeforeCreate = waPersonalAddressRepository.findAll().size();

        // Create the WaPersonalAddress
        restWaPersonalAddressMockMvc.perform(post("/api/wa-personal-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waPersonalAddress)))
            .andExpect(status().isCreated());

        // Validate the WaPersonalAddress in the database
        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeCreate + 1);
        WaPersonalAddress testWaPersonalAddress = waPersonalAddressList.get(waPersonalAddressList.size() - 1);
        assertThat(testWaPersonalAddress.getLocalIdNumber()).isEqualTo(DEFAULT_LOCAL_ID_NUMBER);
        assertThat(testWaPersonalAddress.getPersonalAddressType()).isEqualTo(DEFAULT_PERSONAL_ADDRESS_TYPE);
        assertThat(testWaPersonalAddress.getAdditionalAddress1()).isEqualTo(DEFAULT_ADDITIONAL_ADDRESS_1);
        assertThat(testWaPersonalAddress.getAdditionalAddress2()).isEqualTo(DEFAULT_ADDITIONAL_ADDRESS_2);
        assertThat(testWaPersonalAddress.getPhysicalAddress()).isEqualTo(DEFAULT_PHYSICAL_ADDRESS);
        assertThat(testWaPersonalAddress.getAdditionalPostalDelivery()).isEqualTo(DEFAULT_ADDITIONAL_POSTAL_DELIVERY);
        assertThat(testWaPersonalAddress.getZipCodePostalCode()).isEqualTo(DEFAULT_ZIP_CODE_POSTAL_CODE);
        assertThat(testWaPersonalAddress.getEmployeeCityOfResidence()).isEqualTo(DEFAULT_EMPLOYEE_CITY_OF_RESIDENCE);
        assertThat(testWaPersonalAddress.getCountryOfResidence()).isEqualTo(DEFAULT_COUNTRY_OF_RESIDENCE);
        assertThat(testWaPersonalAddress.getEffectiveDate()).isEqualTo(DEFAULT_EFFECTIVE_DATE);
    }

    @Test
    @Transactional
    public void createWaPersonalAddressWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = waPersonalAddressRepository.findAll().size();

        // Create the WaPersonalAddress with an existing ID
        waPersonalAddress.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restWaPersonalAddressMockMvc.perform(post("/api/wa-personal-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waPersonalAddress)))
            .andExpect(status().isBadRequest());

        // Validate the WaPersonalAddress in the database
        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void checkPersonalAddressTypeIsRequired() throws Exception {
        int databaseSizeBeforeTest = waPersonalAddressRepository.findAll().size();
        // set the field null
        waPersonalAddress.setPersonalAddressType(null);

        // Create the WaPersonalAddress, which fails.

        restWaPersonalAddressMockMvc.perform(post("/api/wa-personal-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waPersonalAddress)))
            .andExpect(status().isBadRequest());

        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void checkEffectiveDateIsRequired() throws Exception {
        int databaseSizeBeforeTest = waPersonalAddressRepository.findAll().size();
        // set the field null
        waPersonalAddress.setEffectiveDate(null);

        // Create the WaPersonalAddress, which fails.

        restWaPersonalAddressMockMvc.perform(post("/api/wa-personal-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waPersonalAddress)))
            .andExpect(status().isBadRequest());

        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeTest);
    }

    @Test
    @Transactional
    public void getAllWaPersonalAddresses() throws Exception {
        // Initialize the database
        waPersonalAddressRepository.saveAndFlush(waPersonalAddress);

        // Get all the waPersonalAddressList
        restWaPersonalAddressMockMvc.perform(get("/api/wa-personal-addresses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(waPersonalAddress.getId().intValue())))
            .andExpect(jsonPath("$.[*].localIdNumber").value(hasItem(DEFAULT_LOCAL_ID_NUMBER)))
            .andExpect(jsonPath("$.[*].personalAddressType").value(hasItem(DEFAULT_PERSONAL_ADDRESS_TYPE)))
            .andExpect(jsonPath("$.[*].additionalAddress1").value(hasItem(DEFAULT_ADDITIONAL_ADDRESS_1)))
            .andExpect(jsonPath("$.[*].additionalAddress2").value(hasItem(DEFAULT_ADDITIONAL_ADDRESS_2)))
            .andExpect(jsonPath("$.[*].physicalAddress").value(hasItem(DEFAULT_PHYSICAL_ADDRESS)))
            .andExpect(jsonPath("$.[*].additionalPostalDelivery").value(hasItem(DEFAULT_ADDITIONAL_POSTAL_DELIVERY)))
            .andExpect(jsonPath("$.[*].zipCodePostalCode").value(hasItem(DEFAULT_ZIP_CODE_POSTAL_CODE)))
            .andExpect(jsonPath("$.[*].employeeCityOfResidence").value(hasItem(DEFAULT_EMPLOYEE_CITY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].countryOfResidence").value(hasItem(DEFAULT_COUNTRY_OF_RESIDENCE)))
            .andExpect(jsonPath("$.[*].effectiveDate").value(hasItem(DEFAULT_EFFECTIVE_DATE.toString())));
    }
    
    @Test
    @Transactional
    public void getWaPersonalAddress() throws Exception {
        // Initialize the database
        waPersonalAddressRepository.saveAndFlush(waPersonalAddress);

        // Get the waPersonalAddress
        restWaPersonalAddressMockMvc.perform(get("/api/wa-personal-addresses/{id}", waPersonalAddress.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(waPersonalAddress.getId().intValue()))
            .andExpect(jsonPath("$.localIdNumber").value(DEFAULT_LOCAL_ID_NUMBER))
            .andExpect(jsonPath("$.personalAddressType").value(DEFAULT_PERSONAL_ADDRESS_TYPE))
            .andExpect(jsonPath("$.additionalAddress1").value(DEFAULT_ADDITIONAL_ADDRESS_1))
            .andExpect(jsonPath("$.additionalAddress2").value(DEFAULT_ADDITIONAL_ADDRESS_2))
            .andExpect(jsonPath("$.physicalAddress").value(DEFAULT_PHYSICAL_ADDRESS))
            .andExpect(jsonPath("$.additionalPostalDelivery").value(DEFAULT_ADDITIONAL_POSTAL_DELIVERY))
            .andExpect(jsonPath("$.zipCodePostalCode").value(DEFAULT_ZIP_CODE_POSTAL_CODE))
            .andExpect(jsonPath("$.employeeCityOfResidence").value(DEFAULT_EMPLOYEE_CITY_OF_RESIDENCE))
            .andExpect(jsonPath("$.countryOfResidence").value(DEFAULT_COUNTRY_OF_RESIDENCE))
            .andExpect(jsonPath("$.effectiveDate").value(DEFAULT_EFFECTIVE_DATE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingWaPersonalAddress() throws Exception {
        // Get the waPersonalAddress
        restWaPersonalAddressMockMvc.perform(get("/api/wa-personal-addresses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateWaPersonalAddress() throws Exception {
        // Initialize the database
        waPersonalAddressRepository.saveAndFlush(waPersonalAddress);

        int databaseSizeBeforeUpdate = waPersonalAddressRepository.findAll().size();

        // Update the waPersonalAddress
        WaPersonalAddress updatedWaPersonalAddress = waPersonalAddressRepository.findById(waPersonalAddress.getId()).get();
        // Disconnect from session so that the updates on updatedWaPersonalAddress are not directly saved in db
        em.detach(updatedWaPersonalAddress);
        updatedWaPersonalAddress
            .localIdNumber(UPDATED_LOCAL_ID_NUMBER)
            .personalAddressType(UPDATED_PERSONAL_ADDRESS_TYPE)
            .additionalAddress1(UPDATED_ADDITIONAL_ADDRESS_1)
            .additionalAddress2(UPDATED_ADDITIONAL_ADDRESS_2)
            .physicalAddress(UPDATED_PHYSICAL_ADDRESS)
            .additionalPostalDelivery(UPDATED_ADDITIONAL_POSTAL_DELIVERY)
            .zipCodePostalCode(UPDATED_ZIP_CODE_POSTAL_CODE)
            .employeeCityOfResidence(UPDATED_EMPLOYEE_CITY_OF_RESIDENCE)
            .countryOfResidence(UPDATED_COUNTRY_OF_RESIDENCE)
            .effectiveDate(UPDATED_EFFECTIVE_DATE);

        restWaPersonalAddressMockMvc.perform(put("/api/wa-personal-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedWaPersonalAddress)))
            .andExpect(status().isOk());

        // Validate the WaPersonalAddress in the database
        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeUpdate);
        WaPersonalAddress testWaPersonalAddress = waPersonalAddressList.get(waPersonalAddressList.size() - 1);
        assertThat(testWaPersonalAddress.getLocalIdNumber()).isEqualTo(UPDATED_LOCAL_ID_NUMBER);
        assertThat(testWaPersonalAddress.getPersonalAddressType()).isEqualTo(UPDATED_PERSONAL_ADDRESS_TYPE);
        assertThat(testWaPersonalAddress.getAdditionalAddress1()).isEqualTo(UPDATED_ADDITIONAL_ADDRESS_1);
        assertThat(testWaPersonalAddress.getAdditionalAddress2()).isEqualTo(UPDATED_ADDITIONAL_ADDRESS_2);
        assertThat(testWaPersonalAddress.getPhysicalAddress()).isEqualTo(UPDATED_PHYSICAL_ADDRESS);
        assertThat(testWaPersonalAddress.getAdditionalPostalDelivery()).isEqualTo(UPDATED_ADDITIONAL_POSTAL_DELIVERY);
        assertThat(testWaPersonalAddress.getZipCodePostalCode()).isEqualTo(UPDATED_ZIP_CODE_POSTAL_CODE);
        assertThat(testWaPersonalAddress.getEmployeeCityOfResidence()).isEqualTo(UPDATED_EMPLOYEE_CITY_OF_RESIDENCE);
        assertThat(testWaPersonalAddress.getCountryOfResidence()).isEqualTo(UPDATED_COUNTRY_OF_RESIDENCE);
        assertThat(testWaPersonalAddress.getEffectiveDate()).isEqualTo(UPDATED_EFFECTIVE_DATE);
    }

    @Test
    @Transactional
    public void updateNonExistingWaPersonalAddress() throws Exception {
        int databaseSizeBeforeUpdate = waPersonalAddressRepository.findAll().size();

        // Create the WaPersonalAddress

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restWaPersonalAddressMockMvc.perform(put("/api/wa-personal-addresses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(waPersonalAddress)))
            .andExpect(status().isBadRequest());

        // Validate the WaPersonalAddress in the database
        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteWaPersonalAddress() throws Exception {
        // Initialize the database
        waPersonalAddressRepository.saveAndFlush(waPersonalAddress);

        int databaseSizeBeforeDelete = waPersonalAddressRepository.findAll().size();

        // Delete the waPersonalAddress
        restWaPersonalAddressMockMvc.perform(delete("/api/wa-personal-addresses/{id}", waPersonalAddress.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<WaPersonalAddress> waPersonalAddressList = waPersonalAddressRepository.findAll();
        assertThat(waPersonalAddressList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
