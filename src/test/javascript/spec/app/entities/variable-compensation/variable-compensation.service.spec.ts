import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { VariableCompensationService } from 'app/entities/variable-compensation/variable-compensation.service';
import { IVariableCompensation, VariableCompensation } from 'app/shared/model/variable-compensation.model';

describe('Service Tests', () => {
  describe('VariableCompensation Service', () => {
    let injector: TestBed;
    let service: VariableCompensationService;
    let httpMock: HttpTestingController;
    let elemDefault: IVariableCompensation;
    let expectedResult: IVariableCompensation | IVariableCompensation[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(VariableCompensationService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new VariableCompensation(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        currentDate
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            effectiveBeginningDate: currentDate.format(DATE_TIME_FORMAT),
            effectiveEndDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a VariableCompensation', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            effectiveBeginningDate: currentDate.format(DATE_TIME_FORMAT),
            effectiveEndDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveBeginningDate: currentDate,
            effectiveEndDate: currentDate
          },
          returnedFromService
        );

        service.create(new VariableCompensation()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a VariableCompensation', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            variableCompensationTypeGroupCode: 'BBBBBB',
            variableCompensationTypeGroupLabel: 'BBBBBB',
            effectiveBeginningDate: currentDate.format(DATE_TIME_FORMAT),
            variableCompensationCurrency: 'BBBBBB',
            variableCompensationLocalTypeCode: 'BBBBBB',
            variableCompensationLocalTypeLabel: 'BBBBBB',
            variableCompensationAmount: 'BBBBBB',
            effectiveEndDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveBeginningDate: currentDate,
            effectiveEndDate: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of VariableCompensation', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            variableCompensationTypeGroupCode: 'BBBBBB',
            variableCompensationTypeGroupLabel: 'BBBBBB',
            effectiveBeginningDate: currentDate.format(DATE_TIME_FORMAT),
            variableCompensationCurrency: 'BBBBBB',
            variableCompensationLocalTypeCode: 'BBBBBB',
            variableCompensationLocalTypeLabel: 'BBBBBB',
            variableCompensationAmount: 'BBBBBB',
            effectiveEndDate: currentDate.format(DATE_TIME_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            effectiveBeginningDate: currentDate,
            effectiveEndDate: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a VariableCompensation', () => {
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
