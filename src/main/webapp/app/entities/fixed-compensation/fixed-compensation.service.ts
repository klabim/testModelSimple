import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IFixedCompensation } from 'app/shared/model/fixed-compensation.model';

type EntityResponseType = HttpResponse<IFixedCompensation>;
type EntityArrayResponseType = HttpResponse<IFixedCompensation[]>;

@Injectable({ providedIn: 'root' })
export class FixedCompensationService {
  public resourceUrl = SERVER_API_URL + 'api/fixed-compensations';

  constructor(protected http: HttpClient) {}

  create(fixedCompensation: IFixedCompensation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fixedCompensation);
    return this.http
      .post<IFixedCompensation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(fixedCompensation: IFixedCompensation): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(fixedCompensation);
    return this.http
      .put<IFixedCompensation>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IFixedCompensation>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IFixedCompensation[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(fixedCompensation: IFixedCompensation): IFixedCompensation {
    const copy: IFixedCompensation = Object.assign({}, fixedCompensation, {
      effectiveBeginningDate:
        fixedCompensation.effectiveBeginningDate && fixedCompensation.effectiveBeginningDate.isValid()
          ? fixedCompensation.effectiveBeginningDate.toJSON()
          : undefined,
      effectiveEndDate:
        fixedCompensation.effectiveEndDate && fixedCompensation.effectiveEndDate.isValid()
          ? fixedCompensation.effectiveEndDate.toJSON()
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
      res.body.forEach((fixedCompensation: IFixedCompensation) => {
        fixedCompensation.effectiveBeginningDate = fixedCompensation.effectiveBeginningDate
          ? moment(fixedCompensation.effectiveBeginningDate)
          : undefined;
        fixedCompensation.effectiveEndDate = fixedCompensation.effectiveEndDate ? moment(fixedCompensation.effectiveEndDate) : undefined;
      });
    }
    return res;
  }
}
