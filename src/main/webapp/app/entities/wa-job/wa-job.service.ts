import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IWaJob } from 'app/shared/model/wa-job.model';

type EntityResponseType = HttpResponse<IWaJob>;
type EntityArrayResponseType = HttpResponse<IWaJob[]>;

@Injectable({ providedIn: 'root' })
export class WaJobService {
  public resourceUrl = SERVER_API_URL + 'api/wa-jobs';

  constructor(protected http: HttpClient) {}

  create(waJob: IWaJob): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waJob);
    return this.http
      .post<IWaJob>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(waJob: IWaJob): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(waJob);
    return this.http
      .put<IWaJob>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IWaJob>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IWaJob[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(waJob: IWaJob): IWaJob {
    const copy: IWaJob = Object.assign({}, waJob, {
      effectiveDate: waJob.effectiveDate && waJob.effectiveDate.isValid() ? waJob.effectiveDate.toJSON() : undefined,
      positionEntryDate: waJob.positionEntryDate && waJob.positionEntryDate.isValid() ? waJob.positionEntryDate.toJSON() : undefined,
      jobEntryDate: waJob.jobEntryDate && waJob.jobEntryDate.isValid() ? waJob.jobEntryDate.toJSON() : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.effectiveDate = res.body.effectiveDate ? moment(res.body.effectiveDate) : undefined;
      res.body.positionEntryDate = res.body.positionEntryDate ? moment(res.body.positionEntryDate) : undefined;
      res.body.jobEntryDate = res.body.jobEntryDate ? moment(res.body.jobEntryDate) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((waJob: IWaJob) => {
        waJob.effectiveDate = waJob.effectiveDate ? moment(waJob.effectiveDate) : undefined;
        waJob.positionEntryDate = waJob.positionEntryDate ? moment(waJob.positionEntryDate) : undefined;
        waJob.jobEntryDate = waJob.jobEntryDate ? moment(waJob.jobEntryDate) : undefined;
      });
    }
    return res;
  }
}
