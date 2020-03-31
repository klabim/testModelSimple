package com.sg.hrco.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WaManagementHris.
 */
@Entity
@Table(name = "wa_management_hris")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaManagementHris implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "management_hris_code")
    private String managementHrisCode;

    @NotNull
    @Column(name = "local_id_number", nullable = false)
    private String localIdNumber;

    @Column(name = "management_hris_label")
    private String managementHrisLabel;

    @Column(name = "entity_management_code")
    private String entityManagementCode;

    @ManyToOne
    @JsonIgnoreProperties("managementHrises")
    private WaEmployee waEmployee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManagementHrisCode() {
        return managementHrisCode;
    }

    public WaManagementHris managementHrisCode(String managementHrisCode) {
        this.managementHrisCode = managementHrisCode;
        return this;
    }

    public void setManagementHrisCode(String managementHrisCode) {
        this.managementHrisCode = managementHrisCode;
    }

    public String getLocalIdNumber() {
        return localIdNumber;
    }

    public WaManagementHris localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getManagementHrisLabel() {
        return managementHrisLabel;
    }

    public WaManagementHris managementHrisLabel(String managementHrisLabel) {
        this.managementHrisLabel = managementHrisLabel;
        return this;
    }

    public void setManagementHrisLabel(String managementHrisLabel) {
        this.managementHrisLabel = managementHrisLabel;
    }

    public String getEntityManagementCode() {
        return entityManagementCode;
    }

    public WaManagementHris entityManagementCode(String entityManagementCode) {
        this.entityManagementCode = entityManagementCode;
        return this;
    }

    public void setEntityManagementCode(String entityManagementCode) {
        this.entityManagementCode = entityManagementCode;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaManagementHris waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaManagementHris)) {
            return false;
        }
        return id != null && id.equals(((WaManagementHris) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaManagementHris{" +
            "id=" + getId() +
            ", managementHrisCode='" + getManagementHrisCode() + "'" +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", managementHrisLabel='" + getManagementHrisLabel() + "'" +
            ", entityManagementCode='" + getEntityManagementCode() + "'" +
            "}";
    }
}
