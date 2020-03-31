import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaAssignment } from 'app/shared/model/wa-assignment.model';

type EntityResponseType = HttpResponse<IWaAssignment>;
type EntityArrayResponseType = HttpResponse<IWaAssignment[]>;

@Injectable({ providedIn: 'root' })
export class WaAssignmentService {
  public resourceUrl = SERVER_API_URL + 'api/wa-assignments';

  constructor(protected http: HttpClient) {}

  create(waAssignment: IWaAssignment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waAssignment);
    return this.http
      .post<IWaAssignment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waAssignment: IWaAssignment): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waAssignment);
    return this.http
      .put<IWaAssignment>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaAssignment>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaAssignment[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waAssignment: IWaAssignment): IWaAssignment {
    const copy: IWaAssignment = Object.assign({}, waAssignment, {
      effectiveDate: waAssignment.effectiveDate && waAssignment.effectiveDate.isValid() ? waAssignment.effectiveDate.toJSON() : undefined
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
      res.body.forEach((waAssignment: IWaAssignment) => {
        waAssignment.effectiveDate = waAssignment.effectiveDate ? moment(waAssignment.effectiveDate) : undefined;
      });
    }
    return res;
  }
}
