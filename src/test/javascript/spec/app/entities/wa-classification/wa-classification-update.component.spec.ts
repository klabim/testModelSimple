import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaClassificationUpdateComponent } from 'app/entities/wa-classification/wa-classification-update.component';
import { WaClassificationService } from 'app/entities/wa-classification/wa-classification.service';
import { WaClassification } from 'app/shared/model/wa-classification.model';

describe('Component Tests', () => {
  describe('WaClassification Management Update Component', () => {
    let comp: WaClassificationUpdateComponent;
    let fixture: ComponentFixture<WaClassificationUpdateComponent>;
    let service: WaClassificationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaClassificationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaClassificationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaClassificationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaClassificationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaClassification(123);
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
        const entity = new WaClassification();
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
