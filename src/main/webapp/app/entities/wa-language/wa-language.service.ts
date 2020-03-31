import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaLanguage } from 'app/shared/model/wa-language.model';

type EntityResponseType = HttpResponse<IWaLanguage>;
type EntityArrayResponseType = HttpResponse<IWaLanguage[]>;

@Injectable({ providedIn: 'root' })
export class WaLanguageService {
  public resourceUrl = SERVER_API_URL + 'api/wa-languages';

  constructor(protected http: HttpClient) {}

  create(waLanguage: IWaLanguage): Observable<EntityResponseType> {
    return this.http.post<IWaLanguage>(this.resourceUrl, waLanguage, { observe: 'response' });
  }

  update(waLanguage: IWaLanguage): Observable<EntityResponseType> {
    return this.http.put<IWaLanguage>(this.resourceUrl, waLanguage, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IWaLanguage>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IWaLanguage[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
