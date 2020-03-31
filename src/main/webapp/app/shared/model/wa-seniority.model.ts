import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaSeniority {
  id?: number;
  localIdNumber?: string;
  groupSgDateOfEntry?: Moment;
  sgSeniorityDate?: Moment;
  hireDate?: Moment;
  bankingSectorSeniority?: string;
  endingOfTrialPeriodEstimatedDate?: Moment;
  waEmployee?: IWaEmployee;
}

export class WaSeniority implements IWaSeniority {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public groupSgDateOfEntry?: Moment,
    public sgSeniorityDate?: Moment,
    public hireDate?: Moment,
    public bankingSectorSeniority?: string,
    public endingOfTrialPeriodEstimatedDate?: Moment,
    public waEmployee?: IWaEmployee
  ) {}
}
