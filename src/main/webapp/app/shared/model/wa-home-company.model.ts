import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaHomeCompany {
  id?: number;
  localIdNumber?: string;
  effectiveAssignmentDate?: Moment;
  sequence?: string;
  assignmentType?: string;
  assignmentTypeLabel?: string;
  homeCompanyCode?: string;
  groupHomeCompanyCode?: string;
  waEmployee?: IWaEmployee;
}

export class WaHomeCompany implements IWaHomeCompany {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public effectiveAssignmentDate?: Moment,
    public sequence?: string,
    public assignmentType?: string,
    public assignmentTypeLabel?: string,
    public homeCompanyCode?: string,
    public groupHomeCompanyCode?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
