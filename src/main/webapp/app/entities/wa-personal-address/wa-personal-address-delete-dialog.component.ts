import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaPersonalAddress } from 'app/shared/model/wa-personal-address.model';
import { WaPersonalAddressService } from './wa-personal-address.service';

@Component({
  templateUrl: './wa-personal-address-delete-dialog.component.html'
})
export class WaPersonalAddressDeleteDialogComponent {
  waPersonalAddress?: IWaPersonalAddress;

  constructor(
    protected waPersonalAddressService: WaPersonalAddressService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waPersonalAddressService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waPersonalAddressListModification');
      this.activeModal.close();
    });
  }
}
