import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { TestModelSimpleSharedModule } from 'app/shared/shared.module';
import { ManagerialLinkComponent } from './managerial-link.component';
import { ManagerialLinkDetailComponent } from './managerial-link-detail.component';
import { ManagerialLinkUpdateComponent } from './managerial-link-update.component';
import { ManagerialLinkDeleteDialogComponent } from './managerial-link-delete-dialog.component';
import { managerialLinkRoute } from './managerial-link.route';

@NgModule({
  imports: [TestModelSimpleSharedModule, RouterModule.forChild(managerialLinkRoute)],
  declarations: [
    ManagerialLinkComponent,
    ManagerialLinkDetailComponent,
    ManagerialLinkUpdateComponent,
    ManagerialLinkDeleteDialogComponent
  ],
  entryComponents: [ManagerialLinkDeleteDialogComponent]
})
export class TestModelSimpleManagerialLinkModule {}
