package com.sg.hrco.test.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

/**
 * A WaEmployee.
 */
@Entity
@Table(name = "wa_employee")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WaEmployee implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ggi")
    private String ggi;

    @Column(name = "home_host_indicator")
    private String homeHostIndicator;

    @Column(name = "legal_group_company_code")
    private String legalGroupCompanyCode;

    @Column(name = "prefix")
    private String prefix;

    @Column(name = "latin_birth_name")
    private String latinBirthName;

    @Column(name = "local_birth_name")
    private String localBirthName;

    @Column(name = "latin_common_name")
    private String latinCommonName;

    @Column(name = "local_common_name")
    private String localCommonName;

    @Column(name = "latin_name_complement")
    private String latinNameComplement;

    @Column(name = "local_name_complement")
    private String localNameComplement;

    @Column(name = "latin_first_name")
    private String latinFirstName;

    @Column(name = "local_first_name")
    private String localFirstName;

    @Column(name = "employee_birth_date")
    private Instant employeeBirthDate;

    @Column(name = "country_of_birth")
    private String countryOfBirth;

    @Column(name = "birth_department")
    private String birthDepartment;

    @NotNull
    @Column(name = "local_id_number", nullable = false)
    private String localIdNumber;

    @Column(name = "sensitive_employee_flag")
    private String sensitiveEmployeeFlag;

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaEmployee> employees = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaGender> genders = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaNationality> nationalities = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaMaritalStatus> maritalStatuses = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaPersonalAddress> personalAddresses = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaAssignment> assignments = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaHostCompany> hostCompanies = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaHomeCompany> homeCompanies = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaJob> jobs = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaClassification> classifications = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaManagementHris> managementHrises = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ManagerialLink> managerialLinks = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaSeniority> seniorities = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<WaLanguage> languages = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FixedCompensation> fixedCompensations = new HashSet<>();

    @OneToMany(mappedBy = "waEmployee")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<VariableCompensation> variableCompensations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("employees")
    private WaEmployee waEmployee;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGgi() {
        return ggi;
    }

    public WaEmployee ggi(String ggi) {
        this.ggi = ggi;
        return this;
    }

    public void setGgi(String ggi) {
        this.ggi = ggi;
    }

    public String getHomeHostIndicator() {
        return homeHostIndicator;
    }

    public WaEmployee homeHostIndicator(String homeHostIndicator) {
        this.homeHostIndicator = homeHostIndicator;
        return this;
    }

    public void setHomeHostIndicator(String homeHostIndicator) {
        this.homeHostIndicator = homeHostIndicator;
    }

    public String getLegalGroupCompanyCode() {
        return legalGroupCompanyCode;
    }

    public WaEmployee legalGroupCompanyCode(String legalGroupCompanyCode) {
        this.legalGroupCompanyCode = legalGroupCompanyCode;
        return this;
    }

    public void setLegalGroupCompanyCode(String legalGroupCompanyCode) {
        this.legalGroupCompanyCode = legalGroupCompanyCode;
    }

    public String getPrefix() {
        return prefix;
    }

    public WaEmployee prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLatinBirthName() {
        return latinBirthName;
    }

    public WaEmployee latinBirthName(String latinBirthName) {
        this.latinBirthName = latinBirthName;
        return this;
    }

    public void setLatinBirthName(String latinBirthName) {
        this.latinBirthName = latinBirthName;
    }

    public String getLocalBirthName() {
        return localBirthName;
    }

    public WaEmployee localBirthName(String localBirthName) {
        this.localBirthName = localBirthName;
        return this;
    }

    public void setLocalBirthName(String localBirthName) {
        this.localBirthName = localBirthName;
    }

    public String getLatinCommonName() {
        return latinCommonName;
    }

    public WaEmployee latinCommonName(String latinCommonName) {
        this.latinCommonName = latinCommonName;
        return this;
    }

    public void setLatinCommonName(String latinCommonName) {
        this.latinCommonName = latinCommonName;
    }

    public String getLocalCommonName() {
        return localCommonName;
    }

    public WaEmployee localCommonName(String localCommonName) {
        this.localCommonName = localCommonName;
        return this;
    }

    public void setLocalCommonName(String localCommonName) {
        this.localCommonName = localCommonName;
    }

    public String getLatinNameComplement() {
        return latinNameComplement;
    }

    public WaEmployee latinNameComplement(String latinNameComplement) {
        this.latinNameComplement = latinNameComplement;
        return this;
    }

    public void setLatinNameComplement(String latinNameComplement) {
        this.latinNameComplement = latinNameComplement;
    }

    public String getLocalNameComplement() {
        return localNameComplement;
    }

    public WaEmployee localNameComplement(String localNameComplement) {
        this.localNameComplement = localNameComplement;
        return this;
    }

    public void setLocalNameComplement(String localNameComplement) {
        this.localNameComplement = localNameComplement;
    }

    public String getLatinFirstName() {
        return latinFirstName;
    }

    public WaEmployee latinFirstName(String latinFirstName) {
        this.latinFirstName = latinFirstName;
        return this;
    }

    public void setLatinFirstName(String latinFirstName) {
        this.latinFirstName = latinFirstName;
    }

    public String getLocalFirstName() {
        return localFirstName;
    }

    public WaEmployee localFirstName(String localFirstName) {
        this.localFirstName = localFirstName;
        return this;
    }

    public void setLocalFirstName(String localFirstName) {
        this.localFirstName = localFirstName;
    }

    public Instant getEmployeeBirthDate() {
        return employeeBirthDate;
    }

    public WaEmployee employeeBirthDate(Instant employeeBirthDate) {
        this.employeeBirthDate = employeeBirthDate;
        return this;
    }

    public void setEmployeeBirthDate(Instant employeeBirthDate) {
        this.employeeBirthDate = employeeBirthDate;
    }

    public String getCountryOfBirth() {
        return countryOfBirth;
    }

    public WaEmployee countryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
        return this;
    }

    public void setCountryOfBirth(String countryOfBirth) {
        this.countryOfBirth = countryOfBirth;
    }

    public String getBirthDepartment() {
        return birthDepartment;
    }

    public WaEmployee birthDepartment(String birthDepartment) {
        this.birthDepartment = birthDepartment;
        return this;
    }

    public void setBirthDepartment(String birthDepartment) {
        this.birthDepartment = birthDepartment;
    }

    public String getLocalIdNumber() {
        return localIdNumber;
    }

    public WaEmployee localIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
        return this;
    }

    public void setLocalIdNumber(String localIdNumber) {
        this.localIdNumber = localIdNumber;
    }

    public String getSensitiveEmployeeFlag() {
        return sensitiveEmployeeFlag;
    }

    public WaEmployee sensitiveEmployeeFlag(String sensitiveEmployeeFlag) {
        this.sensitiveEmployeeFlag = sensitiveEmployeeFlag;
        return this;
    }

    public void setSensitiveEmployeeFlag(String sensitiveEmployeeFlag) {
        this.sensitiveEmployeeFlag = sensitiveEmployeeFlag;
    }

    public Set<WaEmployee> getEmployees() {
        return employees;
    }

    public WaEmployee employees(Set<WaEmployee> waEmployees) {
        this.employees = waEmployees;
        return this;
    }

    public WaEmployee addEmployee(WaEmployee waEmployee) {
        this.employees.add(waEmployee);
        waEmployee.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeEmployee(WaEmployee waEmployee) {
        this.employees.remove(waEmployee);
        waEmployee.setWaEmployee(null);
        return this;
    }

    public void setEmployees(Set<WaEmployee> waEmployees) {
        this.employees = waEmployees;
    }

    public Set<WaGender> getGenders() {
        return genders;
    }

    public WaEmployee genders(Set<WaGender> waGenders) {
        this.genders = waGenders;
        return this;
    }

    public WaEmployee addGender(WaGender waGender) {
        this.genders.add(waGender);
        waGender.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeGender(WaGender waGender) {
        this.genders.remove(waGender);
        waGender.setWaEmployee(null);
        return this;
    }

    public void setGenders(Set<WaGender> waGenders) {
        this.genders = waGenders;
    }

    public Set<WaNationality> getNationalities() {
        return nationalities;
    }

    public WaEmployee nationalities(Set<WaNationality> waNationalities) {
        this.nationalities = waNationalities;
        return this;
    }

    public WaEmployee addNationality(WaNationality waNationality) {
        this.nationalities.add(waNationality);
        waNationality.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeNationality(WaNationality waNationality) {
        this.nationalities.remove(waNationality);
        waNationality.setWaEmployee(null);
        return this;
    }

    public void setNationalities(Set<WaNationality> waNationalities) {
        this.nationalities = waNationalities;
    }

    public Set<WaMaritalStatus> getMaritalStatuses() {
        return maritalStatuses;
    }

    public WaEmployee maritalStatuses(Set<WaMaritalStatus> waMaritalStatuses) {
        this.maritalStatuses = waMaritalStatuses;
        return this;
    }

    public WaEmployee addMaritalStatus(WaMaritalStatus waMaritalStatus) {
        this.maritalStatuses.add(waMaritalStatus);
        waMaritalStatus.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeMaritalStatus(WaMaritalStatus waMaritalStatus) {
        this.maritalStatuses.remove(waMaritalStatus);
        waMaritalStatus.setWaEmployee(null);
        return this;
    }

    public void setMaritalStatuses(Set<WaMaritalStatus> waMaritalStatuses) {
        this.maritalStatuses = waMaritalStatuses;
    }

    public Set<WaPersonalAddress> getPersonalAddresses() {
        return personalAddresses;
    }

    public WaEmployee personalAddresses(Set<WaPersonalAddress> waPersonalAddresses) {
        this.personalAddresses = waPersonalAddresses;
        return this;
    }

    public WaEmployee addPersonalAddress(WaPersonalAddress waPersonalAddress) {
        this.personalAddresses.add(waPersonalAddress);
        waPersonalAddress.setWaEmployee(this);
        return this;
    }

    public WaEmployee removePersonalAddress(WaPersonalAddress waPersonalAddress) {
        this.personalAddresses.remove(waPersonalAddress);
        waPersonalAddress.setWaEmployee(null);
        return this;
    }

    public void setPersonalAddresses(Set<WaPersonalAddress> waPersonalAddresses) {
        this.personalAddresses = waPersonalAddresses;
    }

    public Set<WaAssignment> getAssignments() {
        return assignments;
    }

    public WaEmployee assignments(Set<WaAssignment> waAssignments) {
        this.assignments = waAssignments;
        return this;
    }

    public WaEmployee addAssignment(WaAssignment waAssignment) {
        this.assignments.add(waAssignment);
        waAssignment.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeAssignment(WaAssignment waAssignment) {
        this.assignments.remove(waAssignment);
        waAssignment.setWaEmployee(null);
        return this;
    }

    public void setAssignments(Set<WaAssignment> waAssignments) {
        this.assignments = waAssignments;
    }

    public Set<WaHostCompany> getHostCompanies() {
        return hostCompanies;
    }

    public WaEmployee hostCompanies(Set<WaHostCompany> waHostCompanies) {
        this.hostCompanies = waHostCompanies;
        return this;
    }

    public WaEmployee addHostCompany(WaHostCompany waHostCompany) {
        this.hostCompanies.add(waHostCompany);
        waHostCompany.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeHostCompany(WaHostCompany waHostCompany) {
        this.hostCompanies.remove(waHostCompany);
        waHostCompany.setWaEmployee(null);
        return this;
    }

    public void setHostCompanies(Set<WaHostCompany> waHostCompanies) {
        this.hostCompanies = waHostCompanies;
    }

    public Set<WaHomeCompany> getHomeCompanies() {
        return homeCompanies;
    }

    public WaEmployee homeCompanies(Set<WaHomeCompany> waHomeCompanies) {
        this.homeCompanies = waHomeCompanies;
        return this;
    }

    public WaEmployee addHomeCompany(WaHomeCompany waHomeCompany) {
        this.homeCompanies.add(waHomeCompany);
        waHomeCompany.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeHomeCompany(WaHomeCompany waHomeCompany) {
        this.homeCompanies.remove(waHomeCompany);
        waHomeCompany.setWaEmployee(null);
        return this;
    }

    public void setHomeCompanies(Set<WaHomeCompany> waHomeCompanies) {
        this.homeCompanies = waHomeCompanies;
    }

    public Set<WaJob> getJobs() {
        return jobs;
    }

    public WaEmployee jobs(Set<WaJob> waJobs) {
        this.jobs = waJobs;
        return this;
    }

    public WaEmployee addJob(WaJob waJob) {
        this.jobs.add(waJob);
        waJob.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeJob(WaJob waJob) {
        this.jobs.remove(waJob);
        waJob.setWaEmployee(null);
        return this;
    }

    public void setJobs(Set<WaJob> waJobs) {
        this.jobs = waJobs;
    }

    public Set<WaClassification> getClassifications() {
        return classifications;
    }

    public WaEmployee classifications(Set<WaClassification> waClassifications) {
        this.classifications = waClassifications;
        return this;
    }

    public WaEmployee addClassification(WaClassification waClassification) {
        this.classifications.add(waClassification);
        waClassification.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeClassification(WaClassification waClassification) {
        this.classifications.remove(waClassification);
        waClassification.setWaEmployee(null);
        return this;
    }

    public void setClassifications(Set<WaClassification> waClassifications) {
        this.classifications = waClassifications;
    }

    public Set<WaManagementHris> getManagementHrises() {
        return managementHrises;
    }

    public WaEmployee managementHrises(Set<WaManagementHris> waManagementHrises) {
        this.managementHrises = waManagementHrises;
        return this;
    }

    public WaEmployee addManagementHris(WaManagementHris waManagementHris) {
        this.managementHrises.add(waManagementHris);
        waManagementHris.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeManagementHris(WaManagementHris waManagementHris) {
        this.managementHrises.remove(waManagementHris);
        waManagementHris.setWaEmployee(null);
        return this;
    }

    public void setManagementHrises(Set<WaManagementHris> waManagementHrises) {
        this.managementHrises = waManagementHrises;
    }

    public Set<ManagerialLink> getManagerialLinks() {
        return managerialLinks;
    }

    public WaEmployee managerialLinks(Set<ManagerialLink> managerialLinks) {
        this.managerialLinks = managerialLinks;
        return this;
    }

    public WaEmployee addManagerialLink(ManagerialLink managerialLink) {
        this.managerialLinks.add(managerialLink);
        managerialLink.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeManagerialLink(ManagerialLink managerialLink) {
        this.managerialLinks.remove(managerialLink);
        managerialLink.setWaEmployee(null);
        return this;
    }

    public void setManagerialLinks(Set<ManagerialLink> managerialLinks) {
        this.managerialLinks = managerialLinks;
    }

    public Set<WaSeniority> getSeniorities() {
        return seniorities;
    }

    public WaEmployee seniorities(Set<WaSeniority> waSeniorities) {
        this.seniorities = waSeniorities;
        return this;
    }

    public WaEmployee addSeniority(WaSeniority waSeniority) {
        this.seniorities.add(waSeniority);
        waSeniority.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeSeniority(WaSeniority waSeniority) {
        this.seniorities.remove(waSeniority);
        waSeniority.setWaEmployee(null);
        return this;
    }

    public void setSeniorities(Set<WaSeniority> waSeniorities) {
        this.seniorities = waSeniorities;
    }

    public Set<WaLanguage> getLanguages() {
        return languages;
    }

    public WaEmployee languages(Set<WaLanguage> waLanguages) {
        this.languages = waLanguages;
        return this;
    }

    public WaEmployee addLanguage(WaLanguage waLanguage) {
        this.languages.add(waLanguage);
        waLanguage.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeLanguage(WaLanguage waLanguage) {
        this.languages.remove(waLanguage);
        waLanguage.setWaEmployee(null);
        return this;
    }

    public void setLanguages(Set<WaLanguage> waLanguages) {
        this.languages = waLanguages;
    }

    public Set<FixedCompensation> getFixedCompensations() {
        return fixedCompensations;
    }

    public WaEmployee fixedCompensations(Set<FixedCompensation> fixedCompensations) {
        this.fixedCompensations = fixedCompensations;
        return this;
    }

    public WaEmployee addFixedCompensation(FixedCompensation fixedCompensation) {
        this.fixedCompensations.add(fixedCompensation);
        fixedCompensation.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeFixedCompensation(FixedCompensation fixedCompensation) {
        this.fixedCompensations.remove(fixedCompensation);
        fixedCompensation.setWaEmployee(null);
        return this;
    }

    public void setFixedCompensations(Set<FixedCompensation> fixedCompensations) {
        this.fixedCompensations = fixedCompensations;
    }

    public Set<VariableCompensation> getVariableCompensations() {
        return variableCompensations;
    }

    public WaEmployee variableCompensations(Set<VariableCompensation> variableCompensations) {
        this.variableCompensations = variableCompensations;
        return this;
    }

    public WaEmployee addVariableCompensation(VariableCompensation variableCompensation) {
        this.variableCompensations.add(variableCompensation);
        variableCompensation.setWaEmployee(this);
        return this;
    }

    public WaEmployee removeVariableCompensation(VariableCompensation variableCompensation) {
        this.variableCompensations.remove(variableCompensation);
        variableCompensation.setWaEmployee(null);
        return this;
    }

    public void setVariableCompensations(Set<VariableCompensation> variableCompensations) {
        this.variableCompensations = variableCompensations;
    }

    public WaEmployee getWaEmployee() {
        return waEmployee;
    }

    public WaEmployee waEmployee(WaEmployee waEmployee) {
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
        if (!(o instanceof WaEmployee)) {
            return false;
        }
        return id != null && id.equals(((WaEmployee) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "WaEmployee{" +
            "id=" + getId() +
            ", ggi='" + getGgi() + "'" +
            ", homeHostIndicator='" + getHomeHostIndicator() + "'" +
            ", legalGroupCompanyCode='" + getLegalGroupCompanyCode() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", latinBirthName='" + getLatinBirthName() + "'" +
            ", localBirthName='" + getLocalBirthName() + "'" +
            ", latinCommonName='" + getLatinCommonName() + "'" +
            ", localCommonName='" + getLocalCommonName() + "'" +
            ", latinNameComplement='" + getLatinNameComplement() + "'" +
            ", localNameComplement='" + getLocalNameComplement() + "'" +
            ", latinFirstName='" + getLatinFirstName() + "'" +
            ", localFirstName='" + getLocalFirstName() + "'" +
            ", employeeBirthDate='" + getEmployeeBirthDate() + "'" +
            ", countryOfBirth='" + getCountryOfBirth() + "'" +
            ", birthDepartment='" + getBirthDepartment() + "'" +
            ", localIdNumber='" + getLocalIdNumber() + "'" +
            ", sensitiveEmployeeFlag='" + getSensitiveEmployeeFlag() + "'" +
            "}";
    }
}
