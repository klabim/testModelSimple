import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaHomeCompany, WaHomeCompany } from 'app/shared/model/wa-home-company.model';
import { WaHomeCompanyService } from './wa-home-company.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-home-company-update',
  templateUrl: './wa-home-company-update.component.html'
})
export class WaHomeCompanyUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    effectiveAssignmentDate: [null, [Validators.required]],
    sequence: [null, [Validators.required]],
    assignmentType: [null, [Validators.required]],
    assignmentTypeLabel: [],
    homeCompanyCode: [null, [Validators.required]],
    groupHomeCompanyCode: [],
    waEmployee: []
  });

  constructor(
    protected waHomeCompanyService: WaHomeCompanyService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waHomeCompany }) => {
      if (!waHomeCompany.id) {
        const today = moment().startOf('day');
        waHomeCompany.effectiveAssignmentDate = today;
      }

      this.updateForm(waHomeCompany);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waHomeCompany: IWaHomeCompany): void {
    this.editForm.patchValue({
      id: waHomeCompany.id,
      localIdNumber: waHomeCompany.localIdNumber,
      effectiveAssignmentDate: waHomeCompany.effectiveAssignmentDate
        ? waHomeCompany.effectiveAssignmentDate.format(DATE_TIME_FORMAT)
        : null,
      sequence: waHomeCompany.sequence,
      assignmentType: waHomeCompany.assignmentType,
      assignmentTypeLabel: waHomeCompany.assignmentTypeLabel,
      homeCompanyCode: waHomeCompany.homeCompanyCode,
      groupHomeCompanyCode: waHomeCompany.groupHomeCompanyCode,
      waEmployee: waHomeCompany.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waHomeCompany = this.createFromForm();
    if (waHomeCompany.id !== undefined) {
      this.subscribeToSaveResponse(this.waHomeCompanyService.update(waHomeCompany));
    } else {
      this.subscribeToSaveResponse(this.waHomeCompanyService.create(waHomeCompany));
    }
  }

  private createFromForm(): IWaHomeCompany {
    return {
      ...new WaHomeCompany(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      effectiveAssignmentDate: this.editForm.get(['effectiveAssignmentDate'])!.value
        ? moment(this.editForm.get(['effectiveAssignmentDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sequence: this.editForm.get(['sequence'])!.value,
      assignmentType: this.editForm.get(['assignmentType'])!.value,
      assignmentTypeLabel: this.editForm.get(['assignmentTypeLabel'])!.value,
      homeCompanyCode: this.editForm.get(['homeCompanyCode'])!.value,
      groupHomeCompanyCode: this.editForm.get(['groupHomeCompanyCode'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaHomeCompany>>): void {
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
