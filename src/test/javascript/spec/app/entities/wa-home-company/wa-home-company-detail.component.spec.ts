import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaHomeCompanyDetailComponent } from 'app/entities/wa-home-company/wa-home-company-detail.component';
import { WaHomeCompany } from 'app/shared/model/wa-home-company.model';

describe('Component Tests', () => {
  describe('WaHomeCompany Management Detail Component', () => {
    let comp: WaHomeCompanyDetailComponent;
    let fixture: ComponentFixture<WaHomeCompanyDetailComponent>;
    const route = ({ data: of({ waHomeCompany: new WaHomeCompany(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaHomeCompanyDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaHomeCompanyDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaHomeCompanyDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waHomeCompany on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waHomeCompany).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
