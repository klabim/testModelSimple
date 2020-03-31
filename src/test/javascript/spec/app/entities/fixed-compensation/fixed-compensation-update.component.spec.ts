import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { FixedCompensationUpdateComponent } from 'app/entities/fixed-compensation/fixed-compensation-update.component';
import { FixedCompensationService } from 'app/entities/fixed-compensation/fixed-compensation.service';
import { FixedCompensation } from 'app/shared/model/fixed-compensation.model';

describe('Component Tests', () => {
  describe('FixedCompensation Management Update Component', () => {
    let comp: FixedCompensationUpdateComponent;
    let fixture: ComponentFixture<FixedCompensationUpdateComponent>;
    let service: FixedCompensationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [FixedCompensationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(FixedCompensationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(FixedCompensationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(FixedCompensationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new FixedCompensation(123);
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
        const entity = new FixedCompensation();
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
