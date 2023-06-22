import {Customer} from "./customer.model";

export interface AccountsModel {
  id: string
  balance: number
  createdAt: string
  status: any
  customer: Customer
  overDraft: number
}
