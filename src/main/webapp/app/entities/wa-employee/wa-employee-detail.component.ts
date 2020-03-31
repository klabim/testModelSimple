import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaEmployee } from 'app/shared/model/wa-employee.model';

@Component({
  selector: 'jhi-wa-employee-detail',
  templateUrl: './wa-employee-detail.component.html'
})
export class WaEmployeeDetailComponent implements OnInit {
  waEmployee: IWaEmployee | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waEmployee }) => (this.waEmployee = waEmployee));
  }

  previousState(): void {
    window.history.back();
  }
}
