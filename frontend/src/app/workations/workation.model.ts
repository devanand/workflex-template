export interface Workation {
  workationId: string;
  employee: string;
  origin: string;
  destination: string;
  startDate: string; // ISO date string from backend (e.g. "2024-01-02")
  endDate: string; // ISO date string from backend (e.g. "2024-01-02")
  workingDays: number;
  risk: 'HIGH' | 'LOW' | 'NO_RISK';
}
