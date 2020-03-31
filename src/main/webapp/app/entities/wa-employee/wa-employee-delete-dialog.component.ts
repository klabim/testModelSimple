import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from './wa-employee.service';

@Component({
  templateUrl: './wa-employee-delete-dialog.component.html'
})
export class WaEmployeeDeleteDialogComponent {
  waEmployee?: IWaEmployee;

  constructor(
    protected waEmployeeService: WaEmployeeService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waEmployeeService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waEmployeeListModification');
      this.activeModal.close();
    });
  }
}
