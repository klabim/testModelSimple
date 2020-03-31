import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IManagerialLink } from 'app/shared/model/managerial-link.model';
import { ManagerialLinkService } from './managerial-link.service';

@Component({
  templateUrl: './managerial-link-delete-dialog.component.html'
})
export class ManagerialLinkDeleteDialogComponent {
  managerialLink?: IManagerialLink;

  constructor(
    protected managerialLinkService: ManagerialLinkService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.managerialLinkService.delete(id).subscribe(() => {
      this.eventManager.broadcast('managerialLinkListModification');
      this.activeModal.close();
    });
  }
}
