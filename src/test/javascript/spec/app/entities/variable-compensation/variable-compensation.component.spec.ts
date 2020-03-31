import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { VariableCompensationComponent } from 'app/entities/variable-compensation/variable-compensation.component';
import { VariableCompensationService } from 'app/entities/variable-compensation/variable-compensation.service';
import { VariableCompensation } from 'app/shared/model/variable-compensation.model';

describe('Component Tests', () => {
  describe('VariableCompensation Management Component', () => {
    let comp: VariableCompensationComponent;
    let fixture: ComponentFixture<VariableCompensationComponent>;
    let service: VariableCompensationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [VariableCompensationComponent]
      })
        .overrideTemplate(VariableCompensationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VariableCompensationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VariableCompensationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new VariableCompensation(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.variableCompensations && comp.variableCompensations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
