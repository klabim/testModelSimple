import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaHostCompanyComponent } from 'app/entities/wa-host-company/wa-host-company.component';
import { WaHostCompanyService } from 'app/entities/wa-host-company/wa-host-company.service';
import { WaHostCompany } from 'app/shared/model/wa-host-company.model';

describe('Component Tests', () => {
  describe('WaHostCompany Management Component', () => {
    let comp: WaHostCompanyComponent;
    let fixture: ComponentFixture<WaHostCompanyComponent>;
    let service: WaHostCompanyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaHostCompanyComponent]
      })
        .overrideTemplate(WaHostCompanyComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaHostCompanyComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaHostCompanyService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaHostCompany(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waHostCompanies && comp.waHostCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
