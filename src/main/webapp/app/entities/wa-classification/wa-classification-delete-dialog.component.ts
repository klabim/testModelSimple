import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaClassification } from 'app/shared/model/wa-classification.model';
import { WaClassificationService } from './wa-classification.service';

@Component({
  templateUrl: './wa-classification-delete-dialog.component.html'
})
export class WaClassificationDeleteDialogComponent {
  waClassification?: IWaClassification;

  constructor(
    protected waClassificationService: WaClassificationService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waClassificationService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waClassificationListModification');
      this.activeModal.close();
    });
  }
}
