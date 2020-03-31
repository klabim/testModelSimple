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
 * A WaMaritalStatus.
 */
@Entity
@Table(name = "wa_marital_status")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaMaritalStatus implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "marital_status_code", nullable = false)
    private String maritalStatusCode;

    @Column(name = "marital_status_label")
    private String maritalStatusLabel;

    @NotNull
    @Column(name = "effective_date", nullable = false)
    private Instant effectiveDate;

    @ManyToOne
    @JsonIgnoreProperties("maritalStatuses")
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

    public WaMaritalStatus localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getMaritalStatusCode() {
        return maritalStatusCode;
    }

    public WaMaritalStatus maritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
        return this;
    }

    public void setMaritalStatusCode(String maritalStatusCode) {
        this.maritalStatusCode = maritalStatusCode;
    }

    public String getMaritalStatusLabel() {
        return maritalStatusLabel;
    }

    public WaMaritalStatus maritalStatusLabel(String maritalStatusLabel) {
        this.maritalStatusLabel = maritalStatusLabel;
        return this;
    }

    public void setMaritalStatusLabel(String maritalStatusLabel) {
        this.maritalStatusLabel = maritalStatusLabel;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaMaritalStatus effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaMaritalStatus waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaMaritalStatus)) {
            return false;
        }
        return id != null && id.equals(((WaMaritalStatus) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaMaritalStatus{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", maritalStatusCode='" + getMaritalStatusCode() + "'" +
            ", maritalStatusLabel='" + getMaritalStatusLabel() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            "}";
    }
}
