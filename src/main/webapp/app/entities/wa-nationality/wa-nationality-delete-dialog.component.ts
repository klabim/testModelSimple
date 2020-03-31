import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaNationality } from 'app/shared/model/wa-nationality.model';
import { WaNationalityService } from './wa-nationality.service';

@Component({
  templateUrl: './wa-nationality-delete-dialog.component.html'
})
export class WaNationalityDeleteDialogComponent {
  waNationality?: IWaNationality;

  constructor(
    protected waNationalityService: WaNationalityService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waNationalityService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waNationalityListModification');
      this.activeModal.close();
    });
  }
}
