import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaNationality {
  id?: number;
  localIdNumber?: string;
  nationalityCountryCode?: string;
  effectiveDate?: Moment;
  waEmployee?: IWaEmployee;
}

export class WaNationality implements IWaNationality {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public nationalityCountryCode?: string,
    public effectiveDate?: Moment,
    public waEmployee?: IWaEmployee
  ) {}
}
