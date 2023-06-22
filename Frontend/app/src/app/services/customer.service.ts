import { Injectable } from '@angular/core';

import {HttpClient, HttpClientModule} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../model/customer.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private  http:HttpClient) { }

  public getCustomers():Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>("http://localhost:8080")
  }

  public SearchCustomers(kw:string):Observable<Array<Customer>>{
    return this.http.get<Array<Customer>>(`http://localhost:8080/customers/search?keyword=${kw}`)
  }

  public saveCustomer(customer:Customer):Observable<Customer>{
    return this.http.post<Customer>("http://localhost:8080/save",customer)
  }
  public deleteCustomer(id:number){
    return this.http.delete(`http://localhost:8080/customers/${id}`)
  }









}
