import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IManagerialLink } from 'app/shared/model/managerial-link.model';
import { ManagerialLinkService } from './managerial-link.service';
import { ManagerialLinkDeleteDialogComponent } from './managerial-link-delete-dialog.component';

@Component({
  selector: 'jhi-managerial-link',
  templateUrl: './managerial-link.component.html'
})
export class ManagerialLinkComponent implements OnInit, OnDestroy {
  managerialLinks?: IManagerialLink[];
  eventSubscriber?: Subscription;

  constructor(
    protected managerialLinkService: ManagerialLinkService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.managerialLinkService.query().subscribe((res: HttpResponse<IManagerialLink[]>) => (this.managerialLinks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInManagerialLinks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IManagerialLink): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInManagerialLinks(): void {
    this.eventSubscriber = this.eventManager.subscribe('managerialLinkListModification', () => this.loadAll());
  }

  delete(managerialLink: IManagerialLink): void {
    const modalRef = this.modalService.open(ManagerialLinkDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.managerialLink = managerialLink;
  }
}
