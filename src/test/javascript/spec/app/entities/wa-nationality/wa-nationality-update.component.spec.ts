import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaNationalityUpdateComponent } from 'app/entities/wa-nationality/wa-nationality-update.component';
import { WaNationalityService } from 'app/entities/wa-nationality/wa-nationality.service';
import { WaNationality } from 'app/shared/model/wa-nationality.model';

describe('Component Tests', () => {
  describe('WaNationality Management Update Component', () => {
    let comp: WaNationalityUpdateComponent;
    let fixture: ComponentFixture<WaNationalityUpdateComponent>;
    let service: WaNationalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaNationalityUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaNationalityUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaNationalityUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaNationalityService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaNationality(123);
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
        const entity = new WaNationality();
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
