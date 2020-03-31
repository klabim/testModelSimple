import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { VariableCompensationUpdateComponent } from 'app/entities/variable-compensation/variable-compensation-update.component';
import { VariableCompensationService } from 'app/entities/variable-compensation/variable-compensation.service';
import { VariableCompensation } from 'app/shared/model/variable-compensation.model';

describe('Component Tests', () => {
  describe('VariableCompensation Management Update Component', () => {
    let comp: VariableCompensationUpdateComponent;
    let fixture: ComponentFixture<VariableCompensationUpdateComponent>;
    let service: VariableCompensationService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [VariableCompensationUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(VariableCompensationUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(VariableCompensationUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(VariableCompensationService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new VariableCompensation(123);
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
        const entity = new VariableCompensation();
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
