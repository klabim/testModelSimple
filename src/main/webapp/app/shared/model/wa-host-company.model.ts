import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaHostCompany {
  id?: number;
  localIdNumber?: string;
  effectiveDate?: Moment;
  sequence?: string;
  assignmentType?: string;
  assignmentTypeLabel?: string;
  hostCompanyCode?: string;
  groupHostCompanyCode?: string;
  waEmployee?: IWaEmployee;
}

export class WaHostCompany implements IWaHostCompany {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public effectiveDate?: Moment,
    public sequence?: string,
    public assignmentType?: string,
    public assignmentTypeLabel?: string,
    public hostCompanyCode?: string,
    public groupHostCompanyCode?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
