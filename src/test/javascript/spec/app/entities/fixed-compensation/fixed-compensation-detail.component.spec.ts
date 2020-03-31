import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { FixedCompensationDetailComponent } from 'app/entities/fixed-compensation/fixed-compensation-detail.component';
import { FixedCompensation } from 'app/shared/model/fixed-compensation.model';

describe('Component Tests', () => {
  describe('FixedCompensation Management Detail Component', () => {
    let comp: FixedCompensationDetailComponent;
    let fixture: ComponentFixture<FixedCompensationDetailComponent>;
    const route = ({ data: of({ fixedCompensation: new FixedCompensation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [FixedCompensationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(FixedCompensationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(FixedCompensationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load fixedCompensation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.fixedCompensation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
