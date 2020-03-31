import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { WaManagementHrisService } from 'app/entities/wa-management-hris/wa-management-hris.service';
import { IWaManagementHris, WaManagementHris } from 'app/shared/model/wa-management-hris.model';

describe('Service Tests', () => {
  describe('WaManagementHris Service', () => {
    let injector: TestBed;
    let service: WaManagementHrisService;
    let httpMock: HttpTestingController;
    let elemDefault: IWaManagementHris;
    let expectedResult: IWaManagementHris | IWaManagementHris[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(WaManagementHrisService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new WaManagementHris(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a WaManagementHris', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new WaManagementHris()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a WaManagementHris', () => {
        const returnedFromService = Object.assign(
          {
            managementHrisCode: 'BBBBBB',
            localIdNumber: 'BBBBBB',
            managementHrisLabel: 'BBBBBB',
            entityManagementCode: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of WaManagementHris', () => {
        const returnedFromService = Object.assign(
          {
            managementHrisCode: 'BBBBBB',
            localIdNumber: 'BBBBBB',
            managementHrisLabel: 'BBBBBB',
            entityManagementCode: 'BBBBBB'
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

      it('should delete a WaManagementHris', () => {
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
