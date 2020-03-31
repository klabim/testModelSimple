import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaHomeCompanyComponent } from 'app/entities/wa-home-company/wa-home-company.component';
import { WaHomeCompanyService } from 'app/entities/wa-home-company/wa-home-company.service';
import { WaHomeCompany } from 'app/shared/model/wa-home-company.model';

describe('Component Tests', () => {
  describe('WaHomeCompany Management Component', () => {
    let comp: WaHomeCompanyComponent;
    let fixture: ComponentFixture<WaHomeCompanyComponent>;
    let service: WaHomeCompanyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaHomeCompanyComponent]
      })
        .overrideTemplate(WaHomeCompanyComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaHomeCompanyComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaHomeCompanyService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaHomeCompany(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waHomeCompanies && comp.waHomeCompanies[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
