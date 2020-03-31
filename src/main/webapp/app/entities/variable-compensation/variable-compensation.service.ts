import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IVariableCompensation } from 'app/shared/model/variable-compensation.model';

type EntityResponseType = HttpResponse<IVariableCompensation>;
type EntityArrayResponseType = HttpResponse<IVariableCompensation[]>;

@Injectable({ providedIn: 'root' })
export class VariableCompensationService {
  public resourceUrl = SERVER_API_URL + 'api/variable-compensations';

  constructor(protected http: HttpClient) {}

  create(variableCompensation: IVariableCompensation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(variableCompensation);
    return this.http
      .post<IVariableCompensation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(variableCompensation: IVariableCompensation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(variableCompensation);
    return this.http
      .put<IVariableCompensation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVariableCompensation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVariableCompensation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(variableCompensation: IVariableCompensation): IVariableCompensation {
    const copy: IVariableCompensation = Object.assign({}, variableCompensation, {
      effectiveBeginningDate:
        variableCompensation.effectiveBeginningDate && variableCompensation.effectiveBeginningDate.isValid()
          ? variableCompensation.effectiveBeginningDate.toJSON()
          : undefined,
      effectiveEndDate:
        variableCompensation.effectiveEndDate && variableCompensation.effectiveEndDate.isValid()
          ? variableCompensation.effectiveEndDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.effectiveBeginningDate = res.body.effectiveBeginningDate ? moment(res.body.effectiveBeginningDate) : undefined;
      res.body.effectiveEndDate = res.body.effectiveEndDate ? moment(res.body.effectiveEndDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((variableCompensation: IVariableCompensation) => {
        variableCompensation.effectiveBeginningDate = variableCompensation.effectiveBeginningDate
          ? moment(variableCompensation.effectiveBeginningDate)
          : undefined;
        variableCompensation.effectiveEndDate = variableCompensation.effectiveEndDate
          ? moment(variableCompensation.effectiveEndDate)
          : undefined;
      });
    }
    return res;
  }
}
