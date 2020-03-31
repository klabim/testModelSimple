import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaManagementHrisComponent } from 'app/entities/wa-management-hris/wa-management-hris.component';
import { WaManagementHrisService } from 'app/entities/wa-management-hris/wa-management-hris.service';
import { WaManagementHris } from 'app/shared/model/wa-management-hris.model';

describe('Component Tests', () => {
  describe('WaManagementHris Management Component', () => {
    let comp: WaManagementHrisComponent;
    let fixture: ComponentFixture<WaManagementHrisComponent>;
    let service: WaManagementHrisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaManagementHrisComponent]
      })
        .overrideTemplate(WaManagementHrisComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaManagementHrisComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaManagementHrisService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaManagementHris(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waManagementHrises && comp.waManagementHrises[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
