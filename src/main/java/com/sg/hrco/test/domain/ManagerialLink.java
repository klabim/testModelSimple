package com.sg.hrco.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A ManagerialLink.
 */
@Entity
@Table(name = "managerial_link")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ManagerialLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "managerial_link_type")
    private String managerialLinkType;

    @NotNull
    @Column(name = "local_id_number", nullable = false)
    private String localIdNumber;

    @NotNull
    @Column(name = "local_id_direct_manager", nullable = false)
    private String localIDDirectManager;

    @Column(name = "ggi_direct_line_manager")
    private String ggiDirectLineManager;

    @Column(name = "manager_last_name")
    private String managerLastName;

    @Column(name = "manager_first_name")
    private String managerFirstName;

    @ManyToOne
    @JsonIgnoreProperties("managerialLinks")
    private WaEmployee waEmployee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getManagerialLinkType() {
        return managerialLinkType;
    }

    public ManagerialLink managerialLinkType(String managerialLinkType) {
        this.managerialLinkType = managerialLinkType;
        return this;
    }

    public void setManagerialLinkType(String managerialLinkType) {
        this.managerialLinkType = managerialLinkType;
    }

    public String getLocalIdNumber() {
        return localIdNumber;
    }

    public ManagerialLink localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getLocalIDDirectManager() {
        return localIDDirectManager;
    }

    public ManagerialLink localIDDirectManager(String localIDDirectManager) {
        this.localIDDirectManager = localIDDirectManager;
        return this;
    }

    public void setLocalIDDirectManager(String localIDDirectManager) {
        this.localIDDirectManager = localIDDirectManager;
    }

    public String getGgiDirectLineManager() {
        return ggiDirectLineManager;
    }

    public ManagerialLink ggiDirectLineManager(String ggiDirectLineManager) {
        this.ggiDirectLineManager = ggiDirectLineManager;
        return this;
    }

    public void setGgiDirectLineManager(String ggiDirectLineManager) {
        this.ggiDirectLineManager = ggiDirectLineManager;
    }

    public String getManagerLastName() {
        return managerLastName;
    }

    public ManagerialLink managerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
        return this;
    }

    public void setManagerLastName(String managerLastName) {
        this.managerLastName = managerLastName;
    }

    public String getManagerFirstName() {
        return managerFirstName;
    }

    public ManagerialLink managerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
        return this;
    }

    public void setManagerFirstName(String managerFirstName) {
        this.managerFirstName = managerFirstName;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public ManagerialLink waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof ManagerialLink)) {
            return false;
        }
        return id != null && id.equals(((ManagerialLink) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ManagerialLink{" +
            "id=" + getId() +
            ", managerialLinkType='" + getManagerialLinkType() + "'" +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", localIDDirectManager='" + getLocalIDDirectManager() + "'" +
            ", ggiDirectLineManager='" + getGgiDirectLineManager() + "'" +
            ", managerLastName='" + getManagerLastName() + "'" +
            ", managerFirstName='" + getManagerFirstName() + "'" +
            "}";
    }
}
