import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaHostCompanyDetailComponent } from 'app/entities/wa-host-company/wa-host-company-detail.component';
import { WaHostCompany } from 'app/shared/model/wa-host-company.model';

describe('Component Tests', () => {
  describe('WaHostCompany Management Detail Component', () => {
    let comp: WaHostCompanyDetailComponent;
    let fixture: ComponentFixture<WaHostCompanyDetailComponent>;
    const route = ({ data: of({ waHostCompany: new WaHostCompany(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaHostCompanyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaHostCompanyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaHostCompanyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waHostCompany on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waHostCompany).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
