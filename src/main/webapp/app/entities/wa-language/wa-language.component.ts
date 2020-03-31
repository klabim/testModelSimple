import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaLanguage } from 'app/shared/model/wa-language.model';
import { WaLanguageService } from './wa-language.service';
import { WaLanguageDeleteDialogComponent } from './wa-language-delete-dialog.component';

@Component({
  selector: 'jhi-wa-language',
  templateUrl: './wa-language.component.html'
})
export class WaLanguageComponent implements OnInit, OnDestroy {
  waLanguages?: IWaLanguage[];
  eventSubscriber?: Subscription;

  constructor(protected waLanguageService: WaLanguageService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.waLanguageService.query().subscribe((res: HttpResponse<IWaLanguage[]>) => (this.waLanguages = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaLanguages();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaLanguage): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaLanguages(): void {
    this.eventSubscriber = this.eventManager.subscribe('waLanguageListModification', () => this.loadAll());
  }

  delete(waLanguage: IWaLanguage): void {
    const modalRef = this.modalService.open(WaLanguageDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waLanguage = waLanguage;
  }
}
