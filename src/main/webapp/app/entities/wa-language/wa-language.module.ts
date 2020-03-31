import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { WaLanguageComponent } from './wa-language.component';
import { WaLanguageDetailComponent } from './wa-language-detail.component';
import { WaLanguageUpdateComponent } from './wa-language-update.component';
import { WaLanguageDeleteDialogComponent } from './wa-language-delete-dialog.component';
import { waLanguageRoute } from './wa-language.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(waLanguageRoute)],
  declarations: [WaLanguageComponent, WaLanguageDetailComponent, WaLanguageUpdateComponent, WaLanguageDeleteDialogComponent],
  entryComponents: [WaLanguageDeleteDialogComponent]
})
export class TestModelSimpleWaLanguageModule {}
