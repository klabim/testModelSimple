import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaMaritalStatus, WaMaritalStatus } from 'app/shared/model/wa-marital-status.model';
import { WaMaritalStatusService } from './wa-marital-status.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-marital-status-update',
  templateUrl: './wa-marital-status-update.component.html'
})
export class WaMaritalStatusUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    maritalStatusCode: [null, [Validators.required]],
    maritalStatusLabel: [],
    effectiveDate: [null, [Validators.required]],
    waEmployee: []
  });

  constructor(
    protected waMaritalStatusService: WaMaritalStatusService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waMaritalStatus }) => {
      if (!waMaritalStatus.id) {
        const today = moment().startOf('day');
        waMaritalStatus.effectiveDate = today;
      }

      this.updateForm(waMaritalStatus);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waMaritalStatus: IWaMaritalStatus): void {
    this.editForm.patchValue({
      id: waMaritalStatus.id,
      localIdNumber: waMaritalStatus.localIdNumber,
      maritalStatusCode: waMaritalStatus.maritalStatusCode,
      maritalStatusLabel: waMaritalStatus.maritalStatusLabel,
      effectiveDate: waMaritalStatus.effectiveDate ? waMaritalStatus.effectiveDate.format(DATE_TIME_FORMAT) : null,
      waEmployee: waMaritalStatus.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waMaritalStatus = this.createFromForm();
    if (waMaritalStatus.id !== undefined) {
      this.subscribeToSaveResponse(this.waMaritalStatusService.update(waMaritalStatus));
    } else {
      this.subscribeToSaveResponse(this.waMaritalStatusService.create(waMaritalStatus));
    }
  }

  private createFromForm(): IWaMaritalStatus {
    return {
      ...new WaMaritalStatus(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      maritalStatusCode: this.editForm.get(['maritalStatusCode'])!.value,
      maritalStatusLabel: this.editForm.get(['maritalStatusLabel'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaMaritalStatus>>): void {
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
