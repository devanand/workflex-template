import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Workations } from './workations';

describe('Workations', () => {
  let component: Workations;
  let fixture: ComponentFixture<Workations>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Workations]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Workations);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
