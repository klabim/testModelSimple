import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaClassificationDetailComponent } from 'app/entities/wa-classification/wa-classification-detail.component';
import { WaClassification } from 'app/shared/model/wa-classification.model';

describe('Component Tests', () => {
  describe('WaClassification Management Detail Component', () => {
    let comp: WaClassificationDetailComponent;
    let fixture: ComponentFixture<WaClassificationDetailComponent>;
    const route = ({ data: of({ waClassification: new WaClassification(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaClassificationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaClassificationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaClassificationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waClassification on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waClassification).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
