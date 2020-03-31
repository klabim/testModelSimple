import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaMaritalStatus } from 'app/shared/model/wa-marital-status.model';

type EntityResponseType = HttpResponse<IWaMaritalStatus>;
type EntityArrayResponseType = HttpResponse<IWaMaritalStatus[]>;

@Injectable({ providedIn: 'root' })
export class WaMaritalStatusService {
  public resourceUrl = SERVER_API_URL + 'api/wa-marital-statuses';

  constructor(protected http: HttpClient) {}

  create(waMaritalStatus: IWaMaritalStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waMaritalStatus);
    return this.http
      .post<IWaMaritalStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waMaritalStatus: IWaMaritalStatus): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waMaritalStatus);
    return this.http
      .put<IWaMaritalStatus>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaMaritalStatus>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaMaritalStatus[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waMaritalStatus: IWaMaritalStatus): IWaMaritalStatus {
    const copy: IWaMaritalStatus = Object.assign({}, waMaritalStatus, {
      effectiveDate:
        waMaritalStatus.effectiveDate && waMaritalStatus.effectiveDate.isValid() ? waMaritalStatus.effectiveDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.effectiveDate = res.body.effectiveDate ? moment(res.body.effectiveDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((waMaritalStatus: IWaMaritalStatus) => {
        waMaritalStatus.effectiveDate = waMaritalStatus.effectiveDate ? moment(waMaritalStatus.effectiveDate) : undefined;
      });
    }
    return res;
  }
}
