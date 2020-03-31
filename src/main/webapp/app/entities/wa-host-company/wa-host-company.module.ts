import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaHostCompanyComponent } from './wa-host-company.component';
import { WaHostCompanyDetailComponent } from './wa-host-company-detail.component';
import { WaHostCompanyUpdateComponent } from './wa-host-company-update.component';
import { WaHostCompanyDeleteDialogComponent } from './wa-host-company-delete-dialog.component';
import { waHostCompanyRoute } from './wa-host-company.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waHostCompanyRoute)],
  declarations: [WaHostCompanyComponent, WaHostCompanyDetailComponent, WaHostCompanyUpdateComponent, WaHostCompanyDeleteDialogComponent],
  entryComponents: [WaHostCompanyDeleteDialogComponent]
})
export class TestModelSimpleWaHostCompanyModule {}
