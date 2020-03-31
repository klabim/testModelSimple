import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaSeniority, WaSeniority } from 'app/shared/model/wa-seniority.model';
import { WaSeniorityService } from './wa-seniority.service';
import { WaSeniorityComponent } from './wa-seniority.component';
import { WaSeniorityDetailComponent } from './wa-seniority-detail.component';
import { WaSeniorityUpdateComponent } from './wa-seniority-update.component';

@Injectable({ providedIn: 'root' })
export class WaSeniorityResolve implements Resolve<IWaSeniority> {
  constructor(private service: WaSeniorityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaSeniority> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waSeniority: HttpResponse<WaSeniority>) => {
          if (waSeniority.body) {
            return of(waSeniority.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaSeniority());
  }
}

export const waSeniorityRoute: Routes = [
  {
    path: '',
    component: WaSeniorityComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waSeniority.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaSeniorityDetailComponent,
    resolve: {
      waSeniority: WaSeniorityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waSeniority.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaSeniorityUpdateComponent,
    resolve: {
      waSeniority: WaSeniorityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waSeniority.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaSeniorityUpdateComponent,
    resolve: {
      waSeniority: WaSeniorityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waSeniority.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
