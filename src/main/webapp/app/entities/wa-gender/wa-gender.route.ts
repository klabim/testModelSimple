import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaGender, WaGender } from 'app/shared/model/wa-gender.model';
import { WaGenderService } from './wa-gender.service';
import { WaGenderComponent } from './wa-gender.component';
import { WaGenderDetailComponent } from './wa-gender-detail.component';
import { WaGenderUpdateComponent } from './wa-gender-update.component';

@Injectable({ providedIn: 'root' })
export class WaGenderResolve implements Resolve<IWaGender> {
  constructor(private service: WaGenderService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaGender> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waGender: HttpResponse<WaGender>) => {
          if (waGender.body) {
            return of(waGender.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaGender());
  }
}

export const waGenderRoute: Routes = [
  {
    path: '',
    component: WaGenderComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waGender.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaGenderDetailComponent,
    resolve: {
      waGender: WaGenderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waGender.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaGenderUpdateComponent,
    resolve: {
      waGender: WaGenderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waGender.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaGenderUpdateComponent,
    resolve: {
      waGender: WaGenderResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waGender.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
