import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaSeniorityUpdateComponent } from 'app/entities/wa-seniority/wa-seniority-update.component';
import { WaSeniorityService } from 'app/entities/wa-seniority/wa-seniority.service';
import { WaSeniority } from 'app/shared/model/wa-seniority.model';

describe('Component Tests', () => {
  describe('WaSeniority Management Update Component', () => {
    let comp: WaSeniorityUpdateComponent;
    let fixture: ComponentFixture<WaSeniorityUpdateComponent>;
    let service: WaSeniorityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaSeniorityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaSeniorityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaSeniorityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaSeniorityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaSeniority(123);
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
        const entity = new WaSeniority();
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
