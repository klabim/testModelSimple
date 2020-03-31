import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaHostCompany, WaHostCompany } from 'app/shared/model/wa-host-company.model';
import { WaHostCompanyService } from './wa-host-company.service';
import { WaHostCompanyComponent } from './wa-host-company.component';
import { WaHostCompanyDetailComponent } from './wa-host-company-detail.component';
import { WaHostCompanyUpdateComponent } from './wa-host-company-update.component';

@Injectable({ providedIn: 'root' })
export class WaHostCompanyResolve implements Resolve<IWaHostCompany> {
  constructor(private service: WaHostCompanyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaHostCompany> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waHostCompany: HttpResponse<WaHostCompany>) => {
          if (waHostCompany.body) {
            return of(waHostCompany.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaHostCompany());
  }
}

export const waHostCompanyRoute: Routes = [
  {
    path: '',
    component: WaHostCompanyComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHostCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaHostCompanyDetailComponent,
    resolve: {
      waHostCompany: WaHostCompanyResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHostCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaHostCompanyUpdateComponent,
    resolve: {
      waHostCompany: WaHostCompanyResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHostCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaHostCompanyUpdateComponent,
    resolve: {
      waHostCompany: WaHostCompanyResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHostCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
