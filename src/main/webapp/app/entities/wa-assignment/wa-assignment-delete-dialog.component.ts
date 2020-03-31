import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaAssignment } from 'app/shared/model/wa-assignment.model';
import { WaAssignmentService } from './wa-assignment.service';

@Component({
  templateUrl: './wa-assignment-delete-dialog.component.html'
})
export class WaAssignmentDeleteDialogComponent {
  waAssignment?: IWaAssignment;

  constructor(
    protected waAssignmentService: WaAssignmentService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waAssignmentService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waAssignmentListModification');
      this.activeModal.close();
    });
  }
}
