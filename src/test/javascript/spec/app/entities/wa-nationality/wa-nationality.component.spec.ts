import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaNationalityComponent } from 'app/entities/wa-nationality/wa-nationality.component';
import { WaNationalityService } from 'app/entities/wa-nationality/wa-nationality.service';
import { WaNationality } from 'app/shared/model/wa-nationality.model';

describe('Component Tests', () => {
  describe('WaNationality Management Component', () => {
    let comp: WaNationalityComponent;
    let fixture: ComponentFixture<WaNationalityComponent>;
    let service: WaNationalityService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaNationalityComponent]
      })
        .overrideTemplate(WaNationalityComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaNationalityComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaNationalityService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaNationality(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waNationalities && comp.waNationalities[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
