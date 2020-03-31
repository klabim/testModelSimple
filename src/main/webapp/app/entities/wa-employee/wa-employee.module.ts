import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaEmployeeComponent } from './wa-employee.component';
import { WaEmployeeDetailComponent } from './wa-employee-detail.component';
import { WaEmployeeUpdateComponent } from './wa-employee-update.component';
import { WaEmployeeDeleteDialogComponent } from './wa-employee-delete-dialog.component';
import { waEmployeeRoute } from './wa-employee.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waEmployeeRoute)],
  declarations: [WaEmployeeComponent, WaEmployeeDetailComponent, WaEmployeeUpdateComponent, WaEmployeeDeleteDialogComponent],
  entryComponents: [WaEmployeeDeleteDialogComponent]
})
export class TestModelSimpleWaEmployeeModule {}
