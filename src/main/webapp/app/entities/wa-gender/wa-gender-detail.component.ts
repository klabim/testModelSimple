import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaGender } from 'app/shared/model/wa-gender.model';

@Component({
  selector: 'jhi-wa-gender-detail',
  templateUrl: './wa-gender-detail.component.html'
})
export class WaGenderDetailComponent implements OnInit {
  waGender: IWaGender | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waGender }) => (this.waGender = waGender));
  }

  previousState(): void {
    window.history.back();
  }
}
