import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaJob, WaJob } from 'app/shared/model/wa-job.model';
import { WaJobService } from './wa-job.service';
import { WaJobComponent } from './wa-job.component';
import { WaJobDetailComponent } from './wa-job-detail.component';
import { WaJobUpdateComponent } from './wa-job-update.component';

@Injectable({ providedIn: 'root' })
export class WaJobResolve implements Resolve<IWaJob> {
  constructor(private service: WaJobService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaJob> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waJob: HttpResponse<WaJob>) => {
          if (waJob.body) {
            return of(waJob.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaJob());
  }
}

export const waJobRoute: Routes = [
  {
    path: '',
    component: WaJobComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waJob.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaJobDetailComponent,
    resolve: {
      waJob: WaJobResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waJob.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaJobUpdateComponent,
    resolve: {
      waJob: WaJobResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waJob.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaJobUpdateComponent,
    resolve: {
      waJob: WaJobResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waJob.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
