import {ComponentFixture, TestBed} from '@angular/core/testing';

import {TesterSearchBarComponent} from './tester-search-bar.component';
import {TesterSearchService} from "../service/tester-search-service.service";
import {HttpClientTestingModule} from "@angular/common/http/testing";
import {DropdownListItem} from "../model/dropdown-list-item";
import {Observable} from "rxjs";
import {TesterWithExperience} from "../model/tester-with-experience";

describe('TesterSearchBarComponent', () => {
  const dummyCountries: DropdownListItem[] = [
    {id: 1, label: "GB"},
    {id: 2, label: "US"}
  ];
  const dummyDevices: DropdownListItem[] = [
    {id: 1, label: "iPhone 4"},
    {id: 2, label: "iPhone 4S"}
  ];
  const dummyTestersWithExperience: TesterWithExperience[] = [
    {fullName: "Janko Walski", experience: 7},
    {fullName: "Tomcio Paluch", experience: 1}
  ];
  class MockTesterSearchService{
    getAvailableDevices(): Observable<DropdownListItem[]>{
      return new Observable(subscriber => subscriber.next(dummyDevices));
    }
    getAvailableCountries(): Observable<DropdownListItem[]>{
      return new Observable(subscriber => subscriber.next(dummyCountries));
    }
    findTestersByCriteria(
      selectedCountries: DropdownListItem[],
      selectedDevices: DropdownListItem[]
    ): Observable<TesterWithExperience[]> {
      return new Observable(subscriber => subscriber.next(dummyTestersWithExperience));
    }
  }

  let component: TesterSearchBarComponent;
  let fixture: ComponentFixture<TesterSearchBarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      declarations: [TesterSearchBarComponent],
      providers: [{ provide: TesterSearchService, useClass: MockTesterSearchService }]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TesterSearchBarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should initialize with correct data', () => {
    expect(component.devices).toBe(dummyDevices);
    expect(component.countries).toBe(dummyCountries);
  });

  it('emits the searched event with correct data when button is clicked', () => {
    component.searched.subscribe((testersWithExperience: TesterWithExperience[]) =>
      expect(testersWithExperience).toBe(dummyTestersWithExperience)
    );
    component.handleSearchButtonClick(dummyCountries, dummyDevices);
  });

});
