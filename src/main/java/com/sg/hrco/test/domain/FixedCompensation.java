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
 * A FixedCompensation.
 */
@Entity
@Table(name = "fixed_compensation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FixedCompensation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "fixed_compensation_type_group_code", nullable = false)
    private String fixedCompensationTypeGroupCode;

    @Column(name = "fixed_compensation_type_group_label")
    private String fixedCompensationTypeGroupLabel;

    @NotNull
    @Column(name = "effective_beginning_date", nullable = false)
    private Instant effectiveBeginningDate;

    @Column(name = "fixed_compensation_currency")
    private String fixedCompensationCurrency;

    @Column(name = "fixed_compensation_local_type_code")
    private String fixedCompensationLocalTypeCode;

    @Column(name = "fixed_compensation_local_type_label")
    private String fixedCompensationLocalTypeLabel;

    @Column(name = "fixed_compensation_amount")
    private String fixedCompensationAmount;

    @Column(name = "compensation_frequency")
    private String compensationFrequency;

    @Column(name = "effective_end_date")
    private Instant effectiveEndDate;

    @Column(name = "fixed_compensation_effective_sequence")
    private String fixedCompensationEffectiveSequence;

    @ManyToOne
    @JsonIgnoreProperties("fixedCompensations")
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

    public FixedCompensation localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getFixedCompensationTypeGroupCode() {
        return fixedCompensationTypeGroupCode;
    }

    public FixedCompensation fixedCompensationTypeGroupCode(String fixedCompensationTypeGroupCode) {
        this.fixedCompensationTypeGroupCode = fixedCompensationTypeGroupCode;
        return this;
    }

    public void setFixedCompensationTypeGroupCode(String fixedCompensationTypeGroupCode) {
        this.fixedCompensationTypeGroupCode = fixedCompensationTypeGroupCode;
    }

    public String getFixedCompensationTypeGroupLabel() {
        return fixedCompensationTypeGroupLabel;
    }

    public FixedCompensation fixedCompensationTypeGroupLabel(String fixedCompensationTypeGroupLabel) {
        this.fixedCompensationTypeGroupLabel = fixedCompensationTypeGroupLabel;
        return this;
    }

    public void setFixedCompensationTypeGroupLabel(String fixedCompensationTypeGroupLabel) {
        this.fixedCompensationTypeGroupLabel = fixedCompensationTypeGroupLabel;
    }

    public Instant getEffectiveBeginningDate() {
        return effectiveBeginningDate;
    }

    public FixedCompensation effectiveBeginningDate(Instant effectiveBeginningDate) {
        this.effectiveBeginningDate = effectiveBeginningDate;
        return this;
    }

    public void setEffectiveBeginningDate(Instant effectiveBeginningDate) {
        this.effectiveBeginningDate = effectiveBeginningDate;
    }

    public String getFixedCompensationCurrency() {
        return fixedCompensationCurrency;
    }

    public FixedCompensation fixedCompensationCurrency(String fixedCompensationCurrency) {
        this.fixedCompensationCurrency = fixedCompensationCurrency;
        return this;
    }

    public void setFixedCompensationCurrency(String fixedCompensationCurrency) {
        this.fixedCompensationCurrency = fixedCompensationCurrency;
    }

    public String getFixedCompensationLocalTypeCode() {
        return fixedCompensationLocalTypeCode;
    }

    public FixedCompensation fixedCompensationLocalTypeCode(String fixedCompensationLocalTypeCode) {
        this.fixedCompensationLocalTypeCode = fixedCompensationLocalTypeCode;
        return this;
    }

    public void setFixedCompensationLocalTypeCode(String fixedCompensationLocalTypeCode) {
        this.fixedCompensationLocalTypeCode = fixedCompensationLocalTypeCode;
    }

    public String getFixedCompensationLocalTypeLabel() {
        return fixedCompensationLocalTypeLabel;
    }

    public FixedCompensation fixedCompensationLocalTypeLabel(String fixedCompensationLocalTypeLabel) {
        this.fixedCompensationLocalTypeLabel = fixedCompensationLocalTypeLabel;
        return this;
    }

    public void setFixedCompensationLocalTypeLabel(String fixedCompensationLocalTypeLabel) {
        this.fixedCompensationLocalTypeLabel = fixedCompensationLocalTypeLabel;
    }

    public String getFixedCompensationAmount() {
        return fixedCompensationAmount;
    }

    public FixedCompensation fixedCompensationAmount(String fixedCompensationAmount) {
        this.fixedCompensationAmount = fixedCompensationAmount;
        return this;
    }

    public void setFixedCompensationAmount(String fixedCompensationAmount) {
        this.fixedCompensationAmount = fixedCompensationAmount;
    }

    public String getCompensationFrequency() {
        return compensationFrequency;
    }

    public FixedCompensation compensationFrequency(String compensationFrequency) {
        this.compensationFrequency = compensationFrequency;
        return this;
    }

    public void setCompensationFrequency(String compensationFrequency) {
        this.compensationFrequency = compensationFrequency;
    }

    public Instant getEffectiveEndDate() {
        return effectiveEndDate;
    }

    public FixedCompensation effectiveEndDate(Instant effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
        return this;
    }

    public void setEffectiveEndDate(Instant effectiveEndDate) {
        this.effectiveEndDate = effectiveEndDate;
    }

    public String getFixedCompensationEffectiveSequence() {
        return fixedCompensationEffectiveSequence;
    }

    public FixedCompensation fixedCompensationEffectiveSequence(String fixedCompensationEffectiveSequence) {
        this.fixedCompensationEffectiveSequence = fixedCompensationEffectiveSequence;
        return this;
    }

    public void setFixedCompensationEffectiveSequence(String fixedCompensationEffectiveSequence) {
        this.fixedCompensationEffectiveSequence = fixedCompensationEffectiveSequence;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public FixedCompensation waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof FixedCompensation)) {
            return false;
        }
        return id != null && id.equals(((FixedCompensation) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "FixedCompensation{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", fixedCompensationTypeGroupCode='" + getFixedCompensationTypeGroupCode() + "'" +
            ", fixedCompensationTypeGroupLabel='" + getFixedCompensationTypeGroupLabel() + "'" +
            ", effectiveBeginningDate='" + getEffectiveBeginningDate() + "'" +
            ", fixedCompensationCurrency='" + getFixedCompensationCurrency() + "'" +
            ", fixedCompensationLocalTypeCode='" + getFixedCompensationLocalTypeCode() + "'" +
            ", fixedCompensationLocalTypeLabel='" + getFixedCompensationLocalTypeLabel() + "'" +
            ", fixedCompensationAmount='" + getFixedCompensationAmount() + "'" +
            ", compensationFrequency='" + getCompensationFrequency() + "'" +
            ", effectiveEndDate='" + getEffectiveEndDate() + "'" +
            ", fixedCompensationEffectiveSequence='" + getFixedCompensationEffectiveSequence() + "'" +
            "}";
    }
}
