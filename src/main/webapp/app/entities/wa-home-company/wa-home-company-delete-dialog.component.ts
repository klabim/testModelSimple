import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaHomeCompany } from 'app/shared/model/wa-home-company.model';
import { WaHomeCompanyService } from './wa-home-company.service';

@Component({
  templateUrl: './wa-home-company-delete-dialog.component.html'
})
export class WaHomeCompanyDeleteDialogComponent {
  waHomeCompany?: IWaHomeCompany;

  constructor(
    protected waHomeCompanyService: WaHomeCompanyService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waHomeCompanyService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waHomeCompanyListModification');
      this.activeModal.close();
    });
  }
}
