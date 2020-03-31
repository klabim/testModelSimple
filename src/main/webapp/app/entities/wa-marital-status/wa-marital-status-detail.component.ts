import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaMaritalStatus } from 'app/shared/model/wa-marital-status.model';

@Component({
  selector: 'jhi-wa-marital-status-detail',
  templateUrl: './wa-marital-status-detail.component.html'
})
export class WaMaritalStatusDetailComponent implements OnInit {
  waMaritalStatus: IWaMaritalStatus | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waMaritalStatus }) => (this.waMaritalStatus = waMaritalStatus));
  }

  previousState(): void {
    window.history.back();
  }
}
