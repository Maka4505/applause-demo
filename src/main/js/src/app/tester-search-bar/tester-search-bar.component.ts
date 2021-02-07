import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {TesterWithExperience} from "../model/tester-with-experience";
import {TesterSearchService} from "../service/tester-search-service.service";
import {DropdownListItem} from "../model/dropdown-list-item";

@Component({
  selector: 'tester-search-bar',
  templateUrl: './tester-search-bar.component.html',
  styleUrls: ['./tester-search-bar.component.css']
})
export class TesterSearchBarComponent implements OnInit {

  @Output() searched = new EventEmitter<TesterWithExperience[]>();

  searchButtonLabel: string;
  countries: DropdownListItem[];
  devices: DropdownListItem[];

  constructor(private testerSearchService: TesterSearchService) {
  }

  ngOnInit() {
    this.testerSearchService.getAvailableDevices().subscribe(
      data => this.devices = data
    );
    this.testerSearchService.getAvailableCountries().subscribe(
      data => this.countries = data
    );
    this.searchButtonLabel = "Search";
  }

  handleSearchButtonClick(selectedCountries: DropdownListItem[], selectedDevices: DropdownListItem[]) {
    this.testerSearchService.findTestersByCriteria(selectedCountries, selectedDevices).subscribe(data => {
      this.searched.emit(data);
    });
  }

}
