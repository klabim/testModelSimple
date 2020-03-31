import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IManagerialLink, ManagerialLink } from 'app/shared/model/managerial-link.model';
import { ManagerialLinkService } from './managerial-link.service';
import { ManagerialLinkComponent } from './managerial-link.component';
import { ManagerialLinkDetailComponent } from './managerial-link-detail.component';
import { ManagerialLinkUpdateComponent } from './managerial-link-update.component';

@Injectable({ providedIn: 'root' })
export class ManagerialLinkResolve implements Resolve<IManagerialLink> {
  constructor(private service: ManagerialLinkService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IManagerialLink> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((managerialLink: HttpResponse<ManagerialLink>) => {
          if (managerialLink.body) {
            return of(managerialLink.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new ManagerialLink());
  }
}

export const managerialLinkRoute: Routes = [
  {
    path: '',
    component: ManagerialLinkComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.managerialLink.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: ManagerialLinkDetailComponent,
    resolve: {
      managerialLink: ManagerialLinkResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.managerialLink.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: ManagerialLinkUpdateComponent,
    resolve: {
      managerialLink: ManagerialLinkResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.managerialLink.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: ManagerialLinkUpdateComponent,
    resolve: {
      managerialLink: ManagerialLinkResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.managerialLink.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
