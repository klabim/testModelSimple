import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaJob, WaJob } from 'app/shared/model/wa-job.model';
import { WaJobService } from './wa-job.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-job-update',
  templateUrl: './wa-job-update.component.html'
})
export class WaJobUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    effectiveDate: [null, [Validators.required]],
    sequence: [null, [Validators.required]],
    localPositionCode: [null, [Validators.required]],
    localPositionLevel: [],
    positionEntryDate: [],
    jobCodeLocalValue: [],
    jobCodeGroupeValue: [],
    jobEntryDate: [],
    localCollectiveAgreementCode: [],
    localCollectiveAgreementLabel: [],
    orgRelationship: [],
    prescripteur: [],
    waEmployee: []
  });

  constructor(
    protected waJobService: WaJobService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waJob }) => {
      if (!waJob.id) {
        const today = moment().startOf('day');
        waJob.effectiveDate = today;
        waJob.positionEntryDate = today;
        waJob.jobEntryDate = today;
      }

      this.updateForm(waJob);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waJob: IWaJob): void {
    this.editForm.patchValue({
      id: waJob.id,
      localIdNumber: waJob.localIdNumber,
      effectiveDate: waJob.effectiveDate ? waJob.effectiveDate.format(DATE_TIME_FORMAT) : null,
      sequence: waJob.sequence,
      localPositionCode: waJob.localPositionCode,
      localPositionLevel: waJob.localPositionLevel,
      positionEntryDate: waJob.positionEntryDate ? waJob.positionEntryDate.format(DATE_TIME_FORMAT) : null,
      jobCodeLocalValue: waJob.jobCodeLocalValue,
      jobCodeGroupeValue: waJob.jobCodeGroupeValue,
      jobEntryDate: waJob.jobEntryDate ? waJob.jobEntryDate.format(DATE_TIME_FORMAT) : null,
      localCollectiveAgreementCode: waJob.localCollectiveAgreementCode,
      localCollectiveAgreementLabel: waJob.localCollectiveAgreementLabel,
      orgRelationship: waJob.orgRelationship,
      prescripteur: waJob.prescripteur,
      waEmployee: waJob.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waJob = this.createFromForm();
    if (waJob.id !== undefined) {
      this.subscribeToSaveResponse(this.waJobService.update(waJob));
    } else {
      this.subscribeToSaveResponse(this.waJobService.create(waJob));
    }
  }

  private createFromForm(): IWaJob {
    return {
      ...new WaJob(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      sequence: this.editForm.get(['sequence'])!.value,
      localPositionCode: this.editForm.get(['localPositionCode'])!.value,
      localPositionLevel: this.editForm.get(['localPositionLevel'])!.value,
      positionEntryDate: this.editForm.get(['positionEntryDate'])!.value
        ? moment(this.editForm.get(['positionEntryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      jobCodeLocalValue: this.editForm.get(['jobCodeLocalValue'])!.value,
      jobCodeGroupeValue: this.editForm.get(['jobCodeGroupeValue'])!.value,
      jobEntryDate: this.editForm.get(['jobEntryDate'])!.value
        ? moment(this.editForm.get(['jobEntryDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      localCollectiveAgreementCode: this.editForm.get(['localCollectiveAgreementCode'])!.value,
      localCollectiveAgreementLabel: this.editForm.get(['localCollectiveAgreementLabel'])!.value,
      orgRelationship: this.editForm.get(['orgRelationship'])!.value,
      prescripteur: this.editForm.get(['prescripteur'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaJob>>): void {
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
