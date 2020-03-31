import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { ManagerialLinkService } from 'app/entities/managerial-link/managerial-link.service';
import { IManagerialLink, ManagerialLink } from 'app/shared/model/managerial-link.model';

describe('Service Tests', () => {
  describe('ManagerialLink Service', () => {
    let injector: TestBed;
    let service: ManagerialLinkService;
    let httpMock: HttpTestingController;
    let elemDefault: IManagerialLink;
    let expectedResult: IManagerialLink | IManagerialLink[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(ManagerialLinkService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new ManagerialLink(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a ManagerialLink', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new ManagerialLink()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a ManagerialLink', () => {
        const returnedFromService = Object.assign(
          {
            managerialLinkType: 'BBBBBB',
            localIdNumber: 'BBBBBB',
            localIDDirectManager: 'BBBBBB',
            ggiDirectLineManager: 'BBBBBB',
            managerLastName: 'BBBBBB',
            managerFirstName: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of ManagerialLink', () => {
        const returnedFromService = Object.assign(
          {
            managerialLinkType: 'BBBBBB',
            localIdNumber: 'BBBBBB',
            localIDDirectManager: 'BBBBBB',
            ggiDirectLineManager: 'BBBBBB',
            managerLastName: 'BBBBBB',
            managerFirstName: 'BBBBBB'
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

      it('should delete a ManagerialLink', () => {
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
