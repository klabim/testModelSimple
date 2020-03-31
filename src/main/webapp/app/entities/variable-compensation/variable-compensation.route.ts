import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IVariableCompensation, VariableCompensation } from 'app/shared/model/variable-compensation.model';
import { VariableCompensationService } from './variable-compensation.service';
import { VariableCompensationComponent } from './variable-compensation.component';
import { VariableCompensationDetailComponent } from './variable-compensation-detail.component';
import { VariableCompensationUpdateComponent } from './variable-compensation-update.component';

@Injectable({ providedIn: 'root' })
export class VariableCompensationResolve implements Resolve<IVariableCompensation> {
  constructor(private service: VariableCompensationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IVariableCompensation> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((variableCompensation: HttpResponse<VariableCompensation>) => {
          if (variableCompensation.body) {
            return of(variableCompensation.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new VariableCompensation());
  }
}

export const variableCompensationRoute: Routes = [
  {
    path: '',
    component: VariableCompensationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.variableCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: VariableCompensationDetailComponent,
    resolve: {
      variableCompensation: VariableCompensationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.variableCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: VariableCompensationUpdateComponent,
    resolve: {
      variableCompensation: VariableCompensationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.variableCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: VariableCompensationUpdateComponent,
    resolve: {
      variableCompensation: VariableCompensationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.variableCompensation.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
