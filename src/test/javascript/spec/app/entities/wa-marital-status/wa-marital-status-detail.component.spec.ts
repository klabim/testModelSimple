import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { WaMaritalStatusDetailComponent } from 'app/entities/wa-marital-status/wa-marital-status-detail.component';
import { WaMaritalStatus } from 'app/shared/model/wa-marital-status.model';

describe('Component Tests', () => {
  describe('WaMaritalStatus Management Detail Component', () => {
    let comp: WaMaritalStatusDetailComponent;
    let fixture: ComponentFixture<WaMaritalStatusDetailComponent>;
    const route = ({ data: of({ waMaritalStatus: new WaMaritalStatus(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [WaMaritalStatusDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(WaMaritalStatusDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(WaMaritalStatusDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load waMaritalStatus on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.waMaritalStatus).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
