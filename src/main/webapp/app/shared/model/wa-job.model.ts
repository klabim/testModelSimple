import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaJob {
  id?: number;
  localIdNumber?: string;
  effectiveDate?: Moment;
  sequence?: string;
  localPositionCode?: string;
  localPositionLevel?: string;
  positionEntryDate?: Moment;
  jobCodeLocalValue?: string;
  jobCodeGroupeValue?: string;
  jobEntryDate?: Moment;
  localCollectiveAgreementCode?: string;
  localCollectiveAgreementLabel?: string;
  orgRelationship?: string;
  prescripteur?: string;
  waEmployee?: IWaEmployee;
}

export class WaJob implements IWaJob {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public effectiveDate?: Moment,
    public sequence?: string,
    public localPositionCode?: string,
    public localPositionLevel?: string,
    public positionEntryDate?: Moment,
    public jobCodeLocalValue?: string,
    public jobCodeGroupeValue?: string,
    public jobEntryDate?: Moment,
    public localCollectiveAgreementCode?: string,
    public localCollectiveAgreementLabel?: string,
    public orgRelationship?: string,
    public prescripteur?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
