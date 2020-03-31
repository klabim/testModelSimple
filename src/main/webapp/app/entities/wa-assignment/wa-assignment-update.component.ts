import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaAssignment, WaAssignment } from 'app/shared/model/wa-assignment.model';
import { WaAssignmentService } from './wa-assignment.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-assignment-update',
  templateUrl: './wa-assignment-update.component.html'
})
export class WaAssignmentUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    effectiveDate: [null, [Validators.required]],
    sequence: [null, [Validators.required]],
    localCompanyCode: [],
    legalGroupCompanyCode: [],
    establishment: [],
    poleActivityGroupCode: [],
    groupSubPoleActivityCode: [],
    groupSubPoleActivityTitle: [],
    budgetaryAffectationDepartmentCode: [],
    budgetaryAffectationDepartmentLabel: [],
    budgetFunctionalIdSakkarah: [],
    administrativeDepartmentLocalCode: [],
    administrativeDepartmentLocalLabel: [],
    adminFunctionalIdSakkarah: [],
    detachmentGroupCompany: [],
    detachmentLegalGroupCompany: [],
    waEmployee: []
  });

  constructor(
    protected waAssignmentService: WaAssignmentService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waAssignment }) => {
      if (!waAssignment.id) {
        const today = moment().startOf('day');
        waAssignment.effectiveDate = today;
      }

      this.updateForm(waAssignment);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waAssignment: IWaAssignment): void {
    this.editForm.patchValue({
      id: waAssignment.id,
      localIdNumber: waAssignment.localIdNumber,
      effectiveDate: waAssignment.effectiveDate ? waAssignment.effectiveDate.format(DATE_TIME_FORMAT) : null,
      sequence: waAssignment.sequence,
      localCompanyCode: waAssignment.localCompanyCode,
      legalGroupCompanyCode: waAssignment.legalGroupCompanyCode,
      establishment: waAssignment.establishment,
      poleActivityGroupCode: waAssignment.poleActivityGroupCode,
      groupSubPoleActivityCode: waAssignment.groupSubPoleActivityCode,
      groupSubPoleActivityTitle: waAssignment.groupSubPoleActivityTitle,
      budgetaryAffectationDepartmentCode: waAssignment.budgetaryAffectationDepartmentCode,
      budgetaryAffectationDepartmentLabel: waAssignment.budgetaryAffectationDepartmentLabel,
      budgetFunctionalIdSakkarah: waAssignment.budgetFunctionalIdSakkarah,
      administrativeDepartmentLocalCode: waAssignment.administrativeDepartmentLocalCode,
      administrativeDepartmentLocalLabel: waAssignment.administrativeDepartmentLocalLabel,
      adminFunctionalIdSakkarah: waAssignment.adminFunctionalIdSakkarah,
      detachmentGroupCompany: waAssignment.detachmentGroupCompany,
      detachmentLegalGroupCompany: waAssignment.detachmentLegalGroupCompany,
      waEmployee: waAssignment.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waAssignment = this.createFromForm();
    if (waAssignment.id !== undefined) {
      this.subscribeToSaveResponse(this.waAssignmentService.update(waAssignment));
    } else {
      this.subscribeToSaveResponse(this.waAssignmentService.create(waAssignment));
    }
  }

  private createFromForm(): IWaAssignment {
    return {
      ...new WaAssignment(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sequence: this.editForm.get(['sequence'])!.value,
      localCompanyCode: this.editForm.get(['localCompanyCode'])!.value,
      legalGroupCompanyCode: this.editForm.get(['legalGroupCompanyCode'])!.value,
      establishment: this.editForm.get(['establishment'])!.value,
      poleActivityGroupCode: this.editForm.get(['poleActivityGroupCode'])!.value,
      groupSubPoleActivityCode: this.editForm.get(['groupSubPoleActivityCode'])!.value,
      groupSubPoleActivityTitle: this.editForm.get(['groupSubPoleActivityTitle'])!.value,
      budgetaryAffectationDepartmentCode: this.editForm.get(['budgetaryAffectationDepartmentCode'])!.value,
      budgetaryAffectationDepartmentLabel: this.editForm.get(['budgetaryAffectationDepartmentLabel'])!.value,
      budgetFunctionalIdSakkarah: this.editForm.get(['budgetFunctionalIdSakkarah'])!.value,
      administrativeDepartmentLocalCode: this.editForm.get(['administrativeDepartmentLocalCode'])!.value,
      administrativeDepartmentLocalLabel: this.editForm.get(['administrativeDepartmentLocalLabel'])!.value,
      adminFunctionalIdSakkarah: this.editForm.get(['adminFunctionalIdSakkarah'])!.value,
      detachmentGroupCompany: this.editForm.get(['detachmentGroupCompany'])!.value,
      detachmentLegalGroupCompany: this.editForm.get(['detachmentLegalGroupCompany'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaAssignment>>): void {
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
