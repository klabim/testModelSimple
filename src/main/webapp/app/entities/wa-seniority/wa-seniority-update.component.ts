import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaSeniority, WaSeniority } from 'app/shared/model/wa-seniority.model';
import { WaSeniorityService } from './wa-seniority.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-seniority-update',
  templateUrl: './wa-seniority-update.component.html'
})
export class WaSeniorityUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    groupSgDateOfEntry: [null, [Validators.required]],
    sgSeniorityDate: [null, [Validators.required]],
    hireDate: [null, [Validators.required]],
    bankingSectorSeniority: [],
    endingOfTrialPeriodEstimatedDate: [],
    waEmployee: []
  });

  constructor(
    protected waSeniorityService: WaSeniorityService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waSeniority }) => {
      if (!waSeniority.id) {
        const today = moment().startOf('day');
        waSeniority.groupSgDateOfEntry = today;
        waSeniority.sgSeniorityDate = today;
        waSeniority.hireDate = today;
        waSeniority.endingOfTrialPeriodEstimatedDate = today;
      }

      this.updateForm(waSeniority);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waSeniority: IWaSeniority): void {
    this.editForm.patchValue({
      id: waSeniority.id,
      localIdNumber: waSeniority.localIdNumber,
      groupSgDateOfEntry: waSeniority.groupSgDateOfEntry ? waSeniority.groupSgDateOfEntry.format(DATE_TIME_FORMAT) : null,
      sgSeniorityDate: waSeniority.sgSeniorityDate ? waSeniority.sgSeniorityDate.format(DATE_TIME_FORMAT) : null,
      hireDate: waSeniority.hireDate ? waSeniority.hireDate.format(DATE_TIME_FORMAT) : null,
      bankingSectorSeniority: waSeniority.bankingSectorSeniority,
      endingOfTrialPeriodEstimatedDate: waSeniority.endingOfTrialPeriodEstimatedDate
        ? waSeniority.endingOfTrialPeriodEstimatedDate.format(DATE_TIME_FORMAT)
        : null,
      waEmployee: waSeniority.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waSeniority = this.createFromForm();
    if (waSeniority.id !== undefined) {
      this.subscribeToSaveResponse(this.waSeniorityService.update(waSeniority));
    } else {
      this.subscribeToSaveResponse(this.waSeniorityService.create(waSeniority));
    }
  }

  private createFromForm(): IWaSeniority {
    return {
      ...new WaSeniority(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      groupSgDateOfEntry: this.editForm.get(['groupSgDateOfEntry'])!.value
        ? moment(this.editForm.get(['groupSgDateOfEntry'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sgSeniorityDate: this.editForm.get(['sgSeniorityDate'])!.value
        ? moment(this.editForm.get(['sgSeniorityDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      hireDate: this.editForm.get(['hireDate'])!.value ? moment(this.editForm.get(['hireDate'])!.value, DATE_TIME_FORMAT) : undefined,
      bankingSectorSeniority: this.editForm.get(['bankingSectorSeniority'])!.value,
      endingOfTrialPeriodEstimatedDate: this.editForm.get(['endingOfTrialPeriodEstimatedDate'])!.value
        ? moment(this.editForm.get(['endingOfTrialPeriodEstimatedDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaSeniority>>): void {
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
