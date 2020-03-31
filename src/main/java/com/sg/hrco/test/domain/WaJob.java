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
 * A WaJob.
 */
@Entity
@Table(name = "wa_job")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaJob implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "effective_date", nullable = false)
    private Instant effectiveDate;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private String sequence;

    @NotNull
    @Column(name = "local_position_code", nullable = false)
    private String localPositionCode;

    @Column(name = "local_position_level")
    private String localPositionLevel;

    @Column(name = "position_entry_date")
    private Instant positionEntryDate;

    @Column(name = "job_code_local_value")
    private String jobCodeLocalValue;

    @Column(name = "job_code_groupe_value")
    private String jobCodeGroupeValue;

    @Column(name = "job_entry_date")
    private Instant jobEntryDate;

    @Column(name = "local_collective_agreement_code")
    private String localCollectiveAgreementCode;

    @Column(name = "local_collective_agreement_label")
    private String localCollectiveAgreementLabel;

    @Column(name = "org_relationship")
    private String orgRelationship;

    @Column(name = "prescripteur")
    private String prescripteur;

    @ManyToOne
    @JsonIgnoreProperties("jobs")
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

    public WaJob localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaJob effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getSequence() {
        return sequence;
    }

    public WaJob sequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getLocalPositionCode() {
        return localPositionCode;
    }

    public WaJob localPositionCode(String localPositionCode) {
        this.localPositionCode = localPositionCode;
        return this;
    }

    public void setLocalPositionCode(String localPositionCode) {
        this.localPositionCode = localPositionCode;
    }

    public String getLocalPositionLevel() {
        return localPositionLevel;
    }

    public WaJob localPositionLevel(String localPositionLevel) {
        this.localPositionLevel = localPositionLevel;
        return this;
    }

    public void setLocalPositionLevel(String localPositionLevel) {
        this.localPositionLevel = localPositionLevel;
    }

    public Instant getPositionEntryDate() {
        return positionEntryDate;
    }

    public WaJob positionEntryDate(Instant positionEntryDate) {
        this.positionEntryDate = positionEntryDate;
        return this;
    }

    public void setPositionEntryDate(Instant positionEntryDate) {
        this.positionEntryDate = positionEntryDate;
    }

    public String getJobCodeLocalValue() {
        return jobCodeLocalValue;
    }

    public WaJob jobCodeLocalValue(String jobCodeLocalValue) {
        this.jobCodeLocalValue = jobCodeLocalValue;
        return this;
    }

    public void setJobCodeLocalValue(String jobCodeLocalValue) {
        this.jobCodeLocalValue = jobCodeLocalValue;
    }

    public String getJobCodeGroupeValue() {
        return jobCodeGroupeValue;
    }

    public WaJob jobCodeGroupeValue(String jobCodeGroupeValue) {
        this.jobCodeGroupeValue = jobCodeGroupeValue;
        return this;
    }

    public void setJobCodeGroupeValue(String jobCodeGroupeValue) {
        this.jobCodeGroupeValue = jobCodeGroupeValue;
    }

    public Instant getJobEntryDate() {
        return jobEntryDate;
    }

    public WaJob jobEntryDate(Instant jobEntryDate) {
        this.jobEntryDate = jobEntryDate;
        return this;
    }

    public void setJobEntryDate(Instant jobEntryDate) {
        this.jobEntryDate = jobEntryDate;
    }

    public String getLocalCollectiveAgreementCode() {
        return localCollectiveAgreementCode;
    }

    public WaJob localCollectiveAgreementCode(String localCollectiveAgreementCode) {
        this.localCollectiveAgreementCode = localCollectiveAgreementCode;
        return this;
    }

    public void setLocalCollectiveAgreementCode(String localCollectiveAgreementCode) {
        this.localCollectiveAgreementCode = localCollectiveAgreementCode;
    }

    public String getLocalCollectiveAgreementLabel() {
        return localCollectiveAgreementLabel;
    }

    public WaJob localCollectiveAgreementLabel(String localCollectiveAgreementLabel) {
        this.localCollectiveAgreementLabel = localCollectiveAgreementLabel;
        return this;
    }

    public void setLocalCollectiveAgreementLabel(String localCollectiveAgreementLabel) {
        this.localCollectiveAgreementLabel = localCollectiveAgreementLabel;
    }

    public String getOrgRelationship() {
        return orgRelationship;
    }

    public WaJob orgRelationship(String orgRelationship) {
        this.orgRelationship = orgRelationship;
        return this;
    }

    public void setOrgRelationship(String orgRelationship) {
        this.orgRelationship = orgRelationship;
    }

    public String getPrescripteur() {
        return prescripteur;
    }

    public WaJob prescripteur(String prescripteur) {
        this.prescripteur = prescripteur;
        return this;
    }

    public void setPrescripteur(String prescripteur) {
        this.prescripteur = prescripteur;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaJob waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaJob)) {
            return false;
        }
        return id != null && id.equals(((WaJob) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaJob{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", localPositionCode='" + getLocalPositionCode() + "'" +
            ", localPositionLevel='" + getLocalPositionLevel() + "'" +
            ", positionEntryDate='" + getPositionEntryDate() + "'" +
            ", jobCodeLocalValue='" + getJobCodeLocalValue() + "'" +
            ", jobCodeGroupeValue='" + getJobCodeGroupeValue() + "'" +
            ", jobEntryDate='" + getJobEntryDate() + "'" +
            ", localCollectiveAgreementCode='" + getLocalCollectiveAgreementCode() + "'" +
            ", localCollectiveAgreementLabel='" + getLocalCollectiveAgreementLabel() + "'" +
            ", orgRelationship='" + getOrgRelationship() + "'" +
            ", prescripteur='" + getPrescripteur() + "'" +
            "}";
    }
}
