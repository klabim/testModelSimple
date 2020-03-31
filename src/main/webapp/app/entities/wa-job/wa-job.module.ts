import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaJobComponent } from './wa-job.component';
import { WaJobDetailComponent } from './wa-job-detail.component';
import { WaJobUpdateComponent } from './wa-job-update.component';
import { WaJobDeleteDialogComponent } from './wa-job-delete-dialog.component';
import { waJobRoute } from './wa-job.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waJobRoute)],
  declarations: [WaJobComponent, WaJobDetailComponent, WaJobUpdateComponent, WaJobDeleteDialogComponent],
  entryComponents: [WaJobDeleteDialogComponent]
})
export class TestModelSimpleWaJobModule {}
