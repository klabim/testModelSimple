import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IFixedCompensation, FixedCompensation } from 'app/shared/model/fixed-compensation.model';
import { FixedCompensationService } from './fixed-compensation.service';
import { FixedCompensationComponent } from './fixed-compensation.component';
import { FixedCompensationDetailComponent } from './fixed-compensation-detail.component';
import { FixedCompensationUpdateComponent } from './fixed-compensation-update.component';

@Injectable({ providedIn: 'root' })
export class FixedCompensationResolve implements Resolve<IFixedCompensation> {
  constructor(private service: FixedCompensationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IFixedCompensation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((fixedCompensation: HttpResponse<FixedCompensation>) => {
          if (fixedCompensation.body) {
            return of(fixedCompensation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new FixedCompensation());
  }
}

export const fixedCompensationRoute: Routes = [
  {
    path: '',
    component: FixedCompensationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.fixedCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: FixedCompensationDetailComponent,
    resolve: {
      fixedCompensation: FixedCompensationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.fixedCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: FixedCompensationUpdateComponent,
    resolve: {
      fixedCompensation: FixedCompensationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.fixedCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: FixedCompensationUpdateComponent,
    resolve: {
      fixedCompensation: FixedCompensationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.fixedCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
