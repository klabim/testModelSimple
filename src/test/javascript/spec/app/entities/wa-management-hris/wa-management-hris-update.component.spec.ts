import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaManagementHrisUpdateComponent } from 'app/entities/wa-management-hris/wa-management-hris-update.component';
import { WaManagementHrisService } from 'app/entities/wa-management-hris/wa-management-hris.service';
import { WaManagementHris } from 'app/shared/model/wa-management-hris.model';

describe('Component Tests', () => {
  describe('WaManagementHris Management Update Component', () => {
    let comp: WaManagementHrisUpdateComponent;
    let fixture: ComponentFixture<WaManagementHrisUpdateComponent>;
    let service: WaManagementHrisService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaManagementHrisUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaManagementHrisUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaManagementHrisUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaManagementHrisService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaManagementHris(123);
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
        const entity = new WaManagementHris();
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
