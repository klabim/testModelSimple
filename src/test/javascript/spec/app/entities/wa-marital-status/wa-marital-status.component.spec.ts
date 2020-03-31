import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaMaritalStatusComponent } from 'app/entities/wa-marital-status/wa-marital-status.component';
import { WaMaritalStatusService } from 'app/entities/wa-marital-status/wa-marital-status.service';
import { WaMaritalStatus } from 'app/shared/model/wa-marital-status.model';

describe('Component Tests', () => {
  describe('WaMaritalStatus Management Component', () => {
    let comp: WaMaritalStatusComponent;
    let fixture: ComponentFixture<WaMaritalStatusComponent>;
    let service: WaMaritalStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaMaritalStatusComponent]
      })
        .overrideTemplate(WaMaritalStatusComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaMaritalStatusComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaMaritalStatusService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaMaritalStatus(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waMaritalStatuses && comp.waMaritalStatuses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
