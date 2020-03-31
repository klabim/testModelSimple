import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaAssignmentComponent } from './wa-assignment.component';
import { WaAssignmentDetailComponent } from './wa-assignment-detail.component';
import { WaAssignmentUpdateComponent } from './wa-assignment-update.component';
import { WaAssignmentDeleteDialogComponent } from './wa-assignment-delete-dialog.component';
import { waAssignmentRoute } from './wa-assignment.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waAssignmentRoute)],
  declarations: [WaAssignmentComponent, WaAssignmentDetailComponent, WaAssignmentUpdateComponent, WaAssignmentDeleteDialogComponent],
  entryComponents: [WaAssignmentDeleteDialogComponent]
})
export class TestModelSimpleWaAssignmentModule {}
