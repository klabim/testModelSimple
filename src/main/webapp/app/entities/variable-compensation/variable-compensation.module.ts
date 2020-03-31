import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { VariableCompensationComponent } from './variable-compensation.component';
import { VariableCompensationDetailComponent } from './variable-compensation-detail.component';
import { VariableCompensationUpdateComponent } from './variable-compensation-update.component';
import { VariableCompensationDeleteDialogComponent } from './variable-compensation-delete-dialog.component';
import { variableCompensationRoute } from './variable-compensation.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(variableCompensationRoute)],
  declarations: [
    VariableCompensationComponent,
    VariableCompensationDetailComponent,
    VariableCompensationUpdateComponent,
    VariableCompensationDeleteDialogComponent
  ],
  entryComponents: [VariableCompensationDeleteDialogComponent]
})
export class TestModelSimpleVariableCompensationModule {}
