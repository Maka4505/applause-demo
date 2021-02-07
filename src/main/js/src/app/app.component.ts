import {Component} from '@angular/core';
import {TesterWithExperience} from "./model/tester-with-experience";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  searchResults: TesterWithExperience[] = [];

  onSearchResults(searchResults: TesterWithExperience[]) {
    this.searchResults = searchResults;
  }

}
