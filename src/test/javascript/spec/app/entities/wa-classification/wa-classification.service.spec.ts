import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaClassificationService } from 'app/entities/wa-classification/wa-classification.service';
import { IWaClassification, WaClassification } from 'app/shared/model/wa-classification.model';

describe('Service Tests', () => {
  describe('WaClassification Service', () => {
    let injector: TestBed;
    let service: WaClassificationService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaClassification;
    let expectedResult: IWaClassification | IWaClassification[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaClassificationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaClassification(
        0,
        'AAAAAAA',
        currentDate,
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
        const returnedFromService = Object.assign(
          {
            effectiveDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WaClassification', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            effectiveDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDate: currentDate
          },
          returnedFromService
        );

        service.create(new WaClassification()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaClassification', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            localClassificationCode: 'BBBBBB',
            localClassificationLabel: 'BBBBBB',
            groupClassificationCode: 'BBBBBB',
            groupClassificationLabel: 'BBBBBB',
            professionalCategoryCode: 'BBBBBB',
            professionalCategoryLabel: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaClassification', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            localClassificationCode: 'BBBBBB',
            localClassificationLabel: 'BBBBBB',
            groupClassificationCode: 'BBBBBB',
            groupClassificationLabel: 'BBBBBB',
            professionalCategoryCode: 'BBBBBB',
            professionalCategoryLabel: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WaClassification', () => {
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
