import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CustomerService } from './services/customer.service';
import { CustomerType } from './models/customer.model';
import { FormsModule } from '@angular/forms';


@Component({
  selector    : 'app-root',
  standalone  : true,
  imports     : [RouterOutlet, FormsModule],
  templateUrl : './app.component.html',
  styleUrl    : './app.component.css'
})
export class AppComponent {

  public customerCollection: CustomerType[] = [];
  public isEditFormVisible    = false;
  public isCreateFormVisible  = false;
  public selectedCustomer: CustomerType | null = null;

  // Ще ни трябва инструмент за HTTP заявки
  // private httpClient = inject(HttpClient)

  // Добавям си сервиза за работа с customer обекти
  private customerService = inject(CustomerService)

  // ПРи зареждане на компонента
  public ngOnInit() {
    this.fetchAllCustomers();
  }

  public fetchAllCustomers() {

    this.customerService.getAllCustomers().subscribe((result: any) => {
      this.customerCollection = result.data;
  })
  }

  public processOnCreate() {

    this.isCreateFormVisible  = true;
    this.selectedCustomer   = null;
  }

  public processOnEdit($selectedElement: CustomerType) {

    this.isEditFormVisible = true;
    this.selectedCustomer = $selectedElement;
  }

  public processOnSave() {

    this.customerService.updateCustomer(this.selectedCustomer!).subscribe((result) => {
      console.log(result);
    });
  }

  public processOnDelete($selectedElement: CustomerType) {
    this.customerService.deleteCustomer($selectedElement.id!).subscribe((result: any) => {
      this.fetchAllCustomers();
    });
  }

  public processOnCreateCustomer($inputValue: string) {
    this.customerService.createNewCustomer({
      name: $inputValue
    }).subscribe((result: any) => {

        console.log(result);
        this.fetchAllCustomers();
    });
  }

  public processOnChangeCustomerName($customerInput: string) {

    if(this.selectedCustomer) {
      this.selectedCustomer.name = $customerInput;
    }
  }
}
