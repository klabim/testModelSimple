import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IFixedCompensation } from 'app/shared/model/fixed-compensation.model';

@Component({
  selector: 'jhi-fixed-compensation-detail',
  templateUrl: './fixed-compensation-detail.component.html'
})
export class FixedCompensationDetailComponent implements OnInit {
  fixedCompensation: IFixedCompensation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fixedCompensation }) => (this.fixedCompensation = fixedCompensation));
  }

  previousState(): void {
    window.history.back();
  }
}
