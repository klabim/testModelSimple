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
 * A VariableCompensation.
 */
@Entity
@Table(name = "variable_compensation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VariableCompensation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "variable_compensation_type_group_code", nullable = false)
    private String variableCompensationTypeGroupCode;

    @Column(name = "variable_compensation_type_group_label")
    private String variableCompensationTypeGroupLabel;

    @NotNull
    @Column(name = "effective_beginning_date", nullable = false)
    private Instant effectiveBeginningDate;

    @Column(name = "variable_compensation_currency")
    private String variableCompensationCurrency;

    @Column(name = "variable_compensation_local_type_code")
    private String variableCompensationLocalTypeCode;

    @Column(name = "variable_compensation_local_type_label")
    private String variableCompensationLocalTypeLabel;

    @Column(name = "variable_compensation_amount")
    private String variableCompensationAmount;

    @Column(name = "effective_end_date")
    private Instant effectiveEndDate;

    @ManyToOne
    @JsonIgnoreProperties("variableCompensations")
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

    public VariableCompensation localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getVariableCompensationTypeGroupCode() {
        return variableCompensationTypeGroupCode;
    }

    public VariableCompensation variableCompensationTypeGroupCode(String variableCompensationTypeGroupCode) {
        this.variableCompensationTypeGroupCode = variableCompensationTypeGroupCode;
        return this;
    }

    public void setVariableCompensationTypeGroupCode(String variableCompensationTypeGroupCode) {
        this.variableCompensationTypeGroupCode = variableCompensationTypeGroupCode;
    }

    public String getVariableCompensationTypeGroupLabel() {
        return variableCompensationTypeGroupLabel;
    }

    public VariableCompensation variableCompensationTypeGroupLabel(String variableCompensationTypeGroupLabel) {
        this.variableCompensationTypeGroupLabel = variableCompensationTypeGroupLabel;
        return this;
    }

    public void setVariableCompensationTypeGroupLabel(String variableCompensationTypeGroupLabel) {
        this.variableCompensationTypeGroupLabel = variableCompensationTypeGroupLabel;
    }

    public Instant getEffectiveBeginningDate() {
        return effectiveBeginningDate;
    }

    public VariableCompensation effectiveBeginningDate(Instant effectiveBeginningDate) {
        this.effectiveBeginningDate = effectiveBeginningDate;
        return this;
    }

    public void setEffectiveBeginningDate(Instant effectiveBeginningDate) {
        this.effectiveBeginningDate = effectiveBeginningDate;
    }

    public String getVariableCompensationCurrency() {
        return variableCompensationCurrency;
    }

    public VariableCompensation variableCompensationCurrency(String variableCompensationCurrency) {
        this.variableCompensationCurrency = variableCompensationCurrency;
        return this;
    }

    public void setVariableCompensationCurrency(String variableCompensationCurrency) {
        this.variableCompensationCurrency = variableCompensationCurrency;
    }

    public String getVariableCompensationLocalTypeCode() {
        return variableCompensationLocalTypeCode;
    }

    public VariableCompensation variableCompensationLocalTypeCode(String variableCompensationLocalTypeCode) {
        this.variableCompensationLocalTypeCode = variableCompensationLocalTypeCode;
        return this;
    }

    public void setVariableCompensationLocalTypeCode(String variableCompensationLocalTypeCode) {
        this.variableCompensationLocalTypeCode = variableCompensationLocalTypeCode;
    }

    public String getVariableCompensationLocalTypeLabel() {
        return variableCompensationLocalTypeLabel;
    }

    public VariableCompensation variableCompensationLocalTypeLabel(String variableCompensationLocalTypeLabel) {
        this.variableCompensationLocalTypeLabel = variableCompensationLocalTypeLabel;
        return this;
    }

    public void setVariableCompensationLocalTypeLabel(String variableCompensationLocalTypeLabel) {
        this.variableCompensationLocalTypeLabel = variableCompensationLocalTypeLabel;
    }

    public String getVariableCompensationAmount() {
        return variableCompensationAmount;
    }

    public VariableCompensation variableCompensationAmount(String variableCompensationAmount) {
        this.variableCompensationAmount = variableCompensationAmount;
        return this;
    }

    public void setVariableCompensationAmount(String variableCompensationAmount) {
        this.variableCompensationAmount = variableCompensationAmount;
    }

    public Instant getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public VariableCompensation effectiveEndDate(Instant effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
        return this;
    }

    public void setEffectiveEndDate(Instant effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public VariableCompensation waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof VariableCompensation)) {
            return false;
        }
        return id != null && id.equals(((VariableCompensation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "VariableCompensation{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", variableCompensationTypeGroupCode='" + getVariableCompensationTypeGroupCode() + "'" +
            ", variableCompensationTypeGroupLabel='" + getVariableCompensationTypeGroupLabel() + "'" +
            ", effectiveBeginningDate='" + getEffectiveBeginningDate() + "'" +
            ", variableCompensationCurrency='" + getVariableCompensationCurrency() + "'" +
            ", variableCompensationLocalTypeCode='" + getVariableCompensationLocalTypeCode() + "'" +
            ", variableCompensationLocalTypeLabel='" + getVariableCompensationLocalTypeLabel() + "'" +
            ", variableCompensationAmount='" + getVariableCompensationAmount() + "'" +
            ", effectiveEndDate='" + getEffectiveEndDate() + "'" +
            "}";
    }
}
