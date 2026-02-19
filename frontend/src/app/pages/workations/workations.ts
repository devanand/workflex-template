import { CommonModule } from '@angular/common';
import {
  AfterViewInit,
  ChangeDetectorRef,
  Component,
  DestroyRef,
  ViewChild,
  inject,
} from '@angular/core';

import { takeUntilDestroyed } from '@angular/core/rxjs-interop';

import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatIconModule } from '@angular/material/icon';

import { Workation } from '../../workations/workation.model';
import { WorkationService } from '../../workations/workation';

@Component({
  selector: 'app-workations',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatSortModule, MatIconModule],
  templateUrl: './workations.html',
  styleUrl: './workations.scss',
})
export class Workations implements AfterViewInit {
  private readonly api = inject(WorkationService);
  private readonly cdr = inject(ChangeDetectorRef);
  private readonly destroyRef = inject(DestroyRef);

  displayedColumns: string[] = [
    'employee',
    'origin',
    'destination',
    'startDate',
    'endDate',
    'workingDays',
    'risk',
  ];

  dataSource = new MatTableDataSource<Workation>([]);
  loading = true;

  @ViewChild(MatSort) sort!: MatSort;

  ngOnInit(): void {
    this.api
      .getWorkations()
      .pipe(takeUntilDestroyed(this.destroyRef))
      .subscribe({
        next: (rows) => {
          // 1) Set data
          this.dataSource.data = rows;

          // 2) Configure sorting (dates/numbers/risk label)
          this.dataSource.sortingDataAccessor = (item: Workation, property: string) => {
            switch (property) {
              case 'startDate':
                return new Date(item.startDate).getTime();
              case 'endDate':
                return new Date(item.endDate).getTime();
              case 'workingDays':
                return item.workingDays ?? 0;
              case 'risk':
                return this.riskLabel(item.risk);
              default:
                return (item as any)[property] ?? '';
            }
          };

          this.loading = false;

          // 3) Force a UI update (critical in zoneless / some setups)
          this.cdr.detectChanges();
        },
        error: (err) => {
          console.error('Failed to load workations', err);
          this.loading = false;
          this.cdr.detectChanges();
        },
      });
  }

  ngAfterViewInit(): void {
    // Attach MatSort after the view exists.
    this.dataSource.sort = this.sort;

    // Force UI update so table + sort directives sync correctly
    this.cdr.detectChanges();
  }

  // LOW + NO_RISK => "No risk"
  riskLabel(risk: Workation['risk']): string {
    return risk === 'HIGH' ? 'High risk' : 'No risk';
  }

  // Same label, different colors
  riskClass(risk: Workation['risk']): string {
    if (risk === 'HIGH') return 'risk-high';
    if (risk === 'LOW') return 'risk-low';
    return 'risk-no';
  }

  riskIcon(risk: Workation['risk']): string {
    switch (risk) {
      case 'HIGH':
        return 'assets/icons/red-risk.svg';
      case 'LOW':
        return 'assets/icons/yellow-risk.svg';
      case 'NO_RISK':
        return 'assets/icons/green-risk.svg';
      default:
        return '';
    }
  }

  flagFor(country: string): string {
    const map: Record<string, string> = {
      Germany: '🇩🇪',
      'United States': '🇺🇸',
      Ukraine: '🇺🇦',
      Belgium: '🇧🇪',
      Spain: '🇪🇸',
      Greece: '🇬🇷',
      India: '🇮🇳',
    };
    return map[country] ?? '🏳️';
  }
}
