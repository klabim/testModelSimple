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
 * A WaHostCompany.
 */
@Entity
@Table(name = "wa_host_company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaHostCompany implements Serializable {

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
    @Column(name = "assignment_type", nullable = false)
    private String assignmentType;

    @Column(name = "assignment_type_label")
    private String assignmentTypeLabel;

    @NotNull
    @Column(name = "host_company_code", nullable = false)
    private String hostCompanyCode;

    @Column(name = "group_host_company_code")
    private String groupHostCompanyCode;

    @ManyToOne
    @JsonIgnoreProperties("hostCompanies")
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

    public WaHostCompany localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaHostCompany effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getSequence() {
        return sequence;
    }

    public WaHostCompany sequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public WaHostCompany assignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
        return this;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentTypeLabel() {
        return assignmentTypeLabel;
    }

    public WaHostCompany assignmentTypeLabel(String assignmentTypeLabel) {
        this.assignmentTypeLabel = assignmentTypeLabel;
        return this;
    }

    public void setAssignmentTypeLabel(String assignmentTypeLabel) {
        this.assignmentTypeLabel = assignmentTypeLabel;
    }

    public String getHostCompanyCode() {
        return hostCompanyCode;
    }

    public WaHostCompany hostCompanyCode(String hostCompanyCode) {
        this.hostCompanyCode = hostCompanyCode;
        return this;
    }

    public void setHostCompanyCode(String hostCompanyCode) {
        this.hostCompanyCode = hostCompanyCode;
    }

    public String getGroupHostCompanyCode() {
        return groupHostCompanyCode;
    }

    public WaHostCompany groupHostCompanyCode(String groupHostCompanyCode) {
        this.groupHostCompanyCode = groupHostCompanyCode;
        return this;
    }

    public void setGroupHostCompanyCode(String groupHostCompanyCode) {
        this.groupHostCompanyCode = groupHostCompanyCode;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaHostCompany waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaHostCompany)) {
            return false;
        }
        return id != null && id.equals(((WaHostCompany) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaHostCompany{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", assignmentType='" + getAssignmentType() + "'" +
            ", assignmentTypeLabel='" + getAssignmentTypeLabel() + "'" +
            ", hostCompanyCode='" + getHostCompanyCode() + "'" +
            ", groupHostCompanyCode='" + getGroupHostCompanyCode() + "'" +
            "}";
    }
}
