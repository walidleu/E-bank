import { Injectable } from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {AccountsModel} from "../model/accounts.model";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class AccountServiceService {

  constructor( private http:HttpClient) { }


  public getAccount(accountId:string):Observable<any>{

    return this.http.get<AccountsModel>(`http://localhost:8080/history/${accountId}/pageOperation`)

  }





}
