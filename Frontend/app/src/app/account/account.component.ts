import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {AccountServiceService} from "../services/account-service.service";
import {Observable} from "rxjs";
import {AccountsModel} from "../model/accounts.model";

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.css']
})
export class AccountComponent implements OnInit{


  accountForm!: FormGroup ;
  account$! :any ;

  operationForm!:FormGroup ;
  constructor(private fb:FormBuilder,private accountService:AccountServiceService) {
  }
  ngOnInit(): void {

  this.accountForm=this.fb.group({
    accountId:this.fb.control('')
  })
    this.operationForm=this.fb.group({

      operationType:this.fb.control(null),
      amount:this.fb.control(0),
      description:this.fb.control(null),
      acountDestination : this.fb.control(null)



    })

  }

  handleSearchAccount(){
    let id=this.accountForm.value
     this.accountService.getAccount(id.accountId).subscribe({
       next:value => {
         this.account$=value
         console.log(value)
       },error:err=>{
         console.log(err)
    }
     })

  }

  handleAccountOperation(){

  }



}
