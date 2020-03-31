import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IWaLanguage, WaLanguage } from 'app/shared/model/wa-language.model';
import { WaLanguageService } from './wa-language.service';
import { IWaEmployee } from 'app/shared/model/wa-employee.model';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';

@Component({
  selector: 'jhi-wa-language-update',
  templateUrl: './wa-language-update.component.html'
})
export class WaLanguageUpdateComponent implements OnInit {
  isSaving = false;
  waemployees: IWaEmployee[] = [];

  editForm = this.fb.group({
    id: [],
    localIdNumber: [],
    languageCode: [null, [Validators.required]],
    languageLabel: [],
    languageSpeakProficiencyCode: [],
    languageSpeakProficiencyLabel: [],
    languageReadProficiencyCode: [],
    languageReadProficiencyLabel: [],
    languageWriteProficiencyCode: [],
    languageWriteProficiencyLabel: [],
    nativeLanguage: [],
    languageLevelLocalCode: [],
    languageLevelLocalLabel: [],
    languageLevelGroupCode: [],
    languageLevelGroupLabel: [],
    waEmployee: []
  });

  constructor(
    protected waLanguageService: WaLanguageService,
    protected waEmployeeService: WaEmployeeService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waLanguage }) => {
      this.updateForm(waLanguage);

      this.waEmployeeService.query().subscribe((res: HttpResponse<IWaEmployee[]>) => (this.waemployees = res.body || []));
    });
  }

  updateForm(waLanguage: IWaLanguage): void {
    this.editForm.patchValue({
      id: waLanguage.id,
      localIdNumber: waLanguage.localIdNumber,
      languageCode: waLanguage.languageCode,
      languageLabel: waLanguage.languageLabel,
      languageSpeakProficiencyCode: waLanguage.languageSpeakProficiencyCode,
      languageSpeakProficiencyLabel: waLanguage.languageSpeakProficiencyLabel,
      languageReadProficiencyCode: waLanguage.languageReadProficiencyCode,
      languageReadProficiencyLabel: waLanguage.languageReadProficiencyLabel,
      languageWriteProficiencyCode: waLanguage.languageWriteProficiencyCode,
      languageWriteProficiencyLabel: waLanguage.languageWriteProficiencyLabel,
      nativeLanguage: waLanguage.nativeLanguage,
      languageLevelLocalCode: waLanguage.languageLevelLocalCode,
      languageLevelLocalLabel: waLanguage.languageLevelLocalLabel,
      languageLevelGroupCode: waLanguage.languageLevelGroupCode,
      languageLevelGroupLabel: waLanguage.languageLevelGroupLabel,
      waEmployee: waLanguage.waEmployee
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const waLanguage = this.createFromForm();
    if (waLanguage.id !== undefined) {
      this.subscribeToSaveResponse(this.waLanguageService.update(waLanguage));
    } else {
      this.subscribeToSaveResponse(this.waLanguageService.create(waLanguage));
    }
  }

  private createFromForm(): IWaLanguage {
    return {
      ...new WaLanguage(),
      id: this.editForm.get(['id'])!.value,
      localIdNumber: this.editForm.get(['localIdNumber'])!.value,
      languageCode: this.editForm.get(['languageCode'])!.value,
      languageLabel: this.editForm.get(['languageLabel'])!.value,
      languageSpeakProficiencyCode: this.editForm.get(['languageSpeakProficiencyCode'])!.value,
      languageSpeakProficiencyLabel: this.editForm.get(['languageSpeakProficiencyLabel'])!.value,
      languageReadProficiencyCode: this.editForm.get(['languageReadProficiencyCode'])!.value,
      languageReadProficiencyLabel: this.editForm.get(['languageReadProficiencyLabel'])!.value,
      languageWriteProficiencyCode: this.editForm.get(['languageWriteProficiencyCode'])!.value,
      languageWriteProficiencyLabel: this.editForm.get(['languageWriteProficiencyLabel'])!.value,
      nativeLanguage: this.editForm.get(['nativeLanguage'])!.value,
      languageLevelLocalCode: this.editForm.get(['languageLevelLocalCode'])!.value,
      languageLevelLocalLabel: this.editForm.get(['languageLevelLocalLabel'])!.value,
      languageLevelGroupCode: this.editForm.get(['languageLevelGroupCode'])!.value,
      languageLevelGroupLabel: this.editForm.get(['languageLevelGroupLabel'])!.value,
      waEmployee: this.editForm.get(['waEmployee'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IWaLanguage>>): void {
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
