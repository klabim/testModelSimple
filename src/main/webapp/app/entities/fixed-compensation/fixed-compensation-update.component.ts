import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFixedCompensation, FixedCompensation } from 'app/shared/model/fixed-compensation.model';
import { FixedCompensationService } from './fixed-compensation.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-fixed-compensation-update',
  templateUrl: './fixed-compensation-update.component.html'
})
export class FixedCompensationUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    fixedCompensationTypeGroupCode: [null, [Validators.required]],
    fixedCompensationTypeGroupLabel: [],
    effectiveBeginningDate: [null, [Validators.required]],
    fixedCompensationCurrency: [],
    fixedCompensationLocalTypeCode: [],
    fixedCompensationLocalTypeLabel: [],
    fixedCompensationAmount: [],
    compensationFrequency: [],
    effectiveEndDate: [],
    fixedCompensationEffectiveSequence: [],
    waEmployee: []
  });

  constructor(
    protected fixedCompensationService: FixedCompensationService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ fixedCompensation }) => {
      if (!fixedCompensation.id) {
        const today = moment().startOf('day');
        fixedCompensation.effectiveBeginningDate = today;
        fixedCompensation.effectiveEndDate = today;
      }

      this.updateForm(fixedCompensation);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(fixedCompensation: IFixedCompensation): void {
    this.editForm.patchValue({
      id: fixedCompensation.id,
      localIdNumber: fixedCompensation.localIdNumber,
      fixedCompensationTypeGroupCode: fixedCompensation.fixedCompensationTypeGroupCode,
      fixedCompensationTypeGroupLabel: fixedCompensation.fixedCompensationTypeGroupLabel,
      effectiveBeginningDate: fixedCompensation.effectiveBeginningDate
        ? fixedCompensation.effectiveBeginningDate.format(DATE_TIME_FORMAT)
        : null,
      fixedCompensationCurrency: fixedCompensation.fixedCompensationCurrency,
      fixedCompensationLocalTypeCode: fixedCompensation.fixedCompensationLocalTypeCode,
      fixedCompensationLocalTypeLabel: fixedCompensation.fixedCompensationLocalTypeLabel,
      fixedCompensationAmount: fixedCompensation.fixedCompensationAmount,
      compensationFrequency: fixedCompensation.compensationFrequency,
      effectiveEndDate: fixedCompensation.effectiveEndDate ? fixedCompensation.effectiveEndDate.format(DATE_TIME_FORMAT) : null,
      fixedCompensationEffectiveSequence: fixedCompensation.fixedCompensationEffectiveSequence,
      waEmployee: fixedCompensation.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const fixedCompensation = this.createFromForm();
    if (fixedCompensation.id !== undefined) {
      this.subscribeToSaveResponse(this.fixedCompensationService.update(fixedCompensation));
    } else {
      this.subscribeToSaveResponse(this.fixedCompensationService.create(fixedCompensation));
    }
  }

  private createFromForm(): IFixedCompensation {
    return {
      ...new FixedCompensation(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      fixedCompensationTypeGroupCode: this.editForm.get(['fixedCompensationTypeGroupCode'])!.value,
      fixedCompensationTypeGroupLabel: this.editForm.get(['fixedCompensationTypeGroupLabel'])!.value,
      effectiveBeginningDate: this.editForm.get(['effectiveBeginningDate'])!.value
        ? moment(this.editForm.get(['effectiveBeginningDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fixedCompensationCurrency: this.editForm.get(['fixedCompensationCurrency'])!.value,
      fixedCompensationLocalTypeCode: this.editForm.get(['fixedCompensationLocalTypeCode'])!.value,
      fixedCompensationLocalTypeLabel: this.editForm.get(['fixedCompensationLocalTypeLabel'])!.value,
      fixedCompensationAmount: this.editForm.get(['fixedCompensationAmount'])!.value,
      compensationFrequency: this.editForm.get(['compensationFrequency'])!.value,
      effectiveEndDate: this.editForm.get(['effectiveEndDate'])!.value
        ? moment(this.editForm.get(['effectiveEndDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      fixedCompensationEffectiveSequence: this.editForm.get(['fixedCompensationEffectiveSequence'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFixedCompensation>>): void {
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
