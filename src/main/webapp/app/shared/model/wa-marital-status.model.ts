import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaMaritalStatus {
  id?: number;
  localIdNumber?: string;
  maritalStatusCode?: string;
  maritalStatusLabel?: string;
  effectiveDate?: Moment;
  waEmployee?: IWaEmployee;
}

export class WaMaritalStatus implements IWaMaritalStatus {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public maritalStatusCode?: string,
    public maritalStatusLabel?: string,
    public effectiveDate?: Moment,
    public waEmployee?: IWaEmployee
  ) {}
}
