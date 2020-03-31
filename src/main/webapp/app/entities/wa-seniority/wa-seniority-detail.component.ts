import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaSeniority } from 'app/shared/model/wa-seniority.model';

@Component({
  selector: 'jhi-wa-seniority-detail',
  templateUrl: './wa-seniority-detail.component.html'
})
export class WaSeniorityDetailComponent implements OnInit {
  waSeniority: IWaSeniority | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waSeniority }) => (this.waSeniority = waSeniority));
  }

  previousState(): void {
    window.history.back();
  }
}
