import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaManagementHris {
  id?: number;
  managementHrisCode?: string;
  localIdNumber?: string;
  managementHrisLabel?: string;
  entityManagementCode?: string;
  waEmployee?: IWaEmployee;
}

export class WaManagementHris implements IWaManagementHris {
  constructor(
    public id?: number,
    public managementHrisCode?: string,
    public localIdNumber?: string,
    public managementHrisLabel?: string,
    public entityManagementCode?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
