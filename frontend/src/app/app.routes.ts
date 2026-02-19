import { Routes } from '@angular/router';
import { Workations } from './pages/workations/workations';

export const routes: Routes = [
  { path: '', redirectTo: 'workations', pathMatch: 'full' },
  { path: 'workations', component: Workations },
];
