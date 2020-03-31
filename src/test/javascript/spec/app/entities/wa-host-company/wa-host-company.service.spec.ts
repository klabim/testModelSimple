import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaHostCompanyService } from 'app/entities/wa-host-company/wa-host-company.service';
import { IWaHostCompany, WaHostCompany } from 'app/shared/model/wa-host-company.model';

describe('Service Tests', () => {
  describe('WaHostCompany Service', () => {
    let injector: TestBed;
    let service: WaHostCompanyService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaHostCompany;
    let expectedResult: IWaHostCompany | IWaHostCompany[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaHostCompanyService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaHostCompany(0, 'AAAAAAA', currentDate, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
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

      it('should create a WaHostCompany', () => {
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

        service.create(new WaHostCompany()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaHostCompany', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            assignmentType: 'BBBBBB',
            assignmentTypeLabel: 'BBBBBB',
            hostCompanyCode: 'BBBBBB',
            groupHostCompanyCode: 'BBBBBB'
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

      it('should return a list of WaHostCompany', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            assignmentType: 'BBBBBB',
            assignmentTypeLabel: 'BBBBBB',
            hostCompanyCode: 'BBBBBB',
            groupHostCompanyCode: 'BBBBBB'
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

      it('should delete a WaHostCompany', () => {
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
