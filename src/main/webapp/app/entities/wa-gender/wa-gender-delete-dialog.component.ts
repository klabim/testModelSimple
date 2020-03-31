import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaGender } from 'app/shared/model/wa-gender.model';
import { WaGenderService } from './wa-gender.service';

@Component({
  templateUrl: './wa-gender-delete-dialog.component.html'
})
export class WaGenderDeleteDialogComponent {
  waGender?: IWaGender;

  constructor(protected waGenderService: WaGenderService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waGenderService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waGenderListModification');
      this.activeModal.close();
    });
  }
}
