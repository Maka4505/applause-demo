import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TesterResultListComponent } from './tester-result-list.component';

describe('TesterResultListComponent', () => {
  let component: TesterResultListComponent;
  let fixture: ComponentFixture<TesterResultListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TesterResultListComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterResultListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
