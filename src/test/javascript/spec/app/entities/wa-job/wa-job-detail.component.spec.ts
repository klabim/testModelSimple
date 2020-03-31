import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaJobDetailComponent } from 'app/entities/wa-job/wa-job-detail.component';
import { WaJob } from 'app/shared/model/wa-job.model';

describe('Component Tests', () => {
  describe('WaJob Management Detail Component', () => {
    let comp: WaJobDetailComponent;
    let fixture: ComponentFixture<WaJobDetailComponent>;
    const route = ({ data: of({ waJob: new WaJob(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaJobDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaJobDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaJobDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waJob on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waJob).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
