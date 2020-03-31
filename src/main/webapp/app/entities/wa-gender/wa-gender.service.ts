import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaGender } from 'app/shared/model/wa-gender.model';

type EntityResponseType = HttpResponse<IWaGender>;
type EntityArrayResponseType = HttpResponse<IWaGender[]>;

@Injectable({ providedIn: 'root' })
export class WaGenderService {
  public resourceUrl = SERVER_API_URL + 'api/wa-genders';

  constructor(protected http: HttpClient) {}

  create(waGender: IWaGender): Observable<EntityResponseType> {
    return this.http.post<IWaGender>(this.resourceUrl, waGender, { observe: 'response' });
  }

  update(waGender: IWaGender): Observable<EntityResponseType> {
    return this.http.put<IWaGender>(this.resourceUrl, waGender, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWaGender>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWaGender[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
