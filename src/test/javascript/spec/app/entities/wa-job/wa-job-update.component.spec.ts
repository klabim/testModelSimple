import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaJobUpdateComponent } from 'app/entities/wa-job/wa-job-update.component';
import { WaJobService } from 'app/entities/wa-job/wa-job.service';
import { WaJob } from 'app/shared/model/wa-job.model';

describe('Component Tests', () => {
  describe('WaJob Management Update Component', () => {
    let comp: WaJobUpdateComponent;
    let fixture: ComponentFixture<WaJobUpdateComponent>;
    let service: WaJobService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaJobUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(WaJobUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaJobUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaJobService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new WaJob(123);
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
        const entity = new WaJob();
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
