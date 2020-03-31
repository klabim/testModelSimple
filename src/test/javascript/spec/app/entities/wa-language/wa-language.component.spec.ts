import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaLanguageComponent } from 'app/entities/wa-language/wa-language.component';
import { WaLanguageService } from 'app/entities/wa-language/wa-language.service';
import { WaLanguage } from 'app/shared/model/wa-language.model';

describe('Component Tests', () => {
  describe('WaLanguage Management Component', () => {
    let comp: WaLanguageComponent;
    let fixture: ComponentFixture<WaLanguageComponent>;
    let service: WaLanguageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaLanguageComponent]
      })
        .overrideTemplate(WaLanguageComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaLanguageComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaLanguageService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaLanguage(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waLanguages && comp.waLanguages[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
