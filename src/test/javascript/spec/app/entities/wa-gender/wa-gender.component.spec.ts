import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaGenderComponent } from 'app/entities/wa-gender/wa-gender.component';
import { WaGenderService } from 'app/entities/wa-gender/wa-gender.service';
import { WaGender } from 'app/shared/model/wa-gender.model';

describe('Component Tests', () => {
  describe('WaGender Management Component', () => {
    let comp: WaGenderComponent;
    let fixture: ComponentFixture<WaGenderComponent>;
    let service: WaGenderService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaGenderComponent]
      })
        .overrideTemplate(WaGenderComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaGenderComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaGenderService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaGender(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waGenders && comp.waGenders[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
