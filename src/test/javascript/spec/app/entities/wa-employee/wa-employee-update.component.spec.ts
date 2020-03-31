import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaEmployeeUpdateComponent } from 'app/entities/wa-employee/wa-employee-update.component';
import { WaEmployeeService } from 'app/entities/wa-employee/wa-employee.service';
import { WaEmployee } from 'app/shared/model/wa-employee.model';

describe('Component Tests', () => {
  describe('WaEmployee Management Update Component', () => {
    let comp: WaEmployeeUpdateComponent;
    let fixture: ComponentFixture<WaEmployeeUpdateComponent>;
    let service: WaEmployeeService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaEmployeeUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaEmployeeUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaEmployeeUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaEmployeeService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaEmployee(123);
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
        const entity = new WaEmployee();
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
