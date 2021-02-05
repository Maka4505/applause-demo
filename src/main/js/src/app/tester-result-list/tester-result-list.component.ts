import {Component, OnInit} from '@angular/core';
import {TesterService} from "../service/tester-service.service";
import {Tester} from "../model/tester";

@Component({
  selector: 'tester-result-list',
  templateUrl: './tester-result-list.component.html',
  styleUrls: ['./tester-result-list.component.css']
})
export class TesterResultListComponent implements OnInit {

  testers: Tester[];

  constructor(private testerService: TesterService) {
  }

  ngOnInit() {
    console.log("TesterResultListComponent");
    this.testerService.findAll().subscribe(data => {
      console.log("TesterResultListComponent", data);
      this.testers = data;
    });
  }

}
