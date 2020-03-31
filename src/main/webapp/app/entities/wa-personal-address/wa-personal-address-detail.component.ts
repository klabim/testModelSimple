import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaPersonalAddress } from 'app/shared/model/wa-personal-address.model';

@Component({
  selector: 'jhi-wa-personal-address-detail',
  templateUrl: './wa-personal-address-detail.component.html'
})
export class WaPersonalAddressDetailComponent implements OnInit {
  waPersonalAddress: IWaPersonalAddress | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waPersonalAddress }) => (this.waPersonalAddress = waPersonalAddress));
  }

  previousState(): void {
    window.history.back();
  }
}
