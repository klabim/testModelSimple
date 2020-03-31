import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IWaLanguage } from 'app/shared/model/wa-language.model';
import { WaLanguageService } from './wa-language.service';

@Component({
  templateUrl: './wa-language-delete-dialog.component.html'
})
export class WaLanguageDeleteDialogComponent {
  waLanguage?: IWaLanguage;

  constructor(
    protected waLanguageService: WaLanguageService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.waLanguageService.delete(id).subscribe(() => {
      this.eventManager.broadcast('waLanguageListModification');
      this.activeModal.close();
    });
  }
}
