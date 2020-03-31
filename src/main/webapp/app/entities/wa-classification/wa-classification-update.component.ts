import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaClassification, WaClassification } from 'app/shared/model/wa-classification.model';
import { WaClassificationService } from './wa-classification.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-classification-update',
  templateUrl: './wa-classification-update.component.html'
})
export class WaClassificationUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    effectiveDate: [null, [Validators.required]],
    sequence: [null, [Validators.required]],
    localClassificationCode: [null, [Validators.required]],
    localClassificationLabel: [],
    groupClassificationCode: [],
    groupClassificationLabel: [],
    professionalCategoryCode: [],
    professionalCategoryLabel: [],
    waEmployee: []
  });

  constructor(
    protected waClassificationService: WaClassificationService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waClassification }) => {
      if (!waClassification.id) {
        const today = moment().startOf('day');
        waClassification.effectiveDate = today;
      }

      this.updateForm(waClassification);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waClassification: IWaClassification): void {
    this.editForm.patchValue({
      id: waClassification.id,
      localIdNumber: waClassification.localIdNumber,
      effectiveDate: waClassification.effectiveDate ? waClassification.effectiveDate.format(DATE_TIME_FORMAT) : null,
      sequence: waClassification.sequence,
      localClassificationCode: waClassification.localClassificationCode,
      localClassificationLabel: waClassification.localClassificationLabel,
      groupClassificationCode: waClassification.groupClassificationCode,
      groupClassificationLabel: waClassification.groupClassificationLabel,
      professionalCategoryCode: waClassification.professionalCategoryCode,
      professionalCategoryLabel: waClassification.professionalCategoryLabel,
      waEmployee: waClassification.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waClassification = this.createFromForm();
    if (waClassification.id !== undefined) {
      this.subscribeToSaveResponse(this.waClassificationService.update(waClassification));
    } else {
      this.subscribeToSaveResponse(this.waClassificationService.create(waClassification));
    }
  }

  private createFromForm(): IWaClassification {
    return {
      ...new WaClassification(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sequence: this.editForm.get(['sequence'])!.value,
      localClassificationCode: this.editForm.get(['localClassificationCode'])!.value,
      localClassificationLabel: this.editForm.get(['localClassificationLabel'])!.value,
      groupClassificationCode: this.editForm.get(['groupClassificationCode'])!.value,
      groupClassificationLabel: this.editForm.get(['groupClassificationLabel'])!.value,
      professionalCategoryCode: this.editForm.get(['professionalCategoryCode'])!.value,
      professionalCategoryLabel: this.editForm.get(['professionalCategoryLabel'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaClassification>>): void {
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
