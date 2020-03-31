import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaAssignment, WaAssignment } from 'app/shared/model/wa-assignment.model';
import { WaAssignmentService } from './wa-assignment.service';
import { WaAssignmentComponent } from './wa-assignment.component';
import { WaAssignmentDetailComponent } from './wa-assignment-detail.component';
import { WaAssignmentUpdateComponent } from './wa-assignment-update.component';

@Injectable({ providedIn: 'root' })
export class WaAssignmentResolve implements Resolve<IWaAssignment> {
  constructor(private service: WaAssignmentService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaAssignment> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waAssignment: HttpResponse<WaAssignment>) => {
          if (waAssignment.body) {
            return of(waAssignment.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaAssignment());
  }
}

export const waAssignmentRoute: Routes = [
  {
    path: '',
    component: WaAssignmentComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waAssignment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaAssignmentDetailComponent,
    resolve: {
      waAssignment: WaAssignmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waAssignment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaAssignmentUpdateComponent,
    resolve: {
      waAssignment: WaAssignmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waAssignment.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaAssignmentUpdateComponent,
    resolve: {
      waAssignment: WaAssignmentResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waAssignment.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
