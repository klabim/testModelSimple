import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaAssignmentUpdateComponent } from 'app/entities/wa-assignment/wa-assignment-update.component';
import { WaAssignmentService } from 'app/entities/wa-assignment/wa-assignment.service';
import { WaAssignment } from 'app/shared/model/wa-assignment.model';

describe('Component Tests', () => {
  describe('WaAssignment Management Update Component', () => {
    let comp: WaAssignmentUpdateComponent;
    let fixture: ComponentFixture<WaAssignmentUpdateComponent>;
    let service: WaAssignmentService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaAssignmentUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaAssignmentUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaAssignmentUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaAssignmentService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaAssignment(123);
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
        const entity = new WaAssignment();
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
