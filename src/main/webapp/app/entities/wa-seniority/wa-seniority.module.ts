import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaSeniorityComponent } from './wa-seniority.component';
import { WaSeniorityDetailComponent } from './wa-seniority-detail.component';
import { WaSeniorityUpdateComponent } from './wa-seniority-update.component';
import { WaSeniorityDeleteDialogComponent } from './wa-seniority-delete-dialog.component';
import { waSeniorityRoute } from './wa-seniority.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waSeniorityRoute)],
  declarations: [WaSeniorityComponent, WaSeniorityDetailComponent, WaSeniorityUpdateComponent, WaSeniorityDeleteDialogComponent],
  entryComponents: [WaSeniorityDeleteDialogComponent]
})
export class TestModelSimpleWaSeniorityModule {}
