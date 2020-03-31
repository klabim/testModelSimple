import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaPersonalAddress, WaPersonalAddress } from 'app/shared/model/wa-personal-address.model';
import { WaPersonalAddressService } from './wa-personal-address.service';
import { WaPersonalAddressComponent } from './wa-personal-address.component';
import { WaPersonalAddressDetailComponent } from './wa-personal-address-detail.component';
import { WaPersonalAddressUpdateComponent } from './wa-personal-address-update.component';

@Injectable({ providedIn: 'root' })
export class WaPersonalAddressResolve implements Resolve<IWaPersonalAddress> {
  constructor(private service: WaPersonalAddressService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaPersonalAddress> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waPersonalAddress: HttpResponse<WaPersonalAddress>) => {
          if (waPersonalAddress.body) {
            return of(waPersonalAddress.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaPersonalAddress());
  }
}

export const waPersonalAddressRoute: Routes = [
  {
    path: '',
    component: WaPersonalAddressComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waPersonalAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaPersonalAddressDetailComponent,
    resolve: {
      waPersonalAddress: WaPersonalAddressResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waPersonalAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaPersonalAddressUpdateComponent,
    resolve: {
      waPersonalAddress: WaPersonalAddressResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waPersonalAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaPersonalAddressUpdateComponent,
    resolve: {
      waPersonalAddress: WaPersonalAddressResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waPersonalAddress.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
