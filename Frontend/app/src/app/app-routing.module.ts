import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {CustomerComponent} from "./customer/customer.component";
import {AccountComponent} from "./account/account.component";
import {NewCustomerComponent} from "./new-customer/new-customer.component";

const routes: Routes = [

  {path:"customer",component:CustomerComponent},
  {path:"accounts",component:AccountComponent},
  {path:"new",component:NewCustomerComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})


export class AppRoutingModule { }
