import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWaGender, WaGender } from 'app/shared/model/wa-gender.model';
import { WaGenderService } from './wa-gender.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-gender-update',
  templateUrl: './wa-gender-update.component.html'
})
export class WaGenderUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    employeeLocalGender: [null, [Validators.required]],
    employeeGender: [],
    waEmployee: []
  });

  constructor(
    protected waGenderService: WaGenderService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waGender }) => {
      this.updateForm(waGender);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waGender: IWaGender): void {
    this.editForm.patchValue({
      id: waGender.id,
      localIdNumber: waGender.localIdNumber,
      employeeLocalGender: waGender.employeeLocalGender,
      employeeGender: waGender.employeeGender,
      waEmployee: waGender.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waGender = this.createFromForm();
    if (waGender.id !== undefined) {
      this.subscribeToSaveResponse(this.waGenderService.update(waGender));
    } else {
      this.subscribeToSaveResponse(this.waGenderService.create(waGender));
    }
  }

  private createFromForm(): IWaGender {
    return {
      ...new WaGender(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      employeeLocalGender: this.editForm.get(['employeeLocalGender'])!.value,
      employeeGender: this.editForm.get(['employeeGender'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaGender>>): void {
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
