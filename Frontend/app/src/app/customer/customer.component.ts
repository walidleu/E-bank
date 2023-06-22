import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {error} from "@angular/compiler-cli/src/transformers/util";
import {CustomerService} from "../services/customer.service";
import {Observable, throwError} from "rxjs";
import {Customer} from "../model/customer.model";
import { catchError } from 'rxjs/operators';
import {Form, FormBuilder, FormGroup, ReactiveFormsModule} from "@angular/forms";

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})
export class CustomerComponent implements OnInit{

  customers$! :Observable<Array<Customer>> ;
  errorMesage!:string;


  searchformGroup!: FormGroup ;

  constructor( private http:HttpClient,private customerService : CustomerService,private fb:FormBuilder){
  }



  ngOnInit(): void {

     this.customers$= this.customerService.getCustomers().pipe(
       catchError(error => {
         this.errorMesage = error
         return throwError(error)
       })
     )

   this.searchformGroup  =  this.fb.group({

      keyword:this.fb.control("")

    })


  }

  public handleSearch(){
      let kw = this.searchformGroup?.value.keyword
    this.customers$ = this.customerService.SearchCustomers(kw).pipe(
      catchError(error => {
        this.errorMesage=error
        return throwError(error)
      })
    )
  }


  public handleDelete(c: Customer) {
    let conf=  confirm("Are you sure ? ")
      if (!conf) return;

      this.customerService.deleteCustomer(c.id).subscribe({
        next:resp=>{

          this.handleSearch()
        },error:err=>{
          console.log(err)
        }
      })
  }




}
