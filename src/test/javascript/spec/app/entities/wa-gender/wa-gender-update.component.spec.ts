import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaGenderUpdateComponent } from 'app/entities/wa-gender/wa-gender-update.component';
import { WaGenderService } from 'app/entities/wa-gender/wa-gender.service';
import { WaGender } from 'app/shared/model/wa-gender.model';

describe('Component Tests', () => {
  describe('WaGender Management Update Component', () => {
    let comp: WaGenderUpdateComponent;
    let fixture: ComponentFixture<WaGenderUpdateComponent>;
    let service: WaGenderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaGenderUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaGenderUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaGenderUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaGenderService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaGender(123);
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
        const entity = new WaGender();
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
