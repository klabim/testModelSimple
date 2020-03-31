import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaLanguageDetailComponent } from 'app/entities/wa-language/wa-language-detail.component';
import { WaLanguage } from 'app/shared/model/wa-language.model';

describe('Component Tests', () => {
  describe('WaLanguage Management Detail Component', () => {
    let comp: WaLanguageDetailComponent;
    let fixture: ComponentFixture<WaLanguageDetailComponent>;
    const route = ({ data: of({ waLanguage: new WaLanguage(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaLanguageDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaLanguageDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaLanguageDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waLanguage on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waLanguage).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
