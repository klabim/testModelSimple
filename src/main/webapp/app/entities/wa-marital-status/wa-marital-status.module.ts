import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaMaritalStatusComponent } from './wa-marital-status.component';
import { WaMaritalStatusDetailComponent } from './wa-marital-status-detail.component';
import { WaMaritalStatusUpdateComponent } from './wa-marital-status-update.component';
import { WaMaritalStatusDeleteDialogComponent } from './wa-marital-status-delete-dialog.component';
import { waMaritalStatusRoute } from './wa-marital-status.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waMaritalStatusRoute)],
  declarations: [
    WaMaritalStatusComponent,
    WaMaritalStatusDetailComponent,
    WaMaritalStatusUpdateComponent,
    WaMaritalStatusDeleteDialogComponent
  ],
  entryComponents: [WaMaritalStatusDeleteDialogComponent]
})
export class TestModelSimpleWaMaritalStatusModule {}
