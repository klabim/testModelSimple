import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaGenderDetailComponent } from 'app/entities/wa-gender/wa-gender-detail.component';
import { WaGender } from 'app/shared/model/wa-gender.model';

describe('Component Tests', () => {
  describe('WaGender Management Detail Component', () => {
    let comp: WaGenderDetailComponent;
    let fixture: ComponentFixture<WaGenderDetailComponent>;
    const route = ({ data: of({ waGender: new WaGender(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaGenderDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaGenderDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaGenderDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waGender on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waGender).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
