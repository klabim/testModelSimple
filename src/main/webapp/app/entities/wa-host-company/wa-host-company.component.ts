import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaHostCompany } from 'app/shared/model/wa-host-company.model';
import { WaHostCompanyService } from './wa-host-company.service';
import { WaHostCompanyDeleteDialogComponent } from './wa-host-company-delete-dialog.component';

@Component({
  selector: 'jhi-wa-host-company',
  templateUrl: './wa-host-company.component.html'
})
export class WaHostCompanyComponent implements OnInit, OnDestroy {
  waHostCompanies?: IWaHostCompany[];
  eventSubscriber?: Subscription;

  constructor(
    protected waHostCompanyService: WaHostCompanyService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waHostCompanyService.query().subscribe((res: HttpResponse<IWaHostCompany[]>) => (this.waHostCompanies = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaHostCompanies();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaHostCompany): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaHostCompanies(): void {
    this.eventSubscriber = this.eventManager.subscribe('waHostCompanyListModification', () => this.loadAll());
  }

  delete(waHostCompany: IWaHostCompany): void {
    const modalRef = this.modalService.open(WaHostCompanyDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waHostCompany = waHostCompany;
  }
}
