import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaHomeCompanyComponent } from './wa-home-company.component';
import { WaHomeCompanyDetailComponent } from './wa-home-company-detail.component';
import { WaHomeCompanyUpdateComponent } from './wa-home-company-update.component';
import { WaHomeCompanyDeleteDialogComponent } from './wa-home-company-delete-dialog.component';
import { waHomeCompanyRoute } from './wa-home-company.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waHomeCompanyRoute)],
  declarations: [WaHomeCompanyComponent, WaHomeCompanyDetailComponent, WaHomeCompanyUpdateComponent, WaHomeCompanyDeleteDialogComponent],
  entryComponents: [WaHomeCompanyDeleteDialogComponent]
})
export class TestModelSimpleWaHomeCompanyModule {}
