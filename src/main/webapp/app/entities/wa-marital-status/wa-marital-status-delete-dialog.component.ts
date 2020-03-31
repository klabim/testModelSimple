import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaMaritalStatus } from 'app/shared/model/wa-marital-status.model';
import { WaMaritalStatusService } from './wa-marital-status.service';

@Component({
  templateUrl: './wa-marital-status-delete-dialog.component.html'
})
export class WaMaritalStatusDeleteDialogComponent {
  waMaritalStatus?: IWaMaritalStatus;

  constructor(
    protected waMaritalStatusService: WaMaritalStatusService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waMaritalStatusService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waMaritalStatusListModification');
      this.activeModal.close();
    });
  }
}
