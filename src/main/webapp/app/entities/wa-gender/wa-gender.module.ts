import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaGenderComponent } from './wa-gender.component';
import { WaGenderDetailComponent } from './wa-gender-detail.component';
import { WaGenderUpdateComponent } from './wa-gender-update.component';
import { WaGenderDeleteDialogComponent } from './wa-gender-delete-dialog.component';
import { waGenderRoute } from './wa-gender.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waGenderRoute)],
  declarations: [WaGenderComponent, WaGenderDetailComponent, WaGenderUpdateComponent, WaGenderDeleteDialogComponent],
  entryComponents: [WaGenderDeleteDialogComponent]
})
export class TestModelSimpleWaGenderModule {}
