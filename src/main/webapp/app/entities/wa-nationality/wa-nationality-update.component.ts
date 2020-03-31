import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaNationality, WaNationality } from 'app/shared/model/wa-nationality.model';
import { WaNationalityService } from './wa-nationality.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-nationality-update',
  templateUrl: './wa-nationality-update.component.html'
})
export class WaNationalityUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    nationalityCountryCode: [null, [Validators.required]],
    effectiveDate: [null, [Validators.required]],
    waEmployee: []
  });

  constructor(
    protected waNationalityService: WaNationalityService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waNationality }) => {
      if (!waNationality.id) {
        const today = moment().startOf('day');
        waNationality.effectiveDate = today;
      }

      this.updateForm(waNationality);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waNationality: IWaNationality): void {
    this.editForm.patchValue({
      id: waNationality.id,
      localIdNumber: waNationality.localIdNumber,
      nationalityCountryCode: waNationality.nationalityCountryCode,
      effectiveDate: waNationality.effectiveDate ? waNationality.effectiveDate.format(DATE_TIME_FORMAT) : null,
      waEmployee: waNationality.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waNationality = this.createFromForm();
    if (waNationality.id !== undefined) {
      this.subscribeToSaveResponse(this.waNationalityService.update(waNationality));
    } else {
      this.subscribeToSaveResponse(this.waNationalityService.create(waNationality));
    }
  }

  private createFromForm(): IWaNationality {
    return {
      ...new WaNationality(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      nationalityCountryCode: this.editForm.get(['nationalityCountryCode'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaNationality>>): void {
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
