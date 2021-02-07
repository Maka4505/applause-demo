import {Component, Input} from '@angular/core';
import {IDropdownSettings} from "ng-multiselect-dropdown";
import {DropdownListItem} from "../model/dropdown-list-item";

@Component({
  selector: 'criteria-dropdown',
  templateUrl: './criteria-dropdown.component.html',
  styleUrls: ['./criteria-dropdown.component.css']
})
export class CriteriaDropdownComponent {

  @Input('placeholder') placeholder: string;

  @Input('dropdownListItems') dropdownListItems: DropdownListItem[];

  selectedItems: DropdownListItem[] = [];

  dropdownSettings: IDropdownSettings = {
    singleSelection: false,
    idField: 'id',
    textField: 'label',
    selectAllText: 'Select All',
    unSelectAllText: 'Unselect All',
    itemsShowLimit: 4,
    allowSearchFilter: true
  };

}
