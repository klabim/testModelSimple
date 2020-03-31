import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaSeniorityComponent } from 'app/entities/wa-seniority/wa-seniority.component';
import { WaSeniorityService } from 'app/entities/wa-seniority/wa-seniority.service';
import { WaSeniority } from 'app/shared/model/wa-seniority.model';

describe('Component Tests', () => {
  describe('WaSeniority Management Component', () => {
    let comp: WaSeniorityComponent;
    let fixture: ComponentFixture<WaSeniorityComponent>;
    let service: WaSeniorityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaSeniorityComponent]
      })
        .overrideTemplate(WaSeniorityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaSeniorityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaSeniorityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaSeniority(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waSeniorities && comp.waSeniorities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
