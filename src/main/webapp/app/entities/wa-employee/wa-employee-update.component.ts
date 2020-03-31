import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaEmployee, WaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from './wa-employee.service';

@Component({
  selector: 'jhi-wa-employee-update',
  templateUrl: './wa-employee-update.component.html'
})
export class WaEmployeeUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    ggi: [],
    homeHostIndicator: [],
    legalGroupCompanyCode: [],
    prefix: [],
    latinBirthName: [],
    localBirthName: [],
    latinCommonName: [],
    localCommonName: [],
    latinNameComplement: [],
    localNameComplement: [],
    latinFirstName: [],
    localFirstName: [],
    employeeBirthDate: [],
    countryOfBirth: [],
    birthDepartment: [],
    localIdNumber: [null, [Validators.required]],
    sensitiveEmployeeFlag: [],
    waEmployee: []
  });

  constructor(protected waEmployeeService: WaEmployeeService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waEmployee }) => {
      if (!waEmployee.id) {
        const today = moment().startOf('day');
        waEmployee.employeeBirthDate = today;
      }

      this.updateForm(waEmployee);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waEmployee: IWaEmployee): void {
    this.editForm.patchValue({
      id: waEmployee.id,
      ggi: waEmployee.ggi,
      homeHostIndicator: waEmployee.homeHostIndicator,
      legalGroupCompanyCode: waEmployee.legalGroupCompanyCode,
      prefix: waEmployee.prefix,
      latinBirthName: waEmployee.latinBirthName,
      localBirthName: waEmployee.localBirthName,
      latinCommonName: waEmployee.latinCommonName,
      localCommonName: waEmployee.localCommonName,
      latinNameComplement: waEmployee.latinNameComplement,
      localNameComplement: waEmployee.localNameComplement,
      latinFirstName: waEmployee.latinFirstName,
      localFirstName: waEmployee.localFirstName,
      employeeBirthDate: waEmployee.employeeBirthDate ? waEmployee.employeeBirthDate.format(DATE_TIME_FORMAT) : null,
      countryOfBirth: waEmployee.countryOfBirth,
      birthDepartment: waEmployee.birthDepartment,
      localIdNumber: waEmployee.localIdNumber,
      sensitiveEmployeeFlag: waEmployee.sensitiveEmployeeFlag,
      waEmployee: waEmployee.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waEmployee = this.createFromForm();
    if (waEmployee.id !== undefined) {
      this.subscribeToSaveResponse(this.waEmployeeService.update(waEmployee));
    } else {
      this.subscribeToSaveResponse(this.waEmployeeService.create(waEmployee));
    }
  }

  private createFromForm(): IWaEmployee {
    return {
      ...new WaEmployee(),
      id: this.editForm.get(['id'])!.value,
      ggi: this.editForm.get(['ggi'])!.value,
      homeHostIndicator: this.editForm.get(['homeHostIndicator'])!.value,
      legalGroupCompanyCode: this.editForm.get(['legalGroupCompanyCode'])!.value,
      prefix: this.editForm.get(['prefix'])!.value,
      latinBirthName: this.editForm.get(['latinBirthName'])!.value,
      localBirthName: this.editForm.get(['localBirthName'])!.value,
      latinCommonName: this.editForm.get(['latinCommonName'])!.value,
      localCommonName: this.editForm.get(['localCommonName'])!.value,
      latinNameComplement: this.editForm.get(['latinNameComplement'])!.value,
      localNameComplement: this.editForm.get(['localNameComplement'])!.value,
      latinFirstName: this.editForm.get(['latinFirstName'])!.value,
      localFirstName: this.editForm.get(['localFirstName'])!.value,
      employeeBirthDate: this.editForm.get(['employeeBirthDate'])!.value
        ? moment(this.editForm.get(['employeeBirthDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      countryOfBirth: this.editForm.get(['countryOfBirth'])!.value,
      birthDepartment: this.editForm.get(['birthDepartment'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      sensitiveEmployeeFlag: this.editForm.get(['sensitiveEmployeeFlag'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaEmployee>>): void {
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
