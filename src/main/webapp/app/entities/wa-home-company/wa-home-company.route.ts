import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaHomeCompany, WaHomeCompany } from 'app/shared/model/wa-home-company.model';
import { WaHomeCompanyService } from './wa-home-company.service';
import { WaHomeCompanyComponent } from './wa-home-company.component';
import { WaHomeCompanyDetailComponent } from './wa-home-company-detail.component';
import { WaHomeCompanyUpdateComponent } from './wa-home-company-update.component';

@Injectable({ providedIn: 'root' })
export class WaHomeCompanyResolve implements Resolve<IWaHomeCompany> {
  constructor(private service: WaHomeCompanyService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaHomeCompany> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waHomeCompany: HttpResponse<WaHomeCompany>) => {
          if (waHomeCompany.body) {
            return of(waHomeCompany.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaHomeCompany());
  }
}

export const waHomeCompanyRoute: Routes = [
  {
    path: '',
    component: WaHomeCompanyComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHomeCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaHomeCompanyDetailComponent,
    resolve: {
      waHomeCompany: WaHomeCompanyResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHomeCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaHomeCompanyUpdateComponent,
    resolve: {
      waHomeCompany: WaHomeCompanyResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHomeCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaHomeCompanyUpdateComponent,
    resolve: {
      waHomeCompany: WaHomeCompanyResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waHomeCompany.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
