import { Moment } from 'moment';
import { IWaGender } from 'app/shared/model/wa-gender.model';
import { IWaNationality } from 'app/shared/model/wa-nationality.model';
import { IWaMaritalStatus } from 'app/shared/model/wa-marital-status.model';
import { IWaPersonalAddress } from 'app/shared/model/wa-personal-address.model';
import { IWaAssignment } from 'app/shared/model/wa-assignment.model';
import { IWaHostCompany } from 'app/shared/model/wa-host-company.model';
import { IWaHomeCompany } from 'app/shared/model/wa-home-company.model';
import { IWaJob } from 'app/shared/model/wa-job.model';
import { IWaClassification } from 'app/shared/model/wa-classification.model';
import { IWaManagementHris } from 'app/shared/model/wa-management-hris.model';
import { IManagerialLink } from 'app/shared/model/managerial-link.model';
import { IWaSeniority } from 'app/shared/model/wa-seniority.model';
import { IWaLanguage } from 'app/shared/model/wa-language.model';
import { IFixedCompensation } from 'app/shared/model/fixed-compensation.model';
import { IVariableCompensation } from 'app/shared/model/variable-compensation.model';

export interface IWaEmployee {
  id?: number;
  ggi?: string;
  homeHostIndicator?: string;
  legalGroupCompanyCode?: string;
  prefix?: string;
  latinBirthName?: string;
  localBirthName?: string;
  latinCommonName?: string;
  localCommonName?: string;
  latinNameComplement?: string;
  localNameComplement?: string;
  latinFirstName?: string;
  localFirstName?: string;
  employeeBirthDate?: Moment;
  countryOfBirth?: string;
  birthDepartment?: string;
  localIdNumber?: string;
  sensitiveEmployeeFlag?: string;
  employees?: IWaEmployee[];
  genders?: IWaGender[];
  nationalities?: IWaNationality[];
  maritalStatuses?: IWaMaritalStatus[];
  personalAddresses?: IWaPersonalAddress[];
  assignments?: IWaAssignment[];
  hostCompanies?: IWaHostCompany[];
  homeCompanies?: IWaHomeCompany[];
  jobs?: IWaJob[];
  classifications?: IWaClassification[];
  managementHrises?: IWaManagementHris[];
  managerialLinks?: IManagerialLink[];
  seniorities?: IWaSeniority[];
  languages?: IWaLanguage[];
  fixedCompensations?: IFixedCompensation[];
  variableCompensations?: IVariableCompensation[];
  waEmployee?: IWaEmployee;
}

export class WaEmployee implements IWaEmployee {
  constructor(
    public id?: number,
    public ggi?: string,
    public homeHostIndicator?: string,
    public legalGroupCompanyCode?: string,
    public prefix?: string,
    public latinBirthName?: string,
    public localBirthName?: string,
    public latinCommonName?: string,
    public localCommonName?: string,
    public latinNameComplement?: string,
    public localNameComplement?: string,
    public latinFirstName?: string,
    public localFirstName?: string,
    public employeeBirthDate?: Moment,
    public countryOfBirth?: string,
    public birthDepartment?: string,
    public localIdNumber?: string,
    public sensitiveEmployeeFlag?: string,
    public employees?: IWaEmployee[],
    public genders?: IWaGender[],
    public nationalities?: IWaNationality[],
    public maritalStatuses?: IWaMaritalStatus[],
    public personalAddresses?: IWaPersonalAddress[],
    public assignments?: IWaAssignment[],
    public hostCompanies?: IWaHostCompany[],
    public homeCompanies?: IWaHomeCompany[],
    public jobs?: IWaJob[],
    public classifications?: IWaClassification[],
    public managementHrises?: IWaManagementHris[],
    public managerialLinks?: IManagerialLink[],
    public seniorities?: IWaSeniority[],
    public languages?: IWaLanguage[],
    public fixedCompensations?: IFixedCompensation[],
    public variableCompensations?: IVariableCompensation[],
    public waEmployee?: IWaEmployee
  ) {}
}
