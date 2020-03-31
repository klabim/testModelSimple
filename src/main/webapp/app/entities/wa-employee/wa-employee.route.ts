import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaEmployee, WaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from './wa-employee.service';
import { WaEmployeeComponent } from './wa-employee.component';
import { WaEmployeeDetailComponent } from './wa-employee-detail.component';
import { WaEmployeeUpdateComponent } from './wa-employee-update.component';

@Injectable({ providedIn: 'root' })
export class WaEmployeeResolve implements Resolve<IWaEmployee> {
  constructor(private service: WaEmployeeService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaEmployee> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waEmployee: HttpResponse<WaEmployee>) => {
          if (waEmployee.body) {
            return of(waEmployee.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaEmployee());
  }
}

export const waEmployeeRoute: Routes = [
  {
    path: '',
    component: WaEmployeeComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaEmployeeDetailComponent,
    resolve: {
      waEmployee: WaEmployeeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaEmployeeUpdateComponent,
    resolve: {
      waEmployee: WaEmployeeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaEmployeeUpdateComponent,
    resolve: {
      waEmployee: WaEmployeeResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waEmployee.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
