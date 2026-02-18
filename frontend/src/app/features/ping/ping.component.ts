import { Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ApiService } from '../../core/api/api.service';
import { Observable } from 'rxjs';
import { PingResponse } from '../../core/api/ping.model';

@Component({
  selector: 'app-ping',
  standalone: true,
  imports: [CommonModule],
  template: `
    <div style="padding: 16px;">
      <h1>Health page</h1>

      <ng-container *ngIf="ping$ | async as ping; else loading">
        <p><strong>Status:</strong> {{ ping.status }}</p>
        <p><strong>Timestamp:</strong> {{ ping.timestamp }}</p>
      </ng-container>

      <ng-template #loading>
        <p>Loading…</p>
      </ng-template>
    </div>
  `,
})
export class PingComponent {
  private readonly api = inject(ApiService);
  readonly ping$: Observable<PingResponse> = this.api.getPing();
}
