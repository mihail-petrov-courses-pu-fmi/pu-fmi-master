import { HttpClient } from "@angular/common/http"
import { inject, Injectable } from "@angular/core"
import { CustomerType } from "../models/customer.model";

@Injectable({
  providedIn: "root"
})
export class CustomerService {

  private httpClient = inject(HttpClient);

  public getAllCustomers() {
    return this.httpClient.get('http://localhost:8165/customers')
  }

  public updateCustomer($customer: CustomerType) {
    return this.httpClient.put('http://localhost:8165/customers', $customer);
  }

}
