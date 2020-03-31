import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaJob } from 'app/shared/model/wa-job.model';
import { WaJobService } from './wa-job.service';
import { WaJobDeleteDialogComponent } from './wa-job-delete-dialog.component';

@Component({
  selector: 'jhi-wa-job',
  templateUrl: './wa-job.component.html'
})
export class WaJobComponent implements OnInit, OnDestroy {
  waJobs?: IWaJob[];
  eventSubscriber?: Subscription;

  constructor(protected waJobService: WaJobService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.waJobService.query().subscribe((res: HttpResponse<IWaJob[]>) => (this.waJobs = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaJobs();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaJob): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaJobs(): void {
    this.eventSubscriber = this.eventManager.subscribe('waJobListModification', () => this.loadAll());
  }

  delete(waJob: IWaJob): void {
    const modalRef = this.modalService.open(WaJobDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waJob = waJob;
  }
}
