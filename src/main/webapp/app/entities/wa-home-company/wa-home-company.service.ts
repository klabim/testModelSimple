import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaHomeCompany } from 'app/shared/model/wa-home-company.model';

type EntityResponseType = HttpResponse<IWaHomeCompany>;
type EntityArrayResponseType = HttpResponse<IWaHomeCompany[]>;

@Injectable({ providedIn: 'root' })
export class WaHomeCompanyService {
  public resourceUrl = SERVER_API_URL + 'api/wa-home-companies';

  constructor(protected http: HttpClient) {}

  create(waHomeCompany: IWaHomeCompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waHomeCompany);
    return this.http
      .post<IWaHomeCompany>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waHomeCompany: IWaHomeCompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waHomeCompany);
    return this.http
      .put<IWaHomeCompany>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaHomeCompany>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaHomeCompany[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waHomeCompany: IWaHomeCompany): IWaHomeCompany {
    const copy: IWaHomeCompany = Object.assign({}, waHomeCompany, {
      effectiveAssignmentDate:
        waHomeCompany.effectiveAssignmentDate && waHomeCompany.effectiveAssignmentDate.isValid()
          ? waHomeCompany.effectiveAssignmentDate.toJSON()
          : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.effectiveAssignmentDate = res.body.effectiveAssignmentDate ? moment(res.body.effectiveAssignmentDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((waHomeCompany: IWaHomeCompany) => {
        waHomeCompany.effectiveAssignmentDate = waHomeCompany.effectiveAssignmentDate
          ? moment(waHomeCompany.effectiveAssignmentDate)
          : undefined;
      });
    }
    return res;
  }
}
