import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tester} from "../model/tester";

@Injectable()
export class TesterService {
  private readonly testersUrl: string;

  constructor(private http: HttpClient) {
    this.testersUrl = 'http://localhost:8080/testersWithExperience';
  }

  public findAll(): Observable<Tester[]> {
    let query = "?countries=US,GB&devices=2";
    let fullUrl = this.testersUrl + query;
    console.log("findAll");
    console.log(fullUrl);
    return this.http.get<Tester[]>(fullUrl);
  }

}
