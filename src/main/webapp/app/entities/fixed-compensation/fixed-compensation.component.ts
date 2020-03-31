import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IFixedCompensation } from 'app/shared/model/fixed-compensation.model';
import { FixedCompensationService } from './fixed-compensation.service';
import { FixedCompensationDeleteDialogComponent } from './fixed-compensation-delete-dialog.component';

@Component({
  selector: 'jhi-fixed-compensation',
  templateUrl: './fixed-compensation.component.html'
})
export class FixedCompensationComponent implements OnInit, OnDestroy {
  fixedCompensations?: IFixedCompensation[];
  eventSubscriber?: Subscription;

  constructor(
    protected fixedCompensationService: FixedCompensationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.fixedCompensationService
      .query()
      .subscribe((res: HttpResponse<IFixedCompensation[]>) => (this.fixedCompensations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInFixedCompensations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IFixedCompensation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInFixedCompensations(): void {
    this.eventSubscriber = this.eventManager.subscribe('fixedCompensationListModification', () => this.loadAll());
  }

  delete(fixedCompensation: IFixedCompensation): void {
    const modalRef = this.modalService.open(FixedCompensationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.fixedCompensation = fixedCompensation;
  }
}
