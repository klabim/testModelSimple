package com.sg.hrco.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A WaGender.
 */
@Entity
@Table(name = "wa_gender")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaGender implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "local_id_number")
    private String localIdNumber;

    @NotNull
    @Column(name = "employee_local_gender", nullable = false)
    private String employeeLocalGender;

    @Column(name = "employee_gender")
    private String employeeGender;

    @ManyToOne
    @JsonIgnoreProperties("genders")
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

    public WaGender localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getEmployeeLocalGender() {
        return employeeLocalGender;
    }

    public WaGender employeeLocalGender(String employeeLocalGender) {
        this.employeeLocalGender = employeeLocalGender;
        return this;
    }

    public void setEmployeeLocalGender(String employeeLocalGender) {
        this.employeeLocalGender = employeeLocalGender;
    }

    public String getEmployeeGender() {
        return employeeGender;
    }

    public WaGender employeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
        return this;
    }

    public void setEmployeeGender(String employeeGender) {
        this.employeeGender = employeeGender;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaGender waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaGender)) {
            return false;
        }
        return id != null && id.equals(((WaGender) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaGender{" +
            "id=" + getId() +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", employeeLocalGender='" + getEmployeeLocalGender() + "'" +
            ", employeeGender='" + getEmployeeGender() + "'" +
            "}";
    }
}
