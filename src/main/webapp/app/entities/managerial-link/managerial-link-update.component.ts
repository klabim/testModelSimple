import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IManagerialLink, ManagerialLink } from 'app/shared/model/managerial-link.model';
import { ManagerialLinkService } from './managerial-link.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-managerial-link-update',
  templateUrl: './managerial-link-update.component.html'
})
export class ManagerialLinkUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    managerialLinkType: [],
    localIdNumber: [null, [Validators.required]],
    localIDDirectManager: [null, [Validators.required]],
    ggiDirectLineManager: [],
    managerLastName: [],
    managerFirstName: [],
    waEmployee: []
  });

  constructor(
    protected managerialLinkService: ManagerialLinkService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ managerialLink }) => {
      this.updateForm(managerialLink);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(managerialLink: IManagerialLink): void {
    this.editForm.patchValue({
      id: managerialLink.id,
      managerialLinkType: managerialLink.managerialLinkType,
      localIdNumber: managerialLink.localIdNumber,
      localIDDirectManager: managerialLink.localIDDirectManager,
      ggiDirectLineManager: managerialLink.ggiDirectLineManager,
      managerLastName: managerialLink.managerLastName,
      managerFirstName: managerialLink.managerFirstName,
      waEmployee: managerialLink.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const managerialLink = this.createFromForm();
    if (managerialLink.id !== undefined) {
      this.subscribeToSaveResponse(this.managerialLinkService.update(managerialLink));
    } else {
      this.subscribeToSaveResponse(this.managerialLinkService.create(managerialLink));
    }
  }

  private createFromForm(): IManagerialLink {
    return {
      ...new ManagerialLink(),
      id: this.editForm.get(['id'])!.value,
      managerialLinkType: this.editForm.get(['managerialLinkType'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      localIDDirectManager: this.editForm.get(['localIDDirectManager'])!.value,
      ggiDirectLineManager: this.editForm.get(['ggiDirectLineManager'])!.value,
      managerLastName: this.editForm.get(['managerLastName'])!.value,
      managerFirstName: this.editForm.get(['managerFirstName'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IManagerialLink>>): void {
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
