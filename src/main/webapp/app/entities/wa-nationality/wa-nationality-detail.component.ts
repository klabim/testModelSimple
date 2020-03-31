import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaNationality } from 'app/shared/model/wa-nationality.model';

@Component({
  selector: 'jhi-wa-nationality-detail',
  templateUrl: './wa-nationality-detail.component.html'
})
export class WaNationalityDetailComponent implements OnInit {
  waNationality: IWaNationality | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waNationality }) => (this.waNationality = waNationality));
  }

  previousState(): void {
    window.history.back();
  }
}
