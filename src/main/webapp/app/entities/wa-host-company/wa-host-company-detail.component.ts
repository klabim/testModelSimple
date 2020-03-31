import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaHostCompany } from 'app/shared/model/wa-host-company.model';

@Component({
  selector: 'jhi-wa-host-company-detail',
  templateUrl: './wa-host-company-detail.component.html'
})
export class WaHostCompanyDetailComponent implements OnInit {
  waHostCompany: IWaHostCompany | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waHostCompany }) => (this.waHostCompany = waHostCompany));
  }

  previousState(): void {
    window.history.back();
  }
}
