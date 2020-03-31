import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IVariableCompensation, VariableCompensation } from 'app/shared/model/variable-compensation.model';
import { VariableCompensationService } from './variable-compensation.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-variable-compensation-update',
  templateUrl: './variable-compensation-update.component.html'
})
export class VariableCompensationUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    variableCompensationTypeGroupCode: [null, [Validators.required]],
    variableCompensationTypeGroupLabel: [],
    effectiveBeginningDate: [null, [Validators.required]],
    variableCompensationCurrency: [],
    variableCompensationLocalTypeCode: [],
    variableCompensationLocalTypeLabel: [],
    variableCompensationAmount: [],
    effectiveEndDate: [],
    waEmployee: []
  });

  constructor(
    protected variableCompensationService: VariableCompensationService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ variableCompensation }) => {
      if (!variableCompensation.id) {
        const today = moment().startOf('day');
        variableCompensation.effectiveBeginningDate = today;
        variableCompensation.effectiveEndDate = today;
      }

      this.updateForm(variableCompensation);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(variableCompensation: IVariableCompensation): void {
    this.editForm.patchValue({
      id: variableCompensation.id,
      localIdNumber: variableCompensation.localIdNumber,
      variableCompensationTypeGroupCode: variableCompensation.variableCompensationTypeGroupCode,
      variableCompensationTypeGroupLabel: variableCompensation.variableCompensationTypeGroupLabel,
      effectiveBeginningDate: variableCompensation.effectiveBeginningDate
        ? variableCompensation.effectiveBeginningDate.format(DATE_TIME_FORMAT)
        : null,
      variableCompensationCurrency: variableCompensation.variableCompensationCurrency,
      variableCompensationLocalTypeCode: variableCompensation.variableCompensationLocalTypeCode,
      variableCompensationLocalTypeLabel: variableCompensation.variableCompensationLocalTypeLabel,
      variableCompensationAmount: variableCompensation.variableCompensationAmount,
      effectiveEndDate: variableCompensation.effectiveEndDate ? variableCompensation.effectiveEndDate.format(DATE_TIME_FORMAT) : null,
      waEmployee: variableCompensation.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const variableCompensation = this.createFromForm();
    if (variableCompensation.id !== undefined) {
      this.subscribeToSaveResponse(this.variableCompensationService.update(variableCompensation));
    } else {
      this.subscribeToSaveResponse(this.variableCompensationService.create(variableCompensation));
    }
  }

  private createFromForm(): IVariableCompensation {
    return {
      ...new VariableCompensation(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      variableCompensationTypeGroupCode: this.editForm.get(['variableCompensationTypeGroupCode'])!.value,
      variableCompensationTypeGroupLabel: this.editForm.get(['variableCompensationTypeGroupLabel'])!.value,
      effectiveBeginningDate: this.editForm.get(['effectiveBeginningDate'])!.value
        ? moment(this.editForm.get(['effectiveBeginningDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      variableCompensationCurrency: this.editForm.get(['variableCompensationCurrency'])!.value,
      variableCompensationLocalTypeCode: this.editForm.get(['variableCompensationLocalTypeCode'])!.value,
      variableCompensationLocalTypeLabel: this.editForm.get(['variableCompensationLocalTypeLabel'])!.value,
      variableCompensationAmount: this.editForm.get(['variableCompensationAmount'])!.value,
      effectiveEndDate: this.editForm.get(['effectiveEndDate'])!.value
        ? moment(this.editForm.get(['effectiveEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVariableCompensation>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IWaEmployee): any {
    return item.id;
  }
}
