import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaHomeCompanyUpdateComponent } from 'app/entities/wa-home-company/wa-home-company-update.component';
import { WaHomeCompanyService } from 'app/entities/wa-home-company/wa-home-company.service';
import { WaHomeCompany } from 'app/shared/model/wa-home-company.model';

describe('Component Tests', () => {
  describe('WaHomeCompany Management Update Component', () => {
    let comp: WaHomeCompanyUpdateComponent;
    let fixture: ComponentFixture<WaHomeCompanyUpdateComponent>;
    let service: WaHomeCompanyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaHomeCompanyUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaHomeCompanyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaHomeCompanyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaHomeCompanyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaHomeCompany(123);
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
        const entity = new WaHomeCompany();
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
