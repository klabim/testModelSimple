import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaHostCompany } from 'app/shared/model/wa-host-company.model';

type EntityResponseType = HttpResponse<IWaHostCompany>;
type EntityArrayResponseType = HttpResponse<IWaHostCompany[]>;

@Injectable({ providedIn: 'root' })
export class WaHostCompanyService {
  public resourceUrl = SERVER_API_URL + 'api/wa-host-companies';

  constructor(protected http: HttpClient) {}

  create(waHostCompany: IWaHostCompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waHostCompany);
    return this.http
      .post<IWaHostCompany>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waHostCompany: IWaHostCompany): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waHostCompany);
    return this.http
      .put<IWaHostCompany>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaHostCompany>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaHostCompany[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waHostCompany: IWaHostCompany): IWaHostCompany {
    const copy: IWaHostCompany = Object.assign({}, waHostCompany, {
      effectiveDate: waHostCompany.effectiveDate && waHostCompany.effectiveDate.isValid() ? waHostCompany.effectiveDate.toJSON() : undefined
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
      res.body.forEach((waHostCompany: IWaHostCompany) => {
        waHostCompany.effectiveDate = waHostCompany.effectiveDate ? moment(waHostCompany.effectiveDate) : undefined;
      });
    }
    return res;
  }
}
