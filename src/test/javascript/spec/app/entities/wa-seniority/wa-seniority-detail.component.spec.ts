import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaSeniorityDetailComponent } from 'app/entities/wa-seniority/wa-seniority-detail.component';
import { WaSeniority } from 'app/shared/model/wa-seniority.model';

describe('Component Tests', () => {
  describe('WaSeniority Management Detail Component', () => {
    let comp: WaSeniorityDetailComponent;
    let fixture: ComponentFixture<WaSeniorityDetailComponent>;
    const route = ({ data: of({ waSeniority: new WaSeniority(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaSeniorityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaSeniorityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaSeniorityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waSeniority on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waSeniority).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
