import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';
import { WaPersonalAddressService } from 'app/entities/wa-personal-address/wa-personal-address.service';
import { IWaPersonalAddress, WaPersonalAddress } from 'app/shared/model/wa-personal-address.model';

describe('Service Tests', () => {
  describe('WaPersonalAddress Service', () => {
    let injector: TestBed;
    let service: WaPersonalAddressService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaPersonalAddress;
    let expectedResult: IWaPersonalAddress | IWaPersonalAddress[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaPersonalAddressService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new WaPersonalAddress(
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
        currentDate
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

      it('should create a WaPersonalAddress', () => {
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

        service.create(new WaPersonalAddress()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaPersonalAddress', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            personalAddressType: 'BBBBBB',
            additionalAddress1: 'BBBBBB',
            additionalAddress2: 'BBBBBB',
            physicalAddress: 'BBBBBB',
            additionalPostalDelivery: 'BBBBBB',
            zipCodePostalCode: 'BBBBBB',
            employeeCityOfResidence: 'BBBBBB',
            countryOfResidence: 'BBBBBB',
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

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaPersonalAddress', () => {
        const returnedFromService = Object.assign(
          {
            localIdNumber: 'BBBBBB',
            personalAddressType: 'BBBBBB',
            additionalAddress1: 'BBBBBB',
            additionalAddress2: 'BBBBBB',
            physicalAddress: 'BBBBBB',
            additionalPostalDelivery: 'BBBBBB',
            zipCodePostalCode: 'BBBBBB',
            employeeCityOfResidence: 'BBBBBB',
            countryOfResidence: 'BBBBBB',
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

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a WaPersonalAddress', () => {
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
