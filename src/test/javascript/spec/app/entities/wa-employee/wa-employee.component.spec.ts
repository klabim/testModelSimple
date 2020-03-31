import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaEmployeeComponent } from 'app/entities/wa-employee/wa-employee.component';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';
import { WaEmployee } from 'app/shared/model/wa-employee.model';

describe('Component Tests', () => {
  describe('WaEmployee Management Component', () => {
    let comp: WaEmployeeComponent;
    let fixture: ComponentFixture<WaEmployeeComponent>;
    let service: WaEmployeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaEmployeeComponent]
      })
        .overrideTemplate(WaEmployeeComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaEmployeeComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaEmployeeService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaEmployee(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waEmployees && comp.waEmployees[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
