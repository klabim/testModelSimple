import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { VariableCompensationDetailComponent } from 'app/entities/variable-compensation/variable-compensation-detail.component';
import { VariableCompensation } from 'app/shared/model/variable-compensation.model';

describe('Component Tests', () => {
  describe('VariableCompensation Management Detail Component', () => {
    let comp: VariableCompensationDetailComponent;
    let fixture: ComponentFixture<VariableCompensationDetailComponent>;
    const route = ({ data: of({ variableCompensation: new VariableCompensation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [VariableCompensationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(VariableCompensationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(VariableCompensationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load variableCompensation on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.variableCompensation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
