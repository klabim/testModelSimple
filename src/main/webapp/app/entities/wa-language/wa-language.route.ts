import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IWaLanguage, WaLanguage } from 'app/shared/model/wa-language.model';
import { WaLanguageService } from './wa-language.service';
import { WaLanguageComponent } from './wa-language.component';
import { WaLanguageDetailComponent } from './wa-language-detail.component';
import { WaLanguageUpdateComponent } from './wa-language-update.component';

@Injectable({ providedIn: 'root' })
export class WaLanguageResolve implements Resolve<IWaLanguage> {
  constructor(private service: WaLanguageService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IWaLanguage> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((waLanguage: HttpResponse<WaLanguage>) => {
          if (waLanguage.body) {
            return of(waLanguage.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new WaLanguage());
  }
}

export const waLanguageRoute: Routes = [
  {
    path: '',
    component: WaLanguageComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waLanguage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: WaLanguageDetailComponent,
    resolve: {
      waLanguage: WaLanguageResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waLanguage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: WaLanguageUpdateComponent,
    resolve: {
      waLanguage: WaLanguageResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waLanguage.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: WaLanguageUpdateComponent,
    resolve: {
      waLanguage: WaLanguageResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'testModelSimpleApp.waLanguage.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
