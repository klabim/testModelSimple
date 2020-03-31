import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaAssignment } from 'app/shared/model/wa-assignment.model';
import { WaAssignmentService } from './wa-assignment.service';
import { WaAssignmentDeleteDialogComponent } from './wa-assignment-delete-dialog.component';

@Component({
  selector: 'jhi-wa-assignment',
  templateUrl: './wa-assignment.component.html'
})
export class WaAssignmentComponent implements OnInit, OnDestroy {
  waAssignments?: IWaAssignment[];
  eventSubscriber?: Subscription;

  constructor(
    protected waAssignmentService: WaAssignmentService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.waAssignmentService.query().subscribe((res: HttpResponse<IWaAssignment[]>) => (this.waAssignments = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaAssignments();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaAssignment): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaAssignments(): void {
    this.eventSubscriber = this.eventManager.subscribe('waAssignmentListModification', () => this.loadAll());
  }

  delete(waAssignment: IWaAssignment): void {
    const modalRef = this.modalService.open(WaAssignmentDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waAssignment = waAssignment;
  }
}
