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
 * A WaClassification.
 */
@Entity
@Table(name = "wa_classification")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaClassification implements Serializable {

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
    @Column(name = "local_classification_code", nullable = false)
    private String localClassificationCode;

    @Column(name = "local_classification_label")
    private String localClassificationLabel;

    @Column(name = "group_classification_code")
    private String groupClassificationCode;

    @Column(name = "group_classification_label")
    private String groupClassificationLabel;

    @Column(name = "professional_category_code")
    private String professionalCategoryCode;

    @Column(name = "professional_category_label")
    private String professionalCategoryLabel;

    @ManyToOne
    @JsonIgnoreProperties("classifications")
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

    public WaClassification localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public Instant getEffectiveDate() {
        return effectiveDate;
    }

    public WaClassification effectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
        return this;
    }

    public void setEffectiveDate(Instant effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getSequence() {
        return sequence;
    }

    public WaClassification sequence(String sequence) {
        this.sequence = sequence;
        return this;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getLocalClassificationCode() {
        return localClassificationCode;
    }

    public WaClassification localClassificationCode(String localClassificationCode) {
        this.localClassificationCode = localClassificationCode;
        return this;
    }

    public void setLocalClassificationCode(String localClassificationCode) {
        this.localClassificationCode = localClassificationCode;
    }

    public String getLocalClassificationLabel() {
        return localClassificationLabel;
    }

    public WaClassification localClassificationLabel(String localClassificationLabel) {
        this.localClassificationLabel = localClassificationLabel;
        return this;
    }

    public void setLocalClassificationLabel(String localClassificationLabel) {
        this.localClassificationLabel = localClassificationLabel;
    }

    public String getGroupClassificationCode() {
        return groupClassificationCode;
    }

    public WaClassification groupClassificationCode(String groupClassificationCode) {
        this.groupClassificationCode = groupClassificationCode;
        return this;
    }

    public void setGroupClassificationCode(String groupClassificationCode) {
        this.groupClassificationCode = groupClassificationCode;
    }

    public String getGroupClassificationLabel() {
        return groupClassificationLabel;
    }

    public WaClassification groupClassificationLabel(String groupClassificationLabel) {
        this.groupClassificationLabel = groupClassificationLabel;
        return this;
    }

    public void setGroupClassificationLabel(String groupClassificationLabel) {
        this.groupClassificationLabel = groupClassificationLabel;
    }

    public String getProfessionalCategoryCode() {
        return professionalCategoryCode;
    }

    public WaClassification professionalCategoryCode(String professionalCategoryCode) {
        this.professionalCategoryCode = professionalCategoryCode;
        return this;
    }

    public void setProfessionalCategoryCode(String professionalCategoryCode) {
        this.professionalCategoryCode = professionalCategoryCode;
    }

    public String getProfessionalCategoryLabel() {
        return professionalCategoryLabel;
    }

    public WaClassification professionalCategoryLabel(String professionalCategoryLabel) {
        this.professionalCategoryLabel = professionalCategoryLabel;
        return this;
    }

    public void setProfessionalCategoryLabel(String professionalCategoryLabel) {
        this.professionalCategoryLabel = professionalCategoryLabel;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaClassification waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaClassification)) {
            return false;
        }
        return id != null && id.equals(((WaClassification) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaClassification{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", effectiveDate='" + getEffectiveDate() + "'" +
            ", sequence='" + getSequence() + "'" +
            ", localClassificationCode='" + getLocalClassificationCode() + "'" +
            ", localClassificationLabel='" + getLocalClassificationLabel() + "'" +
            ", groupClassificationCode='" + getGroupClassificationCode() + "'" +
            ", groupClassificationLabel='" + getGroupClassificationLabel() + "'" +
            ", professionalCategoryCode='" + getProfessionalCategoryCode() + "'" +
            ", professionalCategoryLabel='" + getProfessionalCategoryLabel() + "'" +
            "}";
    }
}
