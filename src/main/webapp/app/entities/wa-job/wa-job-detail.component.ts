import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaJob } from 'app/shared/model/wa-job.model';

@Component({
  selector: 'jhi-wa-job-detail',
  templateUrl: './wa-job-detail.component.html'
})
export class WaJobDetailComponent implements OnInit {
  waJob: IWaJob | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waJob }) => (this.waJob = waJob));
  }

  previousState(): void {
    window.history.back();
  }
}
