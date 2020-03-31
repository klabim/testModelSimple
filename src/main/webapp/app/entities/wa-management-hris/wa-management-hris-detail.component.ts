import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaManagementHris } from 'app/shared/model/wa-management-hris.model';

@Component({
  selector: 'jhi-wa-management-hris-detail',
  templateUrl: './wa-management-hris-detail.component.html'
})
export class WaManagementHrisDetailComponent implements OnInit {
  waManagementHris: IWaManagementHris | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waManagementHris }) => (this.waManagementHris = waManagementHris));
  }

  previousState(): void {
    window.history.back();
  }
}
