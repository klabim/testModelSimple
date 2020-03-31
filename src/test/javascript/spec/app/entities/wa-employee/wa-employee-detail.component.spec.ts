import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaEmployeeDetailComponent } from 'app/entities/wa-employee/wa-employee-detail.component';
import { WaEmployee } from 'app/shared/model/wa-employee.model';

describe('Component Tests', () => {
  describe('WaEmployee Management Detail Component', () => {
    let comp: WaEmployeeDetailComponent;
    let fixture: ComponentFixture<WaEmployeeDetailComponent>;
    const route = ({ data: of({ waEmployee: new WaEmployee(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaEmployeeDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaEmployeeDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaEmployeeDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waEmployee on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waEmployee).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
