import {TestBed} from '@angular/core/testing';
import {TesterSearchService} from "./tester-search-service.service";
import {DropdownListItem} from "../model/dropdown-list-item";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
import {environment} from "../../environments/environment";
import {TesterWithExperience} from "../model/tester-with-experience";

describe('TesterSearchService', () => {

  let service: TesterSearchService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TesterSearchService]
    });
    service = TestBed.inject(TesterSearchService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  });

  const dummyCountries: DropdownListItem[] = [
    {id: 1, label: "GB"},
    {id: 2, label: "US"}
  ];

  const dummyDevices: DropdownListItem[] = [
    {id: 1, label: "iPhone 4"},
    {id: 2, label: "iPhone 4S"}
  ];

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return an Observable<DropdownListItem[]> of available devices', () => {
    service.getAvailableDevices().subscribe(availableDevices => {
      expect(availableDevices.length).toBe(2);
      expect(availableDevices).toEqual(dummyDevices);
    });

    const req = httpMock.expectOne(`${environment.devicesUrl}`);
    expect(req.request.method).toBe("GET");
    req.flush(dummyDevices);
  });

  it('should return an Observable<DropdownListItem[]> of available countries', () => {
    service.getAvailableCountries().subscribe(availableCountries => {
      expect(availableCountries.length).toBe(2);
      expect(availableCountries).toEqual(dummyCountries);
    });

    const req = httpMock.expectOne(`${environment.countriesUrl}`);
    expect(req.request.method).toBe("GET");
    req.flush(dummyCountries);
  });

  it('should return an Observable<TesterWithExperience[]> for requested criteria', () => {
    const dummyTestersWithExperience: TesterWithExperience[] = [
      {fullName: "Janko Walski", experience: 7},
      {fullName: "Tomcio Paluch", experience: 1}
    ];

    service.findTestersByCriteria(dummyCountries, dummyDevices).subscribe(testersWithExperience => {
      expect(testersWithExperience.length).toBe(2);
      expect(testersWithExperience).toEqual(dummyTestersWithExperience);
    });

    const req = httpMock.expectOne(`${environment.searchTestersUrl}?countries=GB,US&devices=1,2`);
    expect(req.request.method).toBe("GET");
    req.flush(dummyTestersWithExperience);
  });


});
