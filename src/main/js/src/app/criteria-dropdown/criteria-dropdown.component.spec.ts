import {ComponentFixture, TestBed} from '@angular/core/testing';

import {CriteriaDropdownComponent} from './criteria-dropdown.component';

describe('CriteriaDropdownComponent', () => {
  let component: CriteriaDropdownComponent;
  let fixture: ComponentFixture<CriteriaDropdownComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [CriteriaDropdownComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CriteriaDropdownComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
