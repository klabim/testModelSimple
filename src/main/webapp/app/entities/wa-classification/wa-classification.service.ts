import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaClassification } from 'app/shared/model/wa-classification.model';

type EntityResponseType = HttpResponse<IWaClassification>;
type EntityArrayResponseType = HttpResponse<IWaClassification[]>;

@Injectable({ providedIn: 'root' })
export class WaClassificationService {
  public resourceUrl = SERVER_API_URL + 'api/wa-classifications';

  constructor(protected http: HttpClient) {}

  create(waClassification: IWaClassification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waClassification);
    return this.http
      .post<IWaClassification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waClassification: IWaClassification): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waClassification);
    return this.http
      .put<IWaClassification>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaClassification>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaClassification[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waClassification: IWaClassification): IWaClassification {
    const copy: IWaClassification = Object.assign({}, waClassification, {
      effectiveDate:
        waClassification.effectiveDate && waClassification.effectiveDate.isValid() ? waClassification.effectiveDate.toJSON() : undefined
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
      res.body.forEach((waClassification: IWaClassification) => {
        waClassification.effectiveDate = waClassification.effectiveDate ? moment(waClassification.effectiveDate) : undefined;
      });
    }
    return res;
  }
}
