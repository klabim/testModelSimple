import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { ManagerialLinkUpdateComponent } from 'app/entities/managerial-link/managerial-link-update.component';
import { ManagerialLinkService } from 'app/entities/managerial-link/managerial-link.service';
import { ManagerialLink } from 'app/shared/model/managerial-link.model';

describe('Component Tests', () => {
  describe('ManagerialLink Management Update Component', () => {
    let comp: ManagerialLinkUpdateComponent;
    let fixture: ComponentFixture<ManagerialLinkUpdateComponent>;
    let service: ManagerialLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [ManagerialLinkUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ManagerialLinkUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManagerialLinkUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManagerialLinkService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ManagerialLink(123);
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
        const entity = new ManagerialLink();
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
