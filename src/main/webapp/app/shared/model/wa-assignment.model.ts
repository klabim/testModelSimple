import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaAssignment {
  id?: number;
  localIdNumber?: string;
  effectiveDate?: Moment;
  sequence?: string;
  localCompanyCode?: string;
  legalGroupCompanyCode?: string;
  establishment?: string;
  poleActivityGroupCode?: string;
  groupSubPoleActivityCode?: string;
  groupSubPoleActivityTitle?: string;
  budgetaryAffectationDepartmentCode?: string;
  budgetaryAffectationDepartmentLabel?: string;
  budgetFunctionalIdSakkarah?: string;
  administrativeDepartmentLocalCode?: string;
  administrativeDepartmentLocalLabel?: string;
  adminFunctionalIdSakkarah?: string;
  detachmentGroupCompany?: string;
  detachmentLegalGroupCompany?: string;
  waEmployee?: IWaEmployee;
}

export class WaAssignment implements IWaAssignment {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public effectiveDate?: Moment,
    public sequence?: string,
    public localCompanyCode?: string,
    public legalGroupCompanyCode?: string,
    public establishment?: string,
    public poleActivityGroupCode?: string,
    public groupSubPoleActivityCode?: string,
    public groupSubPoleActivityTitle?: string,
    public budgetaryAffectationDepartmentCode?: string,
    public budgetaryAffectationDepartmentLabel?: string,
    public budgetFunctionalIdSakkarah?: string,
    public administrativeDepartmentLocalCode?: string,
    public administrativeDepartmentLocalLabel?: string,
    public adminFunctionalIdSakkarah?: string,
    public detachmentGroupCompany?: string,
    public detachmentLegalGroupCompany?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
