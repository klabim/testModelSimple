import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaManagementHris } from 'app/shared/model/wa-management-hris.model';
import { WaManagementHrisService } from './wa-management-hris.service';
import { WaManagementHrisDeleteDialogComponent } from './wa-management-hris-delete-dialog.component';

@Component({
  selector: 'jhi-wa-management-hris',
  templateUrl: './wa-management-hris.component.html'
})
export class WaManagementHrisComponent implements OnInit, OnDestroy {
  waManagementHrises?: IWaManagementHris[];
  eventSubscriber?: Subscription;

  constructor(
    protected waManagementHrisService: WaManagementHrisService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waManagementHrisService.query().subscribe((res: HttpResponse<IWaManagementHris[]>) => (this.waManagementHrises = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaManagementHrises();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaManagementHris): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaManagementHrises(): void {
    this.eventSubscriber = this.eventManager.subscribe('waManagementHrisListModification', () => this.loadAll());
  }

  delete(waManagementHris: IWaManagementHris): void {
    const modalRef = this.modalService.open(WaManagementHrisDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waManagementHris = waManagementHris;
  }
}
