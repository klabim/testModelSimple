import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaClassification, WaClassification } from 'app/shared/model/wa-classification.model';
import { WaClassificationService } from './wa-classification.service';
import { WaClassificationComponent } from './wa-classification.component';
import { WaClassificationDetailComponent } from './wa-classification-detail.component';
import { WaClassificationUpdateComponent } from './wa-classification-update.component';

@Injectable({ providedIn: 'root' })
export class WaClassificationResolve implements Resolve<IWaClassification> {
  constructor(private service: WaClassificationService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaClassification> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waClassification: HttpResponse<WaClassification>) => {
          if (waClassification.body) {
            return of(waClassification.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaClassification());
  }
}

export const waClassificationRoute: Routes = [
  {
    path: '',
    component: WaClassificationComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waClassification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaClassificationDetailComponent,
    resolve: {
      waClassification: WaClassificationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waClassification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaClassificationUpdateComponent,
    resolve: {
      waClassification: WaClassificationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waClassification.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaClassificationUpdateComponent,
    resolve: {
      waClassification: WaClassificationResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waClassification.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
