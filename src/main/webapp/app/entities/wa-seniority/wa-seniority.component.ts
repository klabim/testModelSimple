import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaSeniority } from 'app/shared/model/wa-seniority.model';
import { WaSeniorityService } from './wa-seniority.service';
import { WaSeniorityDeleteDialogComponent } from './wa-seniority-delete-dialog.component';

@Component({
  selector: 'jhi-wa-seniority',
  templateUrl: './wa-seniority.component.html'
})
export class WaSeniorityComponent implements OnInit, OnDestroy {
  waSeniorities?: IWaSeniority[];
  eventSubscriber?: Subscription;

  constructor(
    protected waSeniorityService: WaSeniorityService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waSeniorityService.query().subscribe((res: HttpResponse<IWaSeniority[]>) => (this.waSeniorities = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaSeniorities();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaSeniority): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaSeniorities(): void {
    this.eventSubscriber = this.eventManager.subscribe('waSeniorityListModification', () => this.loadAll());
  }

  delete(waSeniority: IWaSeniority): void {
    const modalRef = this.modalService.open(WaSeniorityDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waSeniority = waSeniority;
  }
}
