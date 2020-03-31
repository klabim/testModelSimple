import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaPersonalAddressComponent } from './wa-personal-address.component';
import { WaPersonalAddressDetailComponent } from './wa-personal-address-detail.component';
import { WaPersonalAddressUpdateComponent } from './wa-personal-address-update.component';
import { WaPersonalAddressDeleteDialogComponent } from './wa-personal-address-delete-dialog.component';
import { waPersonalAddressRoute } from './wa-personal-address.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waPersonalAddressRoute)],
  declarations: [
    WaPersonalAddressComponent,
    WaPersonalAddressDetailComponent,
    WaPersonalAddressUpdateComponent,
    WaPersonalAddressDeleteDialogComponent
  ],
  entryComponents: [WaPersonalAddressDeleteDialogComponent]
})
export class TestModelSimpleWaPersonalAddressModule {}
