import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaPersonalAddressUpdateComponent } from 'app/entities/wa-personal-address/wa-personal-address-update.component';
import { WaPersonalAddressService } from 'app/entities/wa-personal-address/wa-personal-address.service';
import { WaPersonalAddress } from 'app/shared/model/wa-personal-address.model';

describe('Component Tests', () => {
  describe('WaPersonalAddress Management Update Component', () => {
    let comp: WaPersonalAddressUpdateComponent;
    let fixture: ComponentFixture<WaPersonalAddressUpdateComponent>;
    let service: WaPersonalAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaPersonalAddressUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaPersonalAddressUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaPersonalAddressUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaPersonalAddressService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaPersonalAddress(123);
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
        const entity = new WaPersonalAddress();
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
