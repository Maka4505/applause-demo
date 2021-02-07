import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {TesterWithExperience} from "../model/tester-with-experience";
import {DropdownListItem} from "../model/dropdown-list-item";
import {environment} from "../../environments/environment";

@Injectable()
export class TesterSearchService {

  private readonly searchTestersUrl: string;
  private readonly countriesUrl: string;
  private readonly devicesUrl: string;

  constructor(private http: HttpClient) {
    this.devicesUrl = environment.devicesUrl;
    this.countriesUrl = environment.countriesUrl;
    this.searchTestersUrl = environment.searchTestersUrl;
  }

  public getAvailableDevices(): Observable<DropdownListItem[]> {
    return this.http.get<DropdownListItem[]>(this.devicesUrl);
  }

  public getAvailableCountries(): Observable<DropdownListItem[]> {
    return this.http.get<DropdownListItem[]>(this.countriesUrl);
  }

  public findTestersByCriteria(
    selectedCountries: DropdownListItem[],
    selectedDevices: DropdownListItem[]
  ): Observable<TesterWithExperience[]> {
    return this.http.get<TesterWithExperience[]>(
      this.buildSearchTestersURL(selectedCountries, selectedDevices)
    );
  }

  private buildSearchTestersURL(selectedCountries: DropdownListItem[], selectedDevices: DropdownListItem[]): string {
    let countriesParameter = selectedCountries.map(c => c.label).join(",");
    let devicesParameter = selectedDevices.map(d => d.id).join(",");
    let query = `?countries=${countriesParameter}&devices=${devicesParameter}`;

    return `${this.searchTestersUrl}${query}`;
  }

}
