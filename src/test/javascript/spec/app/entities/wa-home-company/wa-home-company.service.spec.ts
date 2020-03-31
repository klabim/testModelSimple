import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaHomeCompanyService } from 'app/entities/wa-home-company/wa-home-company.service';
import { IWaHomeCompany, WaHomeCompany } from 'app/shared/model/wa-home-company.model';

describe('Service Tests', () => {
  describe('WaHomeCompany Service', () => {
    let injector: TestBed;
    let service: WaHomeCompanyService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaHomeCompany;
    let expectedResult: IWaHomeCompany | IWaHomeCompany[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaHomeCompanyService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaHomeCompany(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            effectiveAssignmentDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WaHomeCompany', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            effectiveAssignmentDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveAssignmentDate: currentDate
          },
          returnedFromService
        );

        service.create(new WaHomeCompany()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaHomeCompany', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveAssignmentDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            assignmentType: 'BBBBBB',
            assignmentTypeLabel: 'BBBBBB',
            homeCompanyCode: 'BBBBBB',
            groupHomeCompanyCode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveAssignmentDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaHomeCompany', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveAssignmentDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            assignmentType: 'BBBBBB',
            assignmentTypeLabel: 'BBBBBB',
            homeCompanyCode: 'BBBBBB',
            groupHomeCompanyCode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveAssignmentDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WaHomeCompany', () => {
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
