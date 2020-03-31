import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IManagerialLink } from 'app/shared/model/managerial-link.model';

@Component({
  selector: 'jhi-managerial-link-detail',
  templateUrl: './managerial-link-detail.component.html'
})
export class ManagerialLinkDetailComponent implements OnInit {
  managerialLink: IManagerialLink | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ managerialLink }) => (this.managerialLink = managerialLink));
  }

  previousState(): void {
    window.history.back();
  }
}
