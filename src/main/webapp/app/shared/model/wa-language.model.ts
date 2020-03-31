import { IWaEmployee } from 'app/shared/model/wa-employee.model';

export interface IWaLanguage {
  id?: number;
  localIdNumber?: string;
  languageCode?: string;
  languageLabel?: string;
  languageSpeakProficiencyCode?: string;
  languageSpeakProficiencyLabel?: string;
  languageReadProficiencyCode?: string;
  languageReadProficiencyLabel?: string;
  languageWriteProficiencyCode?: string;
  languageWriteProficiencyLabel?: string;
  nativeLanguage?: string;
  languageLevelLocalCode?: string;
  languageLevelLocalLabel?: string;
  languageLevelGroupCode?: string;
  languageLevelGroupLabel?: string;
  waEmployee?: IWaEmployee;
}

export class WaLanguage implements IWaLanguage {
  constructor(
    public id?: number,
    public localIdNumber?: string,
    public languageCode?: string,
    public languageLabel?: string,
    public languageSpeakProficiencyCode?: string,
    public languageSpeakProficiencyLabel?: string,
    public languageReadProficiencyCode?: string,
    public languageReadProficiencyLabel?: string,
    public languageWriteProficiencyCode?: string,
    public languageWriteProficiencyLabel?: string,
    public nativeLanguage?: string,
    public languageLevelLocalCode?: string,
    public languageLevelLocalLabel?: string,
    public languageLevelGroupCode?: string,
    public languageLevelGroupLabel?: string,
    public waEmployee?: IWaEmployee
  ) {}
}
