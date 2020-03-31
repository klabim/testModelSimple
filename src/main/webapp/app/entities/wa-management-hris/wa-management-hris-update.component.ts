import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWaManagementHris, WaManagementHris } from 'app/shared/model/wa-management-hris.model';
import { WaManagementHrisService } from './wa-management-hris.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-management-hris-update',
  templateUrl: './wa-management-hris-update.component.html'
})
export class WaManagementHrisUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    managementHrisCode: [],
    localIdNumber: [null, [Validators.required]],
    managementHrisLabel: [],
    entityManagementCode: [],
    waEmployee: []
  });

  constructor(
    protected waManagementHrisService: WaManagementHrisService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waManagementHris }) => {
      this.updateForm(waManagementHris);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waManagementHris: IWaManagementHris): void {
    this.editForm.patchValue({
      id: waManagementHris.id,
      managementHrisCode: waManagementHris.managementHrisCode,
      localIdNumber: waManagementHris.localIdNumber,
      managementHrisLabel: waManagementHris.managementHrisLabel,
      entityManagementCode: waManagementHris.entityManagementCode,
      waEmployee: waManagementHris.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waManagementHris = this.createFromForm();
    if (waManagementHris.id !== undefined) {
      this.subscribeToSaveResponse(this.waManagementHrisService.update(waManagementHris));
    } else {
      this.subscribeToSaveResponse(this.waManagementHrisService.create(waManagementHris));
    }
  }

  private createFromForm(): IWaManagementHris {
    return {
      ...new WaManagementHris(),
      id: this.editForm.get(['id'])!.value,
      managementHrisCode: this.editForm.get(['managementHrisCode'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      managementHrisLabel: this.editForm.get(['managementHrisLabel'])!.value,
      entityManagementCode: this.editForm.get(['entityManagementCode'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaManagementHris>>): void {
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
