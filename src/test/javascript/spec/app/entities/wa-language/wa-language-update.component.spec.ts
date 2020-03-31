import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaLanguageUpdateComponent } from 'app/entities/wa-language/wa-language-update.component';
import { WaLanguageService } from 'app/entities/wa-language/wa-language.service';
import { WaLanguage } from 'app/shared/model/wa-language.model';

describe('Component Tests', () => {
  describe('WaLanguage Management Update Component', () => {
    let comp: WaLanguageUpdateComponent;
    let fixture: ComponentFixture<WaLanguageUpdateComponent>;
    let service: WaLanguageService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaLanguageUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaLanguageUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaLanguageUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaLanguageService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaLanguage(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaLanguage();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
