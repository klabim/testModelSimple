import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaAssignmentComponent } from 'app/entities/wa-assignment/wa-assignment.component';
import { WaAssignmentService } from 'app/entities/wa-assignment/wa-assignment.service';
import { WaAssignment } from 'app/shared/model/wa-assignment.model';

describe('Component Tests', () => {
  describe('WaAssignment Management Component', () => {
    let comp: WaAssignmentComponent;
    let fixture: ComponentFixture<WaAssignmentComponent>;
    let service: WaAssignmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaAssignmentComponent]
      })
        .overrideTemplate(WaAssignmentComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaAssignmentComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaAssignmentService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaAssignment(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waAssignments && comp.waAssignments[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
