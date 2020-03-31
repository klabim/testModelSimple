import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaAssignmentService } from 'app/entities/wa-assignment/wa-assignment.service';
import { IWaAssignment, WaAssignment } from 'app/shared/model/wa-assignment.model';

describe('Service Tests', () => {
  describe('WaAssignment Service', () => {
    let injector: TestBed;
    let service: WaAssignmentService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaAssignment;
    let expectedResult: IWaAssignment | IWaAssignment[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaAssignmentService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaAssignment(
        0,
        'AAAAAAA',
        currentDate,
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

      it('should create a WaAssignment', () => {
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

        service.create(new WaAssignment()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaAssignment', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            localCompanyCode: 'BBBBBB',
            legalGroupCompanyCode: 'BBBBBB',
            establishment: 'BBBBBB',
            poleActivityGroupCode: 'BBBBBB',
            groupSubPoleActivityCode: 'BBBBBB',
            groupSubPoleActivityTitle: 'BBBBBB',
            budgetaryAffectationDepartmentCode: 'BBBBBB',
            budgetaryAffectationDepartmentLabel: 'BBBBBB',
            budgetFunctionalIdSakkarah: 'BBBBBB',
            administrativeDepartmentLocalCode: 'BBBBBB',
            administrativeDepartmentLocalLabel: 'BBBBBB',
            adminFunctionalIdSakkarah: 'BBBBBB',
            detachmentGroupCompany: 'BBBBBB',
            detachmentLegalGroupCompany: 'BBBBBB'
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

      it('should return a list of WaAssignment', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            effectiveDate: currentDate.format(DATE_TIME_FORMAT),
            sequence: 'BBBBBB',
            localCompanyCode: 'BBBBBB',
            legalGroupCompanyCode: 'BBBBBB',
            establishment: 'BBBBBB',
            poleActivityGroupCode: 'BBBBBB',
            groupSubPoleActivityCode: 'BBBBBB',
            groupSubPoleActivityTitle: 'BBBBBB',
            budgetaryAffectationDepartmentCode: 'BBBBBB',
            budgetaryAffectationDepartmentLabel: 'BBBBBB',
            budgetFunctionalIdSakkarah: 'BBBBBB',
            administrativeDepartmentLocalCode: 'BBBBBB',
            administrativeDepartmentLocalLabel: 'BBBBBB',
            adminFunctionalIdSakkarah: 'BBBBBB',
            detachmentGroupCompany: 'BBBBBB',
            detachmentLegalGroupCompany: 'BBBBBB'
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

      it('should delete a WaAssignment', () => {
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
