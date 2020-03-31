import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaHostCompany } from 'app/shared/model/wa-host-company.model';
import { WaHostCompanyService } from './wa-host-company.service';

@Component({
  templateUrl: './wa-host-company-delete-dialog.component.html'
})
export class WaHostCompanyDeleteDialogComponent {
  waHostCompany?: IWaHostCompany;

  constructor(
    protected waHostCompanyService: WaHostCompanyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waHostCompanyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waHostCompanyListModification');
      this.activeModal.close();
    });
  }
}
