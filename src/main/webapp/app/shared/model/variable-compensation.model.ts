import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IVariableCompensation {
  id?: number;
  localIdNumber?: string;
  variableCompensationTypeGroupCode?: string;
  variableCompensationTypeGroupLabel?: string;
  effectiveBeginningDate?: Moment;
  variableCompensationCurrency?: string;
  variableCompensationLocalTypeCode?: string;
  variableCompensationLocalTypeLabel?: string;
  variableCompensationAmount?: string;
  effectiveEndDate?: Moment;
  waEmployee?: IWaEmployee;
}

export class VariableCompensation implements IVariableCompensation {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public variableCompensationTypeGroupCode?: string,
    public variableCompensationTypeGroupLabel?: string,
    public effectiveBeginningDate?: Moment,
    public variableCompensationCurrency?: string,
    public variableCompensationLocalTypeCode?: string,
    public variableCompensationLocalTypeLabel?: string,
    public variableCompensationAmount?: string,
    public effectiveEndDate?: Moment,
    public waEmployee?: IWaEmployee
  ) {}
}
