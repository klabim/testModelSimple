import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaPersonalAddress } from 'app/shared/model/wa-personal-address.model';

type EntityResponseType = HttpResponse<IWaPersonalAddress>;
type EntityArrayResponseType = HttpResponse<IWaPersonalAddress[]>;

@Injectable({ providedIn: 'root' })
export class WaPersonalAddressService {
  public resourceUrl = SERVER_API_URL + 'api/wa-personal-addresses';

  constructor(protected http: HttpClient) {}

  create(waPersonalAddress: IWaPersonalAddress): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waPersonalAddress);
    return this.http
      .post<IWaPersonalAddress>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waPersonalAddress: IWaPersonalAddress): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waPersonalAddress);
    return this.http
      .put<IWaPersonalAddress>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaPersonalAddress>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaPersonalAddress[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waPersonalAddress: IWaPersonalAddress): IWaPersonalAddress {
    const copy: IWaPersonalAddress = Object.assign({}, waPersonalAddress, {
      effectiveDate:
        waPersonalAddress.effectiveDate && waPersonalAddress.effectiveDate.isValid() ? waPersonalAddress.effectiveDate.toJSON() : undefined
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
      res.body.forEach((waPersonalAddress: IWaPersonalAddress) => {
        waPersonalAddress.effectiveDate = waPersonalAddress.effectiveDate ? moment(waPersonalAddress.effectiveDate) : undefined;
      });
    }
    return res;
  }
}
