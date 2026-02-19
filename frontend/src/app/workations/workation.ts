import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Workation } from './workation.model';

/**
 * Service responsible for calling the backend Workation API.
 * Keeps HTTP logic isolated from UI components (SRP principle).
 */
@Injectable({
  providedIn: 'root',
})
export class WorkationService {
  private readonly apiUrl = '/workflex/workation';

  constructor(private http: HttpClient) {}

  /**
   * Fetch all workations.
   */
  getWorkations(): Observable<Workation[]> {
    return this.http.get<Workation[]>(this.apiUrl);
  }
}
