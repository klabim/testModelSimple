import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';

type EntityResponseType = HttpResponse<IWaEmployee>;
type EntityArrayResponseType = HttpResponse<IWaEmployee[]>;

@Injectable({ providedIn: 'root' })
export class WaEmployeeService {
  public resourceUrl = SERVER_API_URL + 'api/wa-employees';

  constructor(protected http: HttpClient) {}

  create(waEmployee: IWaEmployee): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waEmployee);
    return this.http
      .post<IWaEmployee>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waEmployee: IWaEmployee): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waEmployee);
    return this.http
      .put<IWaEmployee>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaEmployee>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaEmployee[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waEmployee: IWaEmployee): IWaEmployee {
    const copy: IWaEmployee = Object.assign({}, waEmployee, {
      employeeBirthDate:
        waEmployee.employeeBirthDate && waEmployee.employeeBirthDate.isValid() ? waEmployee.employeeBirthDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.employeeBirthDate = res.body.employeeBirthDate ? moment(res.body.employeeBirthDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((waEmployee: IWaEmployee) => {
        waEmployee.employeeBirthDate = waEmployee.employeeBirthDate ? moment(waEmployee.employeeBirthDate) : undefined;
      });
    }
    return res;
  }
}
