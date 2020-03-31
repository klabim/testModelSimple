import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaSeniority } from 'app/shared/model/wa-seniority.model';
import { WaSeniorityService } from './wa-seniority.service';

@Component({
  templateUrl: './wa-seniority-delete-dialog.component.html'
})
export class WaSeniorityDeleteDialogComponent {
  waSeniority?: IWaSeniority;

  constructor(
    protected waSeniorityService: WaSeniorityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waSeniorityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waSeniorityListModification');
      this.activeModal.close();
    });
  }
}
