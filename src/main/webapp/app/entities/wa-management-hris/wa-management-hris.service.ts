import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaManagementHris } from 'app/shared/model/wa-management-hris.model';

type EntityResponseType = HttpResponse<IWaManagementHris>;
type EntityArrayResponseType = HttpResponse<IWaManagementHris[]>;

@Injectable({ providedIn: 'root' })
export class WaManagementHrisService {
  public resourceUrl = SERVER_API_URL + 'api/wa-management-hrises';

  constructor(protected http: HttpClient) {}

  create(waManagementHris: IWaManagementHris): Observable<EntityResponseType> {
    return this.http.post<IWaManagementHris>(this.resourceUrl, waManagementHris, { observe: 'response' });
  }

  update(waManagementHris: IWaManagementHris): Observable<EntityResponseType> {
    return this.http.put<IWaManagementHris>(this.resourceUrl, waManagementHris, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWaManagementHris>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWaManagementHris[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
