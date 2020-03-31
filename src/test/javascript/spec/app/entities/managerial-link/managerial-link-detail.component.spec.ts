import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TestModelSimpleTestModule } from '../../../test.module';
import { ManagerialLinkDetailComponent } from 'app/entities/managerial-link/managerial-link-detail.component';
import { ManagerialLink } from 'app/shared/model/managerial-link.model';

describe('Component Tests', () => {
  describe('ManagerialLink Management Detail Component', () => {
    let comp: ManagerialLinkDetailComponent;
    let fixture: ComponentFixture<ManagerialLinkDetailComponent>;
    const route = ({ data: of({ managerialLink: new ManagerialLink(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [ManagerialLinkDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ManagerialLinkDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ManagerialLinkDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load managerialLink on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.managerialLink).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
