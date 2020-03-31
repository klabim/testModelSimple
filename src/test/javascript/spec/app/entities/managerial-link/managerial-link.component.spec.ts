import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { TestModelSimpleTestModule } from '../../../test.module';
import { ManagerialLinkComponent } from 'app/entities/managerial-link/managerial-link.component';
import { ManagerialLinkService } from 'app/entities/managerial-link/managerial-link.service';
import { ManagerialLink } from 'app/shared/model/managerial-link.model';

describe('Component Tests', () => {
  describe('ManagerialLink Management Component', () => {
    let comp: ManagerialLinkComponent;
    let fixture: ComponentFixture<ManagerialLinkComponent>;
    let service: ManagerialLinkService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TestModelSimpleTestModule],
        declarations: [ManagerialLinkComponent]
      })
        .overrideTemplate(ManagerialLinkComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ManagerialLinkComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ManagerialLinkService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new ManagerialLink(123)],
            headers
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.managerialLinks && comp.managerialLinks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
