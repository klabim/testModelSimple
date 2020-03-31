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
 * A WaNationality.
 */
@Entity
@Table(name = "wa_nationality")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaNationality implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "nationality_country_code", nullable = false)
    private String nationalityCountryCode;

    @NotNull
    @Column(name = "effective_date", nullable = false)
    private Instant effectiveDate;

    @ManyToOne
    @JsonIgnoreProperties("nationalities")
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

    public WaNationality localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getNationalityCountryCode() {
        return nationalityCountryCode;
    }

    public WaNationality nationalityCountryCode(String nationalityCountryCode) {
        this.nationalityCountryCode = nationalityCountryCode;
        return this;
    }

    public void setNationalityCountryCode(String nationalityCountryCode) {
        this.nationalityCountryCode = nationalityCountryCode;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaNationality effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaNationality waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaNationality)) {
            return false;
        }
        return id != null && id.equals(((WaNationality) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaNationality{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", nationalityCountryCode='" + getNationalityCountryCode() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            "}";
    }
}
