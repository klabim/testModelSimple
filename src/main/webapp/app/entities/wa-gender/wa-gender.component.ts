import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IWaGender } from 'app/shared/model/wa-gender.model';
import { WaGenderService } from './wa-gender.service';
import { WaGenderDeleteDialogComponent } from './wa-gender-delete-dialog.component';

@Component({
  selector: 'jhi-wa-gender',
  templateUrl: './wa-gender.component.html'
})
export class WaGenderComponent implements OnInit, OnDestroy {
  waGenders?: IWaGender[];
  eventSubscriber?: Subscription;

  constructor(protected waGenderService: WaGenderService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.waGenderService.query().subscribe((res: HttpResponse<IWaGender[]>) => (this.waGenders = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInWaGenders();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IWaGender): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInWaGenders(): void {
    this.eventSubscriber = this.eventManager.subscribe('waGenderListModification', () => this.loadAll());
  }

  delete(waGender: IWaGender): void {
    const modalRef = this.modalService.open(WaGenderDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.waGender = waGender;
  }
}
