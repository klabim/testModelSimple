import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IFixedCompensation } from 'app/shared/model/fixed-compensation.model';
import { FixedCompensationService } from './fixed-compensation.service';

@Component({
  templateUrl: './fixed-compensation-delete-dialog.component.html'
})
export class FixedCompensationDeleteDialogComponent {
  fixedCompensation?: IFixedCompensation;

  constructor(
    protected fixedCompensationService: FixedCompensationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.fixedCompensationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('fixedCompensationListModification');
      this.activeModal.close();
    });
  }
}
