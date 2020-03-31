import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaNationality } from 'app/shared/model/wa-nationality.model';
import { WaNationalityService } from './wa-nationality.service';
import { WaNationalityDeleteDialogComponent } from './wa-nationality-delete-dialog.component';

@Component({
  selector: 'jhi-wa-nationality',
  templateUrl: './wa-nationality.component.html'
})
export class WaNationalityComponent implements OnInit, OnDestroy {
  waNationalities?: IWaNationality[];
  eventSubscriber?: Subscription;

  constructor(
    protected waNationalityService: WaNationalityService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waNationalityService.query().subscribe((res: HttpResponse<IWaNationality[]>) => (this.waNationalities = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaNationalities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaNationality): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaNationalities(): void {
    this.eventSubscriber = this.eventManager.subscribe('waNationalityListModification', () => this.loadAll());
  }

  delete(waNationality: IWaNationality): void {
    const modalRef = this.modalService.open(WaNationalityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waNationality = waNationality;
  }
}
