import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from './wa-employee.service';
import { WaEmployeeDeleteDialogComponent } from './wa-employee-delete-dialog.component';

@Component({
  selector: 'jhi-wa-employee',
  templateUrl: './wa-employee.component.html'
})
export class WaEmployeeComponent implements OnInit, OnDestroy {
  waEmployees?: IWaEmployee[];
  eventSubscriber?: Subscription;

  constructor(protected waEmployeeService: WaEmployeeService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waEmployees = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaEmployees();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaEmployee): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaEmployees(): void {
    this.eventSubscriber = this.eventManager.subscribe('waEmployeeListModification', () => this.loadAll());
  }

  delete(waEmployee: IWaEmployee): void {
    const modalRef = this.modalService.open(WaEmployeeDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waEmployee = waEmployee;
  }
}
