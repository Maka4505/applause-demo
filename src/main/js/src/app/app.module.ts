import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppComponent} from './app.component';
import {TesterResultListComponent} from './tester-result-list/tester-result-list.component';
import {TesterService} from "./service/tester-service.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    TesterResultListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [TesterService],
  bootstrap: [AppComponent]
})
export class AppModule { }
