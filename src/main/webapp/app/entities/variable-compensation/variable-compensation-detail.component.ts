import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IVariableCompensation } from 'app/shared/model/variable-compensation.model';

@Component({
  selector: 'jhi-variable-compensation-detail',
  templateUrl: './variable-compensation-detail.component.html'
})
export class VariableCompensationDetailComponent implements OnInit {
  variableCompensation: IVariableCompensation | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ variableCompensation }) => (this.variableCompensation = variableCompensation));
  }

  previousState(): void {
    window.history.back();
  }
}
