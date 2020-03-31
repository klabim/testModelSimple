import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaPersonalAddressDetailComponent } from 'app/entities/wa-personal-address/wa-personal-address-detail.component';
import { WaPersonalAddress } from 'app/shared/model/wa-personal-address.model';

describe('Component Tests', () => {
  describe('WaPersonalAddress Management Detail Component', () => {
    let comp: WaPersonalAddressDetailComponent;
    let fixture: ComponentFixture<WaPersonalAddressDetailComponent>;
    const route = ({ data: of({ waPersonalAddress: new WaPersonalAddress(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaPersonalAddressDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaPersonalAddressDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaPersonalAddressDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waPersonalAddress on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waPersonalAddress).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
