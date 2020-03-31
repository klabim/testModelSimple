import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaClassification } from 'app/shared/model/wa-classification.model';
import { WaClassificationService } from './wa-classification.service';
import { WaClassificationDeleteDialogComponent } from './wa-classification-delete-dialog.component';

@Component({
  selector: 'jhi-wa-classification',
  templateUrl: './wa-classification.component.html'
})
export class WaClassificationComponent implements OnInit, OnDestroy {
  waClassifications?: IWaClassification[];
  eventSubscriber?: Subscription;

  constructor(
    protected waClassificationService: WaClassificationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waClassificationService.query().subscribe((res: HttpResponse<IWaClassification[]>) => (this.waClassifications = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaClassifications();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaClassification): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaClassifications(): void {
    this.eventSubscriber = this.eventManager.subscribe('waClassificationListModification', () => this.loadAll());
  }

  delete(waClassification: IWaClassification): void {
    const modalRef = this.modalService.open(WaClassificationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waClassification = waClassification;
  }
}
