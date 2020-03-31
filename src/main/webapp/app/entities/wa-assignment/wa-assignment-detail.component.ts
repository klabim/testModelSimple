import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IWaAssignment } from 'app/shared/model/wa-assignment.model';

@Component({
  selector: 'jhi-wa-assignment-detail',
  templateUrl: './wa-assignment-detail.component.html'
})
export class WaAssignmentDetailComponent implements OnInit {
  waAssignment: IWaAssignment | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ waAssignment }) => (this.waAssignment = waAssignment));
  }

  previousState(): void {
    window.history.back();
  }
}
