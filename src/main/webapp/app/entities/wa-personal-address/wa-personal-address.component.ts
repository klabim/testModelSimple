import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaPersonalAddress } from 'app/shared/model/wa-personal-address.model';
import { WaPersonalAddressService } from './wa-personal-address.service';
import { WaPersonalAddressDeleteDialogComponent } from './wa-personal-address-delete-dialog.component';

@Component({
  selector: 'jhi-wa-personal-address',
  templateUrl: './wa-personal-address.component.html'
})
export class WaPersonalAddressComponent implements OnInit, OnDestroy {
  waPersonalAddresses?: IWaPersonalAddress[];
  eventSubscriber?: Subscription;

  constructor(
    protected waPersonalAddressService: WaPersonalAddressService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waPersonalAddressService
      .query()
      .subscribe((res: HttpResponse<IWaPersonalAddress[]>) => (this.waPersonalAddresses = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaPersonalAddresses();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaPersonalAddress): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaPersonalAddresses(): void {
    this.eventSubscriber = this.eventManager.subscribe('waPersonalAddressListModification', () => this.loadAll());
  }

  delete(waPersonalAddress: IWaPersonalAddress): void {
    const modalRef = this.modalService.open(WaPersonalAddressDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waPersonalAddress = waPersonalAddress;
  }
}
