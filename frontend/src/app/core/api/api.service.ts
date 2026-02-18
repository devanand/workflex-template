import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PingResponse } from './ping.model';

@Injectable({ providedIn: 'root' })
export class ApiService {
  constructor(private readonly http: HttpClient) {}

  getPing(): Observable<PingResponse> {
    return this.http.get<PingResponse>('/api/ping');
  }
}
