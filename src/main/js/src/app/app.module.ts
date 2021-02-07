import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {TesterResultListComponent} from './tester-result-list/tester-result-list.component';
import {TesterSearchService} from "./service/tester-search-service.service";
import {HttpClientModule} from "@angular/common/http";
import {AppHeaderComponent} from './app-header/app-header.component';
import {TesterSearchBarComponent} from './tester-search-bar/tester-search-bar.component';
import {NgMultiSelectDropDownModule} from "ng-multiselect-dropdown";
import {FormsModule} from "@angular/forms";
import {CriteriaDropdownComponent} from './criteria-dropdown/criteria-dropdown.component';

@NgModule({
  declarations: [
    AppComponent,
    TesterResultListComponent,
    AppHeaderComponent,
    TesterSearchBarComponent,
    CriteriaDropdownComponent
  ],
  imports: [
    FormsModule,
    BrowserModule,
    HttpClientModule,
    NgMultiSelectDropDownModule.forRoot()
  ],
  providers: [TesterSearchService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
