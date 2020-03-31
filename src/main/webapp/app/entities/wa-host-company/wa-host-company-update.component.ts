import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaHostCompany, WaHostCompany } from 'app/shared/model/wa-host-company.model';
import { WaHostCompanyService } from './wa-host-company.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-host-company-update',
  templateUrl: './wa-host-company-update.component.html'
})
export class WaHostCompanyUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    effectiveDate: [null, [Validators.required]],
    sequence: [null, [Validators.required]],
    assignmentType: [null, [Validators.required]],
    assignmentTypeLabel: [],
    hostCompanyCode: [null, [Validators.required]],
    groupHostCompanyCode: [],
    waEmployee: []
  });

  constructor(
    protected waHostCompanyService: WaHostCompanyService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waHostCompany }) => {
      if (!waHostCompany.id) {
        const today = moment().startOf('day');
        waHostCompany.effectiveDate = today;
      }

      this.updateForm(waHostCompany);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waHostCompany: IWaHostCompany): void {
    this.editForm.patchValue({
      id: waHostCompany.id,
      localIdNumber: waHostCompany.localIdNumber,
      effectiveDate: waHostCompany.effectiveDate ? waHostCompany.effectiveDate.format(DATE_TIME_FORMAT) : null,
      sequence: waHostCompany.sequence,
      assignmentType: waHostCompany.assignmentType,
      assignmentTypeLabel: waHostCompany.assignmentTypeLabel,
      hostCompanyCode: waHostCompany.hostCompanyCode,
      groupHostCompanyCode: waHostCompany.groupHostCompanyCode,
      waEmployee: waHostCompany.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waHostCompany = this.createFromForm();
    if (waHostCompany.id !== undefined) {
      this.subscribeToSaveResponse(this.waHostCompanyService.update(waHostCompany));
    } else {
      this.subscribeToSaveResponse(this.waHostCompanyService.create(waHostCompany));
    }
  }

  private createFromForm(): IWaHostCompany {
    return {
      ...new WaHostCompany(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sequence: this.editForm.get(['sequence'])!.value,
      assignmentType: this.editForm.get(['assignmentType'])!.value,
      assignmentTypeLabel: this.editForm.get(['assignmentTypeLabel'])!.value,
      hostCompanyCode: this.editForm.get(['hostCompanyCode'])!.value,
      groupHostCompanyCode: this.editForm.get(['groupHostCompanyCode'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaHostCompany>>): void {
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
