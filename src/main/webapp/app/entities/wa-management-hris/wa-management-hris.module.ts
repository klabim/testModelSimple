import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaManagementHrisComponent } from './wa-management-hris.component';
import { WaManagementHrisDetailComponent } from './wa-management-hris-detail.component';
import { WaManagementHrisUpdateComponent } from './wa-management-hris-update.component';
import { WaManagementHrisDeleteDialogComponent } from './wa-management-hris-delete-dialog.component';
import { waManagementHrisRoute } from './wa-management-hris.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waManagementHrisRoute)],
  declarations: [
    WaManagementHrisComponent,
    WaManagementHrisDetailComponent,
    WaManagementHrisUpdateComponent,
    WaManagementHrisDeleteDialogComponent
  ],
  entryComponents: [WaManagementHrisDeleteDialogComponent]
})
export class TestModelSimpleWaManagementHrisModule {}
