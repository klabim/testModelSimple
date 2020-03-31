import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaHostCompanyUpdateComponent } from 'app/entities/wa-host-company/wa-host-company-update.component';
import { WaHostCompanyService } from 'app/entities/wa-host-company/wa-host-company.service';
import { WaHostCompany } from 'app/shared/model/wa-host-company.model';

describe('Component Tests', () => {
  describe('WaHostCompany Management Update Component', () => {
    let comp: WaHostCompanyUpdateComponent;
    let fixture: ComponentFixture<WaHostCompanyUpdateComponent>;
    let service: WaHostCompanyService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaHostCompanyUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaHostCompanyUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaHostCompanyUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaHostCompanyService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaHostCompany(123);
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
        const entity = new WaHostCompany();
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
