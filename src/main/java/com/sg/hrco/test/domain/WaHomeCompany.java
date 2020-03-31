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
 * A WaHomeCompany.
 */
@Entity
@Table(name = "wa_home_company")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaHomeCompany implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "effective_assignment_date", nullable = false)
    private Instant effectiveAssignmentDate;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private String sequence;

    @NotNull
    @Column(name = "assignment_type", nullable = false)
    private String assignmentType;

    @Column(name = "assignment_type_label")
    private String assignmentTypeLabel;

    @NotNull
    @Column(name = "home_company_code", nullable = false)
    private String homeCompanyCode;

    @Column(name = "group_home_company_code")
    private String groupHomeCompanyCode;

    @ManyToOne
    @JsonIgnoreProperties("homeCompanies")
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

    public WaHomeCompany localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public Instant getEffectiveAssignmentDate() {
        return effectiveAssignmentDate;
    }

    public WaHomeCompany effectiveAssignmentDate(Instant effectiveAssignmentDate) {
        this.effectiveAssignmentDate = effectiveAssignmentDate;
        return this;
    }

    public void setEffectiveAssignmentDate(Instant effectiveAssignmentDate) {
        this.effectiveAssignmentDate = effectiveAssignmentDate;
    }

    public String getSequence() {
        return sequence;
    }

    public WaHomeCompany sequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getAssignmentType() {
        return assignmentType;
    }

    public WaHomeCompany assignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
        return this;
    }

    public void setAssignmentType(String assignmentType) {
        this.assignmentType = assignmentType;
    }

    public String getAssignmentTypeLabel() {
        return assignmentTypeLabel;
    }

    public WaHomeCompany assignmentTypeLabel(String assignmentTypeLabel) {
        this.assignmentTypeLabel = assignmentTypeLabel;
        return this;
    }

    public void setAssignmentTypeLabel(String assignmentTypeLabel) {
        this.assignmentTypeLabel = assignmentTypeLabel;
    }

    public String getHomeCompanyCode() {
        return homeCompanyCode;
    }

    public WaHomeCompany homeCompanyCode(String homeCompanyCode) {
        this.homeCompanyCode = homeCompanyCode;
        return this;
    }

    public void setHomeCompanyCode(String homeCompanyCode) {
        this.homeCompanyCode = homeCompanyCode;
    }

    public String getGroupHomeCompanyCode() {
        return groupHomeCompanyCode;
    }

    public WaHomeCompany groupHomeCompanyCode(String groupHomeCompanyCode) {
        this.groupHomeCompanyCode = groupHomeCompanyCode;
        return this;
    }

    public void setGroupHomeCompanyCode(String groupHomeCompanyCode) {
        this.groupHomeCompanyCode = groupHomeCompanyCode;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaHomeCompany waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaHomeCompany)) {
            return false;
        }
        return id != null && id.equals(((WaHomeCompany) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaHomeCompany{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", effectiveAssignmentDate='" + getEffectiveAssignmentDate() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", assignmentType='" + getAssignmentType() + "'" +
            ", assignmentTypeLabel='" + getAssignmentTypeLabel() + "'" +
            ", homeCompanyCode='" + getHomeCompanyCode() + "'" +
            ", groupHomeCompanyCode='" + getGroupHomeCompanyCode() + "'" +
            "}";
    }
}
