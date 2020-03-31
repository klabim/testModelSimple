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
 * A WaSeniority.
 */
@Entity
@Table(name = "wa_seniority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaSeniority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "group_sg_date_of_entry", nullable = false)
    private Instant groupSgDateOfEntry;

    @NotNull
    @Column(name = "sg_seniority_date", nullable = false)
    private Instant sgSeniorityDate;

    @NotNull
    @Column(name = "hire_date", nullable = false)
    private Instant hireDate;

    @Column(name = "banking_sector_seniority")
    private String bankingSectorSeniority;

    @Column(name = "ending_of_trial_period_estimated_date")
    private Instant endingOfTrialPeriodEstimatedDate;

    @ManyToOne
    @JsonIgnoreProperties("seniorities")
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

    public WaSeniority localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public Instant getGroupSgDateOfEntry() {
        return groupSgDateOfEntry;
    }

    public WaSeniority groupSgDateOfEntry(Instant groupSgDateOfEntry) {
        this.groupSgDateOfEntry = groupSgDateOfEntry;
        return this;
    }

    public void setGroupSgDateOfEntry(Instant groupSgDateOfEntry) {
        this.groupSgDateOfEntry = groupSgDateOfEntry;
    }

    public Instant getSgSeniorityDate() {
        return sgSeniorityDate;
    }

    public WaSeniority sgSeniorityDate(Instant sgSeniorityDate) {
        this.sgSeniorityDate = sgSeniorityDate;
        return this;
    }

    public void setSgSeniorityDate(Instant sgSeniorityDate) {
        this.sgSeniorityDate = sgSeniorityDate;
    }

    public Instant getHireDate() {
        return hireDate;
    }

    public WaSeniority hireDate(Instant hireDate) {
        this.hireDate = hireDate;
        return this;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public String getBankingSectorSeniority() {
        return bankingSectorSeniority;
    }

    public WaSeniority bankingSectorSeniority(String bankingSectorSeniority) {
        this.bankingSectorSeniority = bankingSectorSeniority;
        return this;
    }

    public void setBankingSectorSeniority(String bankingSectorSeniority) {
        this.bankingSectorSeniority = bankingSectorSeniority;
    }

    public Instant getEndingOfTrialPeriodEstimatedDate() {
        return endingOfTrialPeriodEstimatedDate;
    }

    public WaSeniority endingOfTrialPeriodEstimatedDate(Instant endingOfTrialPeriodEstimatedDate) {
        this.endingOfTrialPeriodEstimatedDate = endingOfTrialPeriodEstimatedDate;
        return this;
    }

    public void setEndingOfTrialPeriodEstimatedDate(Instant endingOfTrialPeriodEstimatedDate) {
        this.endingOfTrialPeriodEstimatedDate = endingOfTrialPeriodEstimatedDate;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaSeniority waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaSeniority)) {
            return false;
        }
        return id != null && id.equals(((WaSeniority) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaSeniority{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", groupSgDateOfEntry='" + getGroupSgDateOfEntry() + "'" +
            ", sgSeniorityDate='" + getSgSeniorityDate() + "'" +
            ", hireDate='" + getHireDate() + "'" +
            ", bankingSectorSeniority='" + getBankingSectorSeniority() + "'" +
            ", endingOfTrialPeriodEstimatedDate='" + getEndingOfTrialPeriodEstimatedDate() + "'" +
            "}";
    }
}
