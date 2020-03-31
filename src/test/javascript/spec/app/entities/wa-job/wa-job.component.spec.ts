import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaJobComponent } from 'app/entities/wa-job/wa-job.component';
import { WaJobService } from 'app/entities/wa-job/wa-job.service';
import { WaJob } from 'app/shared/model/wa-job.model';

describe('Component Tests', () => {
  describe('WaJob Management Component', () => {
    let comp: WaJobComponent;
    let fixture: ComponentFixture<WaJobComponent>;
    let service: WaJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaJobComponent]
      })
        .overrideTemplate(WaJobComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaJobComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaJobService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaJob(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waJobs && comp.waJobs[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
