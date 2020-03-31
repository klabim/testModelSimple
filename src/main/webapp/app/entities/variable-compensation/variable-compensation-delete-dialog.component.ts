import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IVariableCompensation } from 'app/shared/model/variable-compensation.model';
import { VariableCompensationService } from './variable-compensation.service';

@Component({
  templateUrl: './variable-compensation-delete-dialog.component.html'
})
export class VariableCompensationDeleteDialogComponent {
  variableCompensation?: IVariableCompensation;

  constructor(
    protected variableCompensationService: VariableCompensationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.variableCompensationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('variableCompensationListModification');
      this.activeModal.close();
    });
  }
}
