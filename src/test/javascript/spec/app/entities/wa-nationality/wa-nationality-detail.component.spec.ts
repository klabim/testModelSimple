import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaNationalityDetailComponent } from 'app/entities/wa-nationality/wa-nationality-detail.component';
import { WaNationality } from 'app/shared/model/wa-nationality.model';

describe('Component Tests', () => {
  describe('WaNationality Management Detail Component', () => {
    let comp: WaNationalityDetailComponent;
    let fixture: ComponentFixture<WaNationalityDetailComponent>;
    const route = ({ data: of({ waNationality: new WaNationality(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaNationalityDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaNationalityDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaNationalityDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waNationality on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waNationality).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
