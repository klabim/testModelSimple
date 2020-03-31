import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaMaritalStatus, WaMaritalStatus } from 'app/shared/model/wa-marital-status.model';
import { WaMaritalStatusService } from './wa-marital-status.service';
import { WaMaritalStatusComponent } from './wa-marital-status.component';
import { WaMaritalStatusDetailComponent } from './wa-marital-status-detail.component';
import { WaMaritalStatusUpdateComponent } from './wa-marital-status-update.component';

@Injectable({ providedIn: 'root' })
export class WaMaritalStatusResolve implements Resolve<IWaMaritalStatus> {
  constructor(private service: WaMaritalStatusService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaMaritalStatus> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waMaritalStatus: HttpResponse<WaMaritalStatus>) => {
          if (waMaritalStatus.body) {
            return of(waMaritalStatus.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaMaritalStatus());
  }
}

export const waMaritalStatusRoute: Routes = [
  {
    path: '',
    component: WaMaritalStatusComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waMaritalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaMaritalStatusDetailComponent,
    resolve: {
      waMaritalStatus: WaMaritalStatusResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waMaritalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaMaritalStatusUpdateComponent,
    resolve: {
      waMaritalStatus: WaMaritalStatusResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waMaritalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaMaritalStatusUpdateComponent,
    resolve: {
      waMaritalStatus: WaMaritalStatusResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waMaritalStatus.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
