import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaSeniority } from 'app/shared/model/wa-seniority.model';

type EntityResponseType = HttpResponse<IWaSeniority>;
type EntityArrayResponseType = HttpResponse<IWaSeniority[]>;

@Injectable({ providedIn: 'root' })
export class WaSeniorityService {
  public resourceUrl = SERVER_API_URL + 'api/wa-seniorities';

  constructor(protected http: HttpClient) {}

  create(waSeniority: IWaSeniority): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waSeniority);
    return this.http
      .post<IWaSeniority>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waSeniority: IWaSeniority): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waSeniority);
    return this.http
      .put<IWaSeniority>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaSeniority>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaSeniority[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waSeniority: IWaSeniority): IWaSeniority {
    const copy: IWaSeniority = Object.assign({}, waSeniority, {
      groupSgDateOfEntry:
        waSeniority.groupSgDateOfEntry && waSeniority.groupSgDateOfEntry.isValid() ? waSeniority.groupSgDateOfEntry.toJSON() : undefined,
      sgSeniorityDate:
        waSeniority.sgSeniorityDate && waSeniority.sgSeniorityDate.isValid() ? waSeniority.sgSeniorityDate.toJSON() : undefined,
      hireDate: waSeniority.hireDate && waSeniority.hireDate.isValid() ? waSeniority.hireDate.toJSON() : undefined,
      endingOfTrialPeriodEstimatedDate:
        waSeniority.endingOfTrialPeriodEstimatedDate && waSeniority.endingOfTrialPeriodEstimatedDate.isValid()
          ? waSeniority.endingOfTrialPeriodEstimatedDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.groupSgDateOfEntry = res.body.groupSgDateOfEntry ? moment(res.body.groupSgDateOfEntry) : undefined;
      res.body.sgSeniorityDate = res.body.sgSeniorityDate ? moment(res.body.sgSeniorityDate) : undefined;
      res.body.hireDate = res.body.hireDate ? moment(res.body.hireDate) : undefined;
      res.body.endingOfTrialPeriodEstimatedDate = res.body.endingOfTrialPeriodEstimatedDate
        ? moment(res.body.endingOfTrialPeriodEstimatedDate)
        : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((waSeniority: IWaSeniority) => {
        waSeniority.groupSgDateOfEntry = waSeniority.groupSgDateOfEntry ? moment(waSeniority.groupSgDateOfEntry) : undefined;
        waSeniority.sgSeniorityDate = waSeniority.sgSeniorityDate ? moment(waSeniority.sgSeniorityDate) : undefined;
        waSeniority.hireDate = waSeniority.hireDate ? moment(waSeniority.hireDate) : undefined;
        waSeniority.endingOfTrialPeriodEstimatedDate = waSeniority.endingOfTrialPeriodEstimatedDate
          ? moment(waSeniority.endingOfTrialPeriodEstimatedDate)
          : undefined;
      });
    }
    return res;
  }
}
