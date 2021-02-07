import {Component, Input} from '@angular/core';
import {TesterWithExperience} from "../model/tester-with-experience";

@Component({
  selector: 'tester-result-list',
  templateUrl: './tester-result-list.component.html',
  styleUrls: ['./tester-result-list.component.css']
})
export class TesterResultListComponent {

  @Input("searchResults")
  testersWithExperience: TesterWithExperience[];

}
