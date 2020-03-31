import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IManagerialLink {
  id?: number;
  managerialLinkType?: string;
  localIdNumber?: string;
  localIDDirectManager?: string;
  ggiDirectLineManager?: string;
  managerLastName?: string;
  managerFirstName?: string;
  waEmployee?: IWaEmployee;
}

export class ManagerialLink implements IManagerialLink {
  constructor(
    public id?: number,
    public managerialLinkType?: string,
    public localIdNumber?: string,
    public localIDDirectManager?: string,
    public ggiDirectLineManager?: string,
    public managerLastName?: string,
    public managerFirstName?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
