import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaJobService } from 'app/entities/wa-job/wa-job.service';
import { IWaJob, WaJob } from 'app/shared/model/wa-job.model';

describe('Service Tests', () => {
  describe('WaJob Service', () => {
    let injector: TestBed;
    let service: WaJobService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaJob;
    let expectedResult: IWaJob | IWaJob[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaJobService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaJob(
        0,
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
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
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            positionEntryDate: currentDate.format(DATE_TIME_FORMAT),
            jobEntryDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WaJob', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            positionEntryDate: currentDate.format(DATE_TIME_FORMAT),
            jobEntryDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDate: currentDate,
            positionEntryDate: currentDate,
            jobEntryDate: currentDate
          },
          returnedFromService
        );

        service.create(new WaJob()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaJob', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            localPositionCode: 'BBBBBB',
            localPositionLevel: 'BBBBBB',
            positionEntryDate: currentDate.format(DATE_TIME_FORMAT),
            jobCodeLocalValue: 'BBBBBB',
            jobCodeGroupeValue: 'BBBBBB',
            jobEntryDate: currentDate.format(DATE_TIME_FORMAT),
            localCollectiveAgreementCode: 'BBBBBB',
            localCollectiveAgreementLabel: 'BBBBBB',
            orgRelationship: 'BBBBBB',
            prescripteur: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDate: currentDate,
            positionEntryDate: currentDate,
            jobEntryDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaJob', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            localPositionCode: 'BBBBBB',
            localPositionLevel: 'BBBBBB',
            positionEntryDate: currentDate.format(DATE_TIME_FORMAT),
            jobCodeLocalValue: 'BBBBBB',
            jobCodeGroupeValue: 'BBBBBB',
            jobEntryDate: currentDate.format(DATE_TIME_FORMAT),
            localCollectiveAgreementCode: 'BBBBBB',
            localCollectiveAgreementLabel: 'BBBBBB',
            orgRelationship: 'BBBBBB',
            prescripteur: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveDate: currentDate,
            positionEntryDate: currentDate,
            jobEntryDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WaJob', () => {
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
