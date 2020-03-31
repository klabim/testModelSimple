import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaManagementHrisDetailComponent } from 'app/entities/wa-management-hris/wa-management-hris-detail.component';
import { WaManagementHris } from 'app/shared/model/wa-management-hris.model';

describe('Component Tests', () => {
  describe('WaManagementHris Management Detail Component', () => {
    let comp: WaManagementHrisDetailComponent;
    let fixture: ComponentFixture<WaManagementHrisDetailComponent>;
    const route = ({ data: of({ waManagementHris: new WaManagementHris(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaManagementHrisDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaManagementHrisDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaManagementHrisDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waManagementHris on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waManagementHris).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
