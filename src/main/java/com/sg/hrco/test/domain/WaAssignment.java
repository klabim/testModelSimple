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
 * A WaAssignment.
 */
@Entity
@Table(name = "wa_assignment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaAssignment implements Serializable {

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

    @Column(name = "local_company_code")
    private String localCompanyCode;

    @Column(name = "legal_group_company_code")
    private String legalGroupCompanyCode;

    @Column(name = "establishment")
    private String establishment;

    @Column(name = "pole_activity_group_code")
    private String poleActivityGroupCode;

    @Column(name = "group_sub_pole_activity_code")
    private String groupSubPoleActivityCode;

    @Column(name = "group_sub_pole_activity_title")
    private String groupSubPoleActivityTitle;

    @Column(name = "budgetary_affectation_department_code")
    private String budgetaryAffectationDepartmentCode;

    @Column(name = "budgetary_affectation_department_label")
    private String budgetaryAffectationDepartmentLabel;

    @Column(name = "budget_functional_id_sakkarah")
    private String budgetFunctionalIdSakkarah;

    @Column(name = "administrative_department_local_code")
    private String administrativeDepartmentLocalCode;

    @Column(name = "administrative_department_local_label")
    private String administrativeDepartmentLocalLabel;

    @Column(name = "admin_functional_id_sakkarah")
    private String adminFunctionalIdSakkarah;

    @Column(name = "detachment_group_company")
    private String detachmentGroupCompany;

    @Column(name = "detachment_legal_group_company")
    private String detachmentLegalGroupCompany;

    @ManyToOne
    @JsonIgnoreProperties("assignments")
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

    public WaAssignment localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaAssignment effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getSequence() {
        return sequence;
    }

    public WaAssignment sequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getLocalCompanyCode() {
        return localCompanyCode;
    }

    public WaAssignment localCompanyCode(String localCompanyCode) {
        this.localCompanyCode = localCompanyCode;
        return this;
    }

    public void setLocalCompanyCode(String localCompanyCode) {
        this.localCompanyCode = localCompanyCode;
    }

    public String getLegalGroupCompanyCode() {
        return legalGroupCompanyCode;
    }

    public WaAssignment legalGroupCompanyCode(String legalGroupCompanyCode) {
        this.legalGroupCompanyCode = legalGroupCompanyCode;
        return this;
    }

    public void setLegalGroupCompanyCode(String legalGroupCompanyCode) {
        this.legalGroupCompanyCode = legalGroupCompanyCode;
    }

    public String getEstablishment() {
        return establishment;
    }

    public WaAssignment establishment(String establishment) {
        this.establishment = establishment;
        return this;
    }

    public void setEstablishment(String establishment) {
        this.establishment = establishment;
    }

    public String getPoleActivityGroupCode() {
        return poleActivityGroupCode;
    }

    public WaAssignment poleActivityGroupCode(String poleActivityGroupCode) {
        this.poleActivityGroupCode = poleActivityGroupCode;
        return this;
    }

    public void setPoleActivityGroupCode(String poleActivityGroupCode) {
        this.poleActivityGroupCode = poleActivityGroupCode;
    }

    public String getGroupSubPoleActivityCode() {
        return groupSubPoleActivityCode;
    }

    public WaAssignment groupSubPoleActivityCode(String groupSubPoleActivityCode) {
        this.groupSubPoleActivityCode = groupSubPoleActivityCode;
        return this;
    }

    public void setGroupSubPoleActivityCode(String groupSubPoleActivityCode) {
        this.groupSubPoleActivityCode = groupSubPoleActivityCode;
    }

    public String getGroupSubPoleActivityTitle() {
        return groupSubPoleActivityTitle;
    }

    public WaAssignment groupSubPoleActivityTitle(String groupSubPoleActivityTitle) {
        this.groupSubPoleActivityTitle = groupSubPoleActivityTitle;
        return this;
    }

    public void setGroupSubPoleActivityTitle(String groupSubPoleActivityTitle) {
        this.groupSubPoleActivityTitle = groupSubPoleActivityTitle;
    }

    public String getBudgetaryAffectationDepartmentCode() {
        return budgetaryAffectationDepartmentCode;
    }

    public WaAssignment budgetaryAffectationDepartmentCode(String budgetaryAffectationDepartmentCode) {
        this.budgetaryAffectationDepartmentCode = budgetaryAffectationDepartmentCode;
        return this;
    }

    public void setBudgetaryAffectationDepartmentCode(String budgetaryAffectationDepartmentCode) {
        this.budgetaryAffectationDepartmentCode = budgetaryAffectationDepartmentCode;
    }

    public String getBudgetaryAffectationDepartmentLabel() {
        return budgetaryAffectationDepartmentLabel;
    }

    public WaAssignment budgetaryAffectationDepartmentLabel(String budgetaryAffectationDepartmentLabel) {
        this.budgetaryAffectationDepartmentLabel = budgetaryAffectationDepartmentLabel;
        return this;
    }

    public void setBudgetaryAffectationDepartmentLabel(String budgetaryAffectationDepartmentLabel) {
        this.budgetaryAffectationDepartmentLabel = budgetaryAffectationDepartmentLabel;
    }

    public String getBudgetFunctionalIdSakkarah() {
        return budgetFunctionalIdSakkarah;
    }

    public WaAssignment budgetFunctionalIdSakkarah(String budgetFunctionalIdSakkarah) {
        this.budgetFunctionalIdSakkarah = budgetFunctionalIdSakkarah;
        return this;
    }

    public void setBudgetFunctionalIdSakkarah(String budgetFunctionalIdSakkarah) {
        this.budgetFunctionalIdSakkarah = budgetFunctionalIdSakkarah;
    }

    public String getAdministrativeDepartmentLocalCode() {
        return administrativeDepartmentLocalCode;
    }

    public WaAssignment administrativeDepartmentLocalCode(String administrativeDepartmentLocalCode) {
        this.administrativeDepartmentLocalCode = administrativeDepartmentLocalCode;
        return this;
    }

    public void setAdministrativeDepartmentLocalCode(String administrativeDepartmentLocalCode) {
        this.administrativeDepartmentLocalCode = administrativeDepartmentLocalCode;
    }

    public String getAdministrativeDepartmentLocalLabel() {
        return administrativeDepartmentLocalLabel;
    }

    public WaAssignment administrativeDepartmentLocalLabel(String administrativeDepartmentLocalLabel) {
        this.administrativeDepartmentLocalLabel = administrativeDepartmentLocalLabel;
        return this;
    }

    public void setAdministrativeDepartmentLocalLabel(String administrativeDepartmentLocalLabel) {
        this.administrativeDepartmentLocalLabel = administrativeDepartmentLocalLabel;
    }

    public String getAdminFunctionalIdSakkarah() {
        return adminFunctionalIdSakkarah;
    }

    public WaAssignment adminFunctionalIdSakkarah(String adminFunctionalIdSakkarah) {
        this.adminFunctionalIdSakkarah = adminFunctionalIdSakkarah;
        return this;
    }

    public void setAdminFunctionalIdSakkarah(String adminFunctionalIdSakkarah) {
        this.adminFunctionalIdSakkarah = adminFunctionalIdSakkarah;
    }

    public String getDetachmentGroupCompany() {
        return detachmentGroupCompany;
    }

    public WaAssignment detachmentGroupCompany(String detachmentGroupCompany) {
        this.detachmentGroupCompany = detachmentGroupCompany;
        return this;
    }

    public void setDetachmentGroupCompany(String detachmentGroupCompany) {
        this.detachmentGroupCompany = detachmentGroupCompany;
    }

    public String getDetachmentLegalGroupCompany() {
        return detachmentLegalGroupCompany;
    }

    public WaAssignment detachmentLegalGroupCompany(String detachmentLegalGroupCompany) {
        this.detachmentLegalGroupCompany = detachmentLegalGroupCompany;
        return this;
    }

    public void setDetachmentLegalGroupCompany(String detachmentLegalGroupCompany) {
        this.detachmentLegalGroupCompany = detachmentLegalGroupCompany;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaAssignment waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaAssignment)) {
            return false;
        }
        return id != null && id.equals(((WaAssignment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaAssignment{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", localCompanyCode='" + getLocalCompanyCode() + "'" +
            ", legalGroupCompanyCode='" + getLegalGroupCompanyCode() + "'" +
            ", establishment='" + getEstablishment() + "'" +
            ", poleActivityGroupCode='" + getPoleActivityGroupCode() + "'" +
            ", groupSubPoleActivityCode='" + getGroupSubPoleActivityCode() + "'" +
            ", groupSubPoleActivityTitle='" + getGroupSubPoleActivityTitle() + "'" +
            ", budgetaryAffectationDepartmentCode='" + getBudgetaryAffectationDepartmentCode() + "'" +
            ", budgetaryAffectationDepartmentLabel='" + getBudgetaryAffectationDepartmentLabel() + "'" +
            ", budgetFunctionalIdSakkarah='" + getBudgetFunctionalIdSakkarah() + "'" +
            ", administrativeDepartmentLocalCode='" + getAdministrativeDepartmentLocalCode() + "'" +
            ", administrativeDepartmentLocalLabel='" + getAdministrativeDepartmentLocalLabel() + "'" +
            ", adminFunctionalIdSakkarah='" + getAdminFunctionalIdSakkarah() + "'" +
            ", detachmentGroupCompany='" + getDetachmentGroupCompany() + "'" +
            ", detachmentLegalGroupCompany='" + getDetachmentLegalGroupCompany() + "'" +
            "}";
    }
}
