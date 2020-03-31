package com.sg.hrco.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WaLanguage.
 */
@Entity
@Table(name = "wa_language")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaLanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "language_code", nullable = false)
    private String languageCode;

    @Column(name = "language_label")
    private String languageLabel;

    @Column(name = "language_speak_proficiency_code")
    private String languageSpeakProficiencyCode;

    @Column(name = "language_speak_proficiency_label")
    private String languageSpeakProficiencyLabel;

    @Column(name = "language_read_proficiency_code")
    private String languageReadProficiencyCode;

    @Column(name = "language_read_proficiency_label")
    private String languageReadProficiencyLabel;

    @Column(name = "language_write_proficiency_code")
    private String languageWriteProficiencyCode;

    @Column(name = "language_write_proficiency_label")
    private String languageWriteProficiencyLabel;

    @Column(name = "native_language")
    private String nativeLanguage;

    @Column(name = "language_level_local_code")
    private String languageLevelLocalCode;

    @Column(name = "language_level_local_label")
    private String languageLevelLocalLabel;

    @Column(name = "language_level_group_code")
    private String languageLevelGroupCode;

    @Column(name = "language_level_group_label")
    private String languageLevelGroupLabel;

    @ManyToOne
    @JsonIgnoreProperties("languages")
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

    public WaLanguage localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public WaLanguage languageCode(String languageCode) {
        this.languageCode = languageCode;
        return this;
    }

    public void setLanguageCode(String languageCode) {
        this.languageCode = languageCode;
    }

    public String getLanguageLabel() {
        return languageLabel;
    }

    public WaLanguage languageLabel(String languageLabel) {
        this.languageLabel = languageLabel;
        return this;
    }

    public void setLanguageLabel(String languageLabel) {
        this.languageLabel = languageLabel;
    }

    public String getLanguageSpeakProficiencyCode() {
        return languageSpeakProficiencyCode;
    }

    public WaLanguage languageSpeakProficiencyCode(String languageSpeakProficiencyCode) {
        this.languageSpeakProficiencyCode = languageSpeakProficiencyCode;
        return this;
    }

    public void setLanguageSpeakProficiencyCode(String languageSpeakProficiencyCode) {
        this.languageSpeakProficiencyCode = languageSpeakProficiencyCode;
    }

    public String getLanguageSpeakProficiencyLabel() {
        return languageSpeakProficiencyLabel;
    }

    public WaLanguage languageSpeakProficiencyLabel(String languageSpeakProficiencyLabel) {
        this.languageSpeakProficiencyLabel = languageSpeakProficiencyLabel;
        return this;
    }

    public void setLanguageSpeakProficiencyLabel(String languageSpeakProficiencyLabel) {
        this.languageSpeakProficiencyLabel = languageSpeakProficiencyLabel;
    }

    public String getLanguageReadProficiencyCode() {
        return languageReadProficiencyCode;
    }

    public WaLanguage languageReadProficiencyCode(String languageReadProficiencyCode) {
        this.languageReadProficiencyCode = languageReadProficiencyCode;
        return this;
    }

    public void setLanguageReadProficiencyCode(String languageReadProficiencyCode) {
        this.languageReadProficiencyCode = languageReadProficiencyCode;
    }

    public String getLanguageReadProficiencyLabel() {
        return languageReadProficiencyLabel;
    }

    public WaLanguage languageReadProficiencyLabel(String languageReadProficiencyLabel) {
        this.languageReadProficiencyLabel = languageReadProficiencyLabel;
        return this;
    }

    public void setLanguageReadProficiencyLabel(String languageReadProficiencyLabel) {
        this.languageReadProficiencyLabel = languageReadProficiencyLabel;
    }

    public String getLanguageWriteProficiencyCode() {
        return languageWriteProficiencyCode;
    }

    public WaLanguage languageWriteProficiencyCode(String languageWriteProficiencyCode) {
        this.languageWriteProficiencyCode = languageWriteProficiencyCode;
        return this;
    }

    public void setLanguageWriteProficiencyCode(String languageWriteProficiencyCode) {
        this.languageWriteProficiencyCode = languageWriteProficiencyCode;
    }

    public String getLanguageWriteProficiencyLabel() {
        return languageWriteProficiencyLabel;
    }

    public WaLanguage languageWriteProficiencyLabel(String languageWriteProficiencyLabel) {
        this.languageWriteProficiencyLabel = languageWriteProficiencyLabel;
        return this;
    }

    public void setLanguageWriteProficiencyLabel(String languageWriteProficiencyLabel) {
        this.languageWriteProficiencyLabel = languageWriteProficiencyLabel;
    }

    public String getNativeLanguage() {
        return nativeLanguage;
    }

    public WaLanguage nativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
        return this;
    }

    public void setNativeLanguage(String nativeLanguage) {
        this.nativeLanguage = nativeLanguage;
    }

    public String getLanguageLevelLocalCode() {
        return languageLevelLocalCode;
    }

    public WaLanguage languageLevelLocalCode(String languageLevelLocalCode) {
        this.languageLevelLocalCode = languageLevelLocalCode;
        return this;
    }

    public void setLanguageLevelLocalCode(String languageLevelLocalCode) {
        this.languageLevelLocalCode = languageLevelLocalCode;
    }

    public String getLanguageLevelLocalLabel() {
        return languageLevelLocalLabel;
    }

    public WaLanguage languageLevelLocalLabel(String languageLevelLocalLabel) {
        this.languageLevelLocalLabel = languageLevelLocalLabel;
        return this;
    }

    public void setLanguageLevelLocalLabel(String languageLevelLocalLabel) {
        this.languageLevelLocalLabel = languageLevelLocalLabel;
    }

    public String getLanguageLevelGroupCode() {
        return languageLevelGroupCode;
    }

    public WaLanguage languageLevelGroupCode(String languageLevelGroupCode) {
        this.languageLevelGroupCode = languageLevelGroupCode;
        return this;
    }

    public void setLanguageLevelGroupCode(String languageLevelGroupCode) {
        this.languageLevelGroupCode = languageLevelGroupCode;
    }

    public String getLanguageLevelGroupLabel() {
        return languageLevelGroupLabel;
    }

    public WaLanguage languageLevelGroupLabel(String languageLevelGroupLabel) {
        this.languageLevelGroupLabel = languageLevelGroupLabel;
        return this;
    }

    public void setLanguageLevelGroupLabel(String languageLevelGroupLabel) {
        this.languageLevelGroupLabel = languageLevelGroupLabel;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaLanguage waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaLanguage)) {
            return false;
        }
        return id != null && id.equals(((WaLanguage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaLanguage{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", languageCode='" + getLanguageCode() + "'" +
            ", languageLabel='" + getLanguageLabel() + "'" +
            ", languageSpeakProficiencyCode='" + getLanguageSpeakProficiencyCode() + "'" +
            ", languageSpeakProficiencyLabel='" + getLanguageSpeakProficiencyLabel() + "'" +
            ", languageReadProficiencyCode='" + getLanguageReadProficiencyCode() + "'" +
            ", languageReadProficiencyLabel='" + getLanguageReadProficiencyLabel() + "'" +
            ", languageWriteProficiencyCode='" + getLanguageWriteProficiencyCode() + "'" +
            ", languageWriteProficiencyLabel='" + getLanguageWriteProficiencyLabel() + "'" +
            ", nativeLanguage='" + getNativeLanguage() + "'" +
            ", languageLevelLocalCode='" + getLanguageLevelLocalCode() + "'" +
            ", languageLevelLocalLabel='" + getLanguageLevelLocalLabel() + "'" +
            ", languageLevelGroupCode='" + getLanguageLevelGroupCode() + "'" +
            ", languageLevelGroupLabel='" + getLanguageLevelGroupLabel() + "'" +
            "}";
    }
}
