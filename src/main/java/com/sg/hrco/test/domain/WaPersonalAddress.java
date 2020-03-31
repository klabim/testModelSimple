package com.sg.hrco.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;

/**
 * A WaPersonalAddress.
 */
@Entity
@Table(name = "wa_personal_address")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaPersonalAddress implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "personal_address_type", nullable = false)
    private String personalAddressType;

    @Column(name = "additional_address_1")
    private String additionalAddress1;

    @Column(name = "additional_address_2")
    private String additionalAddress2;

    @Column(name = "physical_address")
    private String physicalAddress;

    @Column(name = "additional_postal_delivery")
    private String additionalPostalDelivery;

    @Column(name = "zip_code_postal_code")
    private String zipCodePostalCode;

    @Column(name = "employee_city_of_residence")
    private String employeeCityOfResidence;

    @Column(name = "country_of_residence")
    private String countryOfResidence;

    @NotNull
    @Column(name = "effective_date", nullable = false)
    private Instant effectiveDate;

    @ManyToOne
    @JsonIgnoreProperties("personalAddresses")
    private WaEmployee waEmployee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocalIdNumber() {
        return localIdNumber;
    }

    public WaPersonalAddress localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getPersonalAddressType() {
        return personalAddressType;
    }

    public WaPersonalAddress personalAddressType(String personalAddressType) {
        this.personalAddressType = personalAddressType;
        return this;
    }

    public void setPersonalAddressType(String personalAddressType) {
        this.personalAddressType = personalAddressType;
    }

    public String getAdditionalAddress1() {
        return additionalAddress1;
    }

    public WaPersonalAddress additionalAddress1(String additionalAddress1) {
        this.additionalAddress1 = additionalAddress1;
        return this;
    }

    public void setAdditionalAddress1(String additionalAddress1) {
        this.additionalAddress1 = additionalAddress1;
    }

    public String getAdditionalAddress2() {
        return additionalAddress2;
    }

    public WaPersonalAddress additionalAddress2(String additionalAddress2) {
        this.additionalAddress2 = additionalAddress2;
        return this;
    }

    public void setAdditionalAddress2(String additionalAddress2) {
        this.additionalAddress2 = additionalAddress2;
    }

    public String getPhysicalAddress() {
        return physicalAddress;
    }

    public WaPersonalAddress physicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
        return this;
    }

    public void setPhysicalAddress(String physicalAddress) {
        this.physicalAddress = physicalAddress;
    }

    public String getAdditionalPostalDelivery() {
        return additionalPostalDelivery;
    }

    public WaPersonalAddress additionalPostalDelivery(String additionalPostalDelivery) {
        this.additionalPostalDelivery = additionalPostalDelivery;
        return this;
    }

    public void setAdditionalPostalDelivery(String additionalPostalDelivery) {
        this.additionalPostalDelivery = additionalPostalDelivery;
    }

    public String getZipCodePostalCode() {
        return zipCodePostalCode;
    }

    public WaPersonalAddress zipCodePostalCode(String zipCodePostalCode) {
        this.zipCodePostalCode = zipCodePostalCode;
        return this;
    }

    public void setZipCodePostalCode(String zipCodePostalCode) {
        this.zipCodePostalCode = zipCodePostalCode;
    }

    public String getEmployeeCityOfResidence() {
        return employeeCityOfResidence;
    }

    public WaPersonalAddress employeeCityOfResidence(String employeeCityOfResidence) {
        this.employeeCityOfResidence = employeeCityOfResidence;
        return this;
    }

    public void setEmployeeCityOfResidence(String employeeCityOfResidence) {
        this.employeeCityOfResidence = employeeCityOfResidence;
    }

    public String getCountryOfResidence() {
        return countryOfResidence;
    }

    public WaPersonalAddress countryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
        return this;
    }

    public void setCountryOfResidence(String countryOfResidence) {
        this.countryOfResidence = countryOfResidence;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaPersonalAddress effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaPersonalAddress waEmployee(WaEmployee waEmployee) {
        this.waEmployee = waEmployee;
        return this;
    }

    public void setWaEmployee(WaEmployee waEmployee) {
        this.waEmployee = waEmployee;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof WaPersonalAddress)) {
            return false;
        }
        return id != null && id.equals(((WaPersonalAddress) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaPersonalAddress{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", personalAddressType='" + getPersonalAddressType() + "'" +
            ", additionalAddress1='" + getAdditionalAddress1() + "'" +
            ", additionalAddress2='" + getAdditionalAddress2() + "'" +
            ", physicalAddress='" + getPhysicalAddress() + "'" +
            ", additionalPostalDelivery='" + getAdditionalPostalDelivery() + "'" +
            ", zipCodePostalCode='" + getZipCodePostalCode() + "'" +
            ", employeeCityOfResidence='" + getEmployeeCityOfResidence() + "'" +
            ", countryOfResidence='" + getCountryOfResidence() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            "}";
    }
}
