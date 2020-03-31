import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaNationality } from 'app/shared/model/wa-nationality.model';

type EntityResponseType = HttpResponse<IWaNationality>;
type EntityArrayResponseType = HttpResponse<IWaNationality[]>;

@Injectable({ providedIn: 'root' })
export class WaNationalityService {
  public resourceUrl = SERVER_API_URL + 'api/wa-nationalities';

  constructor(protected http: HttpClient) {}

  create(waNationality: IWaNationality): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waNationality);
    return this.http
      .post<IWaNationality>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waNationality: IWaNationality): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waNationality);
    return this.http
      .put<IWaNationality>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaNationality>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaNationality[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waNationality: IWaNationality): IWaNationality {
    const copy: IWaNationality = Object.assign({}, waNationality, {
      effectiveDate: waNationality.effectiveDate && waNationality.effectiveDate.isValid() ? waNationality.effectiveDate.toJSON() : undefined
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
      res.body.forEach((waNationality: IWaNationality) => {
        waNationality.effectiveDate = waNationality.effectiveDate ? moment(waNationality.effectiveDate) : undefined;
      });
    }
    return res;
  }
}
