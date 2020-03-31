import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WaLanguageService } from 'app/entities/wa-language/wa-language.service';
import { IWaLanguage, WaLanguage } from 'app/shared/model/wa-language.model';

describe('Service Tests', () => {
  describe('WaLanguage Service', () => {
    let injector: TestBed;
    let service: WaLanguageService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaLanguage;
    let expectedResult: IWaLanguage | IWaLanguage[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaLanguageService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new WaLanguage(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WaLanguage', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WaLanguage()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaLanguage', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            languageCode: 'BBBBBB',
            languageLabel: 'BBBBBB',
            languageSpeakProficiencyCode: 'BBBBBB',
            languageSpeakProficiencyLabel: 'BBBBBB',
            languageReadProficiencyCode: 'BBBBBB',
            languageReadProficiencyLabel: 'BBBBBB',
            languageWriteProficiencyCode: 'BBBBBB',
            languageWriteProficiencyLabel: 'BBBBBB',
            nativeLanguage: 'BBBBBB',
            languageLevelLocalCode: 'BBBBBB',
            languageLevelLocalLabel: 'BBBBBB',
            languageLevelGroupCode: 'BBBBBB',
            languageLevelGroupLabel: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaLanguage', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            languageCode: 'BBBBBB',
            languageLabel: 'BBBBBB',
            languageSpeakProficiencyCode: 'BBBBBB',
            languageSpeakProficiencyLabel: 'BBBBBB',
            languageReadProficiencyCode: 'BBBBBB',
            languageReadProficiencyLabel: 'BBBBBB',
            languageWriteProficiencyCode: 'BBBBBB',
            languageWriteProficiencyLabel: 'BBBBBB',
            nativeLanguage: 'BBBBBB',
            languageLevelLocalCode: 'BBBBBB',
            languageLevelLocalLabel: 'BBBBBB',
            languageLevelGroupCode: 'BBBBBB',
            languageLevelGroupLabel: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WaLanguage', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
