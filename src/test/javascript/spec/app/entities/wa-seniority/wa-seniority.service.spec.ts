import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaSeniorityService } from 'app/entities/wa-seniority/wa-seniority.service';
import { IWaSeniority, WaSeniority } from 'app/shared/model/wa-seniority.model';

describe('Service Tests', () => {
  describe('WaSeniority Service', () => {
    let injector: TestBed;
    let service: WaSeniorityService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaSeniority;
    let expectedResult: IWaSeniority | IWaSeniority[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaSeniorityService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaSeniority(0, 'AAAAAAA', currentDate, currentDate, currentDate, 'AAAAAAA', currentDate);
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            groupSgDateOfEntry: currentDate.format(DATE_TIME_FORMAT),
            sgSeniorityDate: currentDate.format(DATE_TIME_FORMAT),
            hireDate: currentDate.format(DATE_TIME_FORMAT),
            endingOfTrialPeriodEstimatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WaSeniority', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            groupSgDateOfEntry: currentDate.format(DATE_TIME_FORMAT),
            sgSeniorityDate: currentDate.format(DATE_TIME_FORMAT),
            hireDate: currentDate.format(DATE_TIME_FORMAT),
            endingOfTrialPeriodEstimatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            groupSgDateOfEntry: currentDate,
            sgSeniorityDate: currentDate,
            hireDate: currentDate,
            endingOfTrialPeriodEstimatedDate: currentDate
          },
          returnedFromService
        );

        service.create(new WaSeniority()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaSeniority', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            groupSgDateOfEntry: currentDate.format(DATE_TIME_FORMAT),
            sgSeniorityDate: currentDate.format(DATE_TIME_FORMAT),
            hireDate: currentDate.format(DATE_TIME_FORMAT),
            bankingSectorSeniority: 'BBBBBB',
            endingOfTrialPeriodEstimatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            groupSgDateOfEntry: currentDate,
            sgSeniorityDate: currentDate,
            hireDate: currentDate,
            endingOfTrialPeriodEstimatedDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaSeniority', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            groupSgDateOfEntry: currentDate.format(DATE_TIME_FORMAT),
            sgSeniorityDate: currentDate.format(DATE_TIME_FORMAT),
            hireDate: currentDate.format(DATE_TIME_FORMAT),
            bankingSectorSeniority: 'BBBBBB',
            endingOfTrialPeriodEstimatedDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            groupSgDateOfEntry: currentDate,
            sgSeniorityDate: currentDate,
            hireDate: currentDate,
            endingOfTrialPeriodEstimatedDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WaSeniority', () => {
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
