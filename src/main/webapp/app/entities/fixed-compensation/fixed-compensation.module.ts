import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { FixedCompensationComponent } from './fixed-compensation.component';
import { FixedCompensationDetailComponent } from './fixed-compensation-detail.component';
import { FixedCompensationUpdateComponent } from './fixed-compensation-update.component';
import { FixedCompensationDeleteDialogComponent } from './fixed-compensation-delete-dialog.component';
import { fixedCompensationRoute } from './fixed-compensation.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(fixedCompensationRoute)],
  declarations: [
    FixedCompensationComponent,
    FixedCompensationDetailComponent,
    FixedCompensationUpdateComponent,
    FixedCompensationDeleteDialogComponent
  ],
  entryComponents: [FixedCompensationDeleteDialogComponent]
})
export class TestModelSimpleFixedCompensationModule {}
