import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaClassificationComponent } from 'app/entities/wa-classification/wa-classification.component';
import { WaClassificationService } from 'app/entities/wa-classification/wa-classification.service';
import { WaClassification } from 'app/shared/model/wa-classification.model';

describe('Component Tests', () => {
  describe('WaClassification Management Component', () => {
    let comp: WaClassificationComponent;
    let fixture: ComponentFixture<WaClassificationComponent>;
    let service: WaClassificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaClassificationComponent]
      })
        .overrideTemplate(WaClassificationComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaClassificationComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaClassificationService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaClassification(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waClassifications && comp.waClassifications[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
