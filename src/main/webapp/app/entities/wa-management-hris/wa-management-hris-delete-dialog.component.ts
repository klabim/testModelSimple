import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaManagementHris } from 'app/shared/model/wa-management-hris.model';
import { WaManagementHrisService } from './wa-management-hris.service';

@Component({
  templateUrl: './wa-management-hris-delete-dialog.component.html'
})
export class WaManagementHrisDeleteDialogComponent {
  waManagementHris?: IWaManagementHris;

  constructor(
    protected waManagementHrisService: WaManagementHrisService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waManagementHrisService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waManagementHrisListModification');
      this.activeModal.close();
    });
  }
}
