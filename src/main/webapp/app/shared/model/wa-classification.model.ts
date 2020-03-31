import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaClassification {
  id?: number;
  localIdNumber?: string;
  effectiveDate?: Moment;
  sequence?: string;
  localClassificationCode?: string;
  localClassificationLabel?: string;
  groupClassificationCode?: string;
  groupClassificationLabel?: string;
  professionalCategoryCode?: string;
  professionalCategoryLabel?: string;
  waEmployee?: IWaEmployee;
}

export class WaClassification implements IWaClassification {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public effectiveDate?: Moment,
    public sequence?: string,
    public localClassificationCode?: string,
    public localClassificationLabel?: string,
    public groupClassificationCode?: string,
    public groupClassificationLabel?: string,
    public professionalCategoryCode?: string,
    public professionalCategoryLabel?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
