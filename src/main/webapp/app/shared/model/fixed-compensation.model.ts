import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IFixedCompensation {
  id?: number;
  localIdNumber?: string;
  fixedCompensationTypeGroupCode?: string;
  fixedCompensationTypeGroupLabel?: string;
  effectiveBeginningDate?: Moment;
  fixedCompensationCurrency?: string;
  fixedCompensationLocalTypeCode?: string;
  fixedCompensationLocalTypeLabel?: string;
  fixedCompensationAmount?: string;
  compensationFrequency?: string;
  effectiveEndDate?: Moment;
  fixedCompensationEffectiveSequence?: string;
  waEmployee?: IWaEmployee;
}

export class FixedCompensation implements IFixedCompensation {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public fixedCompensationTypeGroupCode?: string,
    public fixedCompensationTypeGroupLabel?: string,
    public effectiveBeginningDate?: Moment,
    public fixedCompensationCurrency?: string,
    public fixedCompensationLocalTypeCode?: string,
    public fixedCompensationLocalTypeLabel?: string,
    public fixedCompensationAmount?: string,
    public compensationFrequency?: string,
    public effectiveEndDate?: Moment,
    public fixedCompensationEffectiveSequence?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
