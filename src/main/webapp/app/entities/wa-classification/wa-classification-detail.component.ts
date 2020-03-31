import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaClassification } from 'app/shared/model/wa-classification.model';

@Component({
  selector: 'jhi-wa-classification-detail',
  templateUrl: './wa-classification-detail.component.html'
})
export class WaClassificationDetailComponent implements OnInit {
  waClassification: IWaClassification | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waClassification }) => (this.waClassification = waClassification));
  }

  previousState(): void {
    window.history.back();
  }
}
