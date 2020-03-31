import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IManagerialLink } from 'app/shared/model/managerial-link.model';

type EntityResponseType = HttpResponse<IManagerialLink>;
type EntityArrayResponseType = HttpResponse<IManagerialLink[]>;

@Injectable({ providedIn: 'root' })
export class ManagerialLinkService {
  public resourceUrl = SERVER_API_URL + 'api/managerial-links';

  constructor(protected http: HttpClient) {}

  create(managerialLink: IManagerialLink): Observable<EntityResponseType> {
    return this.http.post<IManagerialLink>(this.resourceUrl, managerialLink, { observe: 'response' });
  }

  update(managerialLink: IManagerialLink): Observable<EntityResponseType> {
    return this.http.put<IManagerialLink>(this.resourceUrl, managerialLink, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IManagerialLink>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IManagerialLink[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
