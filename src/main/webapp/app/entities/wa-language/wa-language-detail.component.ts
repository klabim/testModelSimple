import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaLanguage } from 'app/shared/model/wa-language.model';

@Component({
  selector: 'jhi-wa-language-detail',
  templateUrl: './wa-language-detail.component.html'
})
export class WaLanguageDetailComponent implements OnInit {
  waLanguage: IWaLanguage | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waLanguage }) => (this.waLanguage = waLanguage));
  }

  previousState(): void {
    window.history.back();
  }
}
