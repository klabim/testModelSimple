import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaGender {
  id?: number;
  localIdNumber?: string;
  employeeLocalGender?: string;
  employeeGender?: string;
  waEmployee?: IWaEmployee;
}

export class WaGender implements IWaGender {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public employeeLocalGender?: string,
    public employeeGender?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
