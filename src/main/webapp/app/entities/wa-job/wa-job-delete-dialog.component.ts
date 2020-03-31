import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaJob } from 'app/shared/model/wa-job.model';
import { WaJobService } from './wa-job.service';

@Component({
  templateUrl: './wa-job-delete-dialog.component.html'
})
export class WaJobDeleteDialogComponent {
  waJob?: IWaJob;

  constructor(protected waJobService: WaJobService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waJobService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waJobListModification');
      this.activeModal.close();
    });
  }
}
