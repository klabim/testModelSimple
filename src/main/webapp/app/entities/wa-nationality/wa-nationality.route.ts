import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaNationality, WaNationality } from 'app/shared/model/wa-nationality.model';
import { WaNationalityService } from './wa-nationality.service';
import { WaNationalityComponent } from './wa-nationality.component';
import { WaNationalityDetailComponent } from './wa-nationality-detail.component';
import { WaNationalityUpdateComponent } from './wa-nationality-update.component';

@Injectable({ providedIn: 'root' })
export class WaNationalityResolve implements Resolve<IWaNationality> {
  constructor(private service: WaNationalityService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaNationality> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waNationality: HttpResponse<WaNationality>) => {
          if (waNationality.body) {
            return of(waNationality.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaNationality());
  }
}

export const waNationalityRoute: Routes = [
  {
    path: '',
    component: WaNationalityComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waNationality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaNationalityDetailComponent,
    resolve: {
      waNationality: WaNationalityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waNationality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaNationalityUpdateComponent,
    resolve: {
      waNationality: WaNationalityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waNationality.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaNationalityUpdateComponent,
    resolve: {
      waNationality: WaNationalityResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waNationality.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
