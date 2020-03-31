import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { FixedCompensationComponent } from 'app/entities/fixed-compensation/fixed-compensation.component';
import { FixedCompensationService } from 'app/entities/fixed-compensation/fixed-compensation.service';
import { FixedCompensation } from 'app/shared/model/fixed-compensation.model';

describe('Component Tests', () => {
  describe('FixedCompensation Management Component', () => {
    let comp: FixedCompensationComponent;
    let fixture: ComponentFixture<FixedCompensationComponent>;
    let service: FixedCompensationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [FixedCompensationComponent]
      })
        .overrideTemplate(FixedCompensationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FixedCompensationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FixedCompensationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new FixedCompensation(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.fixedCompensations && comp.fixedCompensations[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
