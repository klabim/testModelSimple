import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaHomeCompany } from 'app/shared/model/wa-home-company.model';
import { WaHomeCompanyService } from './wa-home-company.service';
import { WaHomeCompanyDeleteDialogComponent } from './wa-home-company-delete-dialog.component';

@Component({
  selector: 'jhi-wa-home-company',
  templateUrl: './wa-home-company.component.html'
})
export class WaHomeCompanyComponent implements OnInit, OnDestroy {
  waHomeCompanies?: IWaHomeCompany[];
  eventSubscriber?: Subscription;

  constructor(
    protected waHomeCompanyService: WaHomeCompanyService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waHomeCompanyService.query().subscribe((res: HttpResponse<IWaHomeCompany[]>) => (this.waHomeCompanies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaHomeCompanies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaHomeCompany): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaHomeCompanies(): void {
    this.eventSubscriber = this.eventManager.subscribe('waHomeCompanyListModification', () => this.loadAll());
  }

  delete(waHomeCompany: IWaHomeCompany): void {
    const modalRef = this.modalService.open(WaHomeCompanyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waHomeCompany = waHomeCompany;
  }
}
