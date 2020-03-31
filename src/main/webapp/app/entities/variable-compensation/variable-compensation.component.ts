import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IVariableCompensation } from 'app/shared/model/variable-compensation.model';
import { VariableCompensationService } from './variable-compensation.service';
import { VariableCompensationDeleteDialogComponent } from './variable-compensation-delete-dialog.component';

@Component({
  selector: 'jhi-variable-compensation',
  templateUrl: './variable-compensation.component.html'
})
export class VariableCompensationComponent implements OnInit, OnDestroy {
  variableCompensations?: IVariableCompensation[];
  eventSubscriber?: Subscription;

  constructor(
    protected variableCompensationService: VariableCompensationService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.variableCompensationService
      .query()
      .subscribe((res: HttpResponse<IVariableCompensation[]>) => (this.variableCompensations = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInVariableCompensations();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVariableCompensation): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVariableCompensations(): void {
    this.eventSubscriber = this.eventManager.subscribe('variableCompensationListModification', () => this.loadAll());
  }

  delete(variableCompensation: IVariableCompensation): void {
    const modalRef = this.modalService.open(VariableCompensationDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.variableCompensation = variableCompensation;
  }
}
