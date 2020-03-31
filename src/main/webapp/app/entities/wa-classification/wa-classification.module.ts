import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaClassificationComponent } from './wa-classification.component';
import { WaClassificationDetailComponent } from './wa-classification-detail.component';
import { WaClassificationUpdateComponent } from './wa-classification-update.component';
import { WaClassificationDeleteDialogComponent } from './wa-classification-delete-dialog.component';
import { waClassificationRoute } from './wa-classification.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waClassificationRoute)],
  declarations: [
    WaClassificationComponent,
    WaClassificationDetailComponent,
    WaClassificationUpdateComponent,
    WaClassificationDeleteDialogComponent
  ],
  entryComponents: [WaClassificationDeleteDialogComponent]
})
export class TestModelSimpleWaClassificationModule {}
