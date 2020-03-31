import { Moment } from 'moment';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaPersonalAddress {
  id?: number;
  localIdNumber?: string;
  personalAddressType?: string;
  additionalAddress1?: string;
  additionalAddress2?: string;
  physicalAddress?: string;
  additionalPostalDelivery?: string;
  zipCodePostalCode?: string;
  employeeCityOfResidence?: string;
  countryOfResidence?: string;
  effectiveDate?: Moment;
  waEmployee?: IWaEmployee;
}

export class WaPersonalAddress implements IWaPersonalAddress {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public personalAddressType?: string,
    public additionalAddress1?: string,
    public additionalAddress2?: string,
    public physicalAddress?: string,
    public additionalPostalDelivery?: string,
    public zipCodePostalCode?: string,
    public employeeCityOfResidence?: string,
    public countryOfResidence?: string,
    public effectiveDate?: Moment,
    public waEmployee?: IWaEmployee
  ) {}
}
