import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaPersonalAddressComponent } from 'app/entities/wa-personal-address/wa-personal-address.component';
import { WaPersonalAddressService } from 'app/entities/wa-personal-address/wa-personal-address.service';
import { WaPersonalAddress } from 'app/shared/model/wa-personal-address.model';

describe('Component Tests', () => {
  describe('WaPersonalAddress Management Component', () => {
    let comp: WaPersonalAddressComponent;
    let fixture: ComponentFixture<WaPersonalAddressComponent>;
    let service: WaPersonalAddressService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaPersonalAddressComponent]
      })
        .overrideTemplate(WaPersonalAddressComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(WaPersonalAddressComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(WaPersonalAddressService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new WaPersonalAddress(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.waPersonalAddresses && comp.waPersonalAddresses[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
