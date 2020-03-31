import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaMaritalStatus } from 'app/shared/model/wa-marital-status.model';
import { WaMaritalStatusService } from './wa-marital-status.service';
import { WaMaritalStatusDeleteDialogComponent } from './wa-marital-status-delete-dialog.component';

@Component({
  selector: 'jhi-wa-marital-status',
  templateUrl: './wa-marital-status.component.html'
})
export class WaMaritalStatusComponent implements OnInit, OnDestroy {
  waMaritalStatuses?: IWaMaritalStatus[];
  eventSubscriber?: Subscription;

  constructor(
    protected waMaritalStatusService: WaMaritalStatusService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waMaritalStatusService.query().subscribe((res: HttpResponse<IWaMaritalStatus[]>) => (this.waMaritalStatuses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaMaritalStatuses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaMaritalStatus): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaMaritalStatuses(): void {
    this.eventSubscriber = this.eventManager.subscribe('waMaritalStatusListModification', () => this.loadAll());
  }

  delete(waMaritalStatus: IWaMaritalStatus): void {
    const modalRef = this.modalService.open(WaMaritalStatusDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waMaritalStatus = waMaritalStatus;
  }
}
