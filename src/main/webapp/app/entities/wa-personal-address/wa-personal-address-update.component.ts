import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IWaPersonalAddress, WaPersonalAddress } from 'app/shared/model/wa-personal-address.model';
import { WaPersonalAddressService } from './wa-personal-address.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-personal-address-update',
  templateUrl: './wa-personal-address-update.component.html'
})
export class WaPersonalAddressUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    personalAddressType: [null, [Validators.required]],
    additionalAddress1: [],
    additionalAddress2: [],
    physicalAddress: [],
    additionalPostalDelivery: [],
    zipCodePostalCode: [],
    employeeCityOfResidence: [],
    countryOfResidence: [],
    effectiveDate: [null, [Validators.required]],
    waEmployee: []
  });

  constructor(
    protected waPersonalAddressService: WaPersonalAddressService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waPersonalAddress }) => {
      if (!waPersonalAddress.id) {
        const today = moment().startOf('day');
        waPersonalAddress.effectiveDate = today;
      }

      this.updateForm(waPersonalAddress);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waPersonalAddress: IWaPersonalAddress): void {
    this.editForm.patchValue({
      id: waPersonalAddress.id,
      localIdNumber: waPersonalAddress.localIdNumber,
      personalAddressType: waPersonalAddress.personalAddressType,
      additionalAddress1: waPersonalAddress.additionalAddress1,
      additionalAddress2: waPersonalAddress.additionalAddress2,
      physicalAddress: waPersonalAddress.physicalAddress,
      additionalPostalDelivery: waPersonalAddress.additionalPostalDelivery,
      zipCodePostalCode: waPersonalAddress.zipCodePostalCode,
      employeeCityOfResidence: waPersonalAddress.employeeCityOfResidence,
      countryOfResidence: waPersonalAddress.countryOfResidence,
      effectiveDate: waPersonalAddress.effectiveDate ? waPersonalAddress.effectiveDate.format(DATE_TIME_FORMAT) : null,
      waEmployee: waPersonalAddress.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waPersonalAddress = this.createFromForm();
    if (waPersonalAddress.id !== undefined) {
      this.subscribeToSaveResponse(this.waPersonalAddressService.update(waPersonalAddress));
    } else {
      this.subscribeToSaveResponse(this.waPersonalAddressService.create(waPersonalAddress));
    }
  }

  private createFromForm(): IWaPersonalAddress {
    return {
      ...new WaPersonalAddress(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      personalAddressType: this.editForm.get(['personalAddressType'])!.value,
      additionalAddress1: this.editForm.get(['additionalAddress1'])!.value,
      additionalAddress2: this.editForm.get(['additionalAddress2'])!.value,
      physicalAddress: this.editForm.get(['physicalAddress'])!.value,
      additionalPostalDelivery: this.editForm.get(['additionalPostalDelivery'])!.value,
      zipCodePostalCode: this.editForm.get(['zipCodePostalCode'])!.value,
      employeeCityOfResidence: this.editForm.get(['employeeCityOfResidence'])!.value,
      countryOfResidence: this.editForm.get(['countryOfResidence'])!.value,
      effectiveDate: this.editForm.get(['effectiveDate'])!.value
        ? moment(this.editForm.get(['effectiveDate'])!.value, DATE_TIME_FORMAT)
        : undefined,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaPersonalAddress>>): void {
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
