import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaManagementHris, WaManagementHris } from 'app/shared/model/wa-management-hris.model';
import { WaManagementHrisService } from './wa-management-hris.service';
import { WaManagementHrisComponent } from './wa-management-hris.component';
import { WaManagementHrisDetailComponent } from './wa-management-hris-detail.component';
import { WaManagementHrisUpdateComponent } from './wa-management-hris-update.component';

@Injectable({ providedIn: 'root' })
export class WaManagementHrisResolve implements Resolve<IWaManagementHris> {
  constructor(private service: WaManagementHrisService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaManagementHris> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waManagementHris: HttpResponse<WaManagementHris>) => {
          if (waManagementHris.body) {
            return of(waManagementHris.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaManagementHris());
  }
}

export const waManagementHrisRoute: Routes = [
  {
    path: '',
    component: WaManagementHrisComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waManagementHris.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaManagementHrisDetailComponent,
    resolve: {
      waManagementHris: WaManagementHrisResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waManagementHris.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaManagementHrisUpdateComponent,
    resolve: {
      waManagementHris: WaManagementHrisResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waManagementHris.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaManagementHrisUpdateComponent,
    resolve: {
      waManagementHris: WaManagementHrisResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waManagementHris.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
