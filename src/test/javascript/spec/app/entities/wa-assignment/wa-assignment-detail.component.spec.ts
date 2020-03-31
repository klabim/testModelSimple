import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaAssignmentDetailComponent } from 'app/entities/wa-assignment/wa-assignment-detail.component';
import { WaAssignment } from 'app/shared/model/wa-assignment.model';

describe('Component Tests', () => {
  describe('WaAssignment Management Detail Component', () => {
    let comp: WaAssignmentDetailComponent;
    let fixture: ComponentFixture<WaAssignmentDetailComponent>;
    const route = ({ data: of({ waAssignment: new WaAssignment(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaAssignmentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaAssignmentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaAssignmentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waAssignment on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waAssignment).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
