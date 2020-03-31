import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaMaritalStatusUpdateComponent } from 'app/entities/wa-marital-status/wa-marital-status-update.component';
import { WaMaritalStatusService } from 'app/entities/wa-marital-status/wa-marital-status.service';
import { WaMaritalStatus } from 'app/shared/model/wa-marital-status.model';

describe('Component Tests', () => {
  describe('WaMaritalStatus Management Update Component', () => {
    let comp: WaMaritalStatusUpdateComponent;
    let fixture: ComponentFixture<WaMaritalStatusUpdateComponent>;
    let service: WaMaritalStatusService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaMaritalStatusUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaMaritalStatusUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaMaritalStatusUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaMaritalStatusService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaMaritalStatus(123);
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
        const entity = new WaMaritalStatus();
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
