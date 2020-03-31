import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaNationalityComponent } from './wa-nationality.component';
import { WaNationalityDetailComponent } from './wa-nationality-detail.component';
import { WaNationalityUpdateComponent } from './wa-nationality-update.component';
import { WaNationalityDeleteDialogComponent } from './wa-nationality-delete-dialog.component';
import { waNationalityRoute } from './wa-nationality.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waNationalityRoute)],
  declarations: [WaNationalityComponent, WaNationalityDetailComponent, WaNationalityUpdateComponent, WaNationalityDeleteDialogComponent],
  entryComponents: [WaNationalityDeleteDialogComponent]
})
export class TestModelSimpleWaNationalityModule {}
