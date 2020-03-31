import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaHomeCompany } from 'app/shared/model/wa-home-company.model';

@Component({
  selector: 'jhi-wa-home-company-detail',
  templateUrl: './wa-home-company-detail.component.html'
})
export class WaHomeCompanyDetailComponent implements OnInit {
  waHomeCompany: IWaHomeCompany | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waHomeCompany }) => (this.waHomeCompany = waHomeCompany));
  }

  previousState(): void {
    window.history.back();
  }
}
