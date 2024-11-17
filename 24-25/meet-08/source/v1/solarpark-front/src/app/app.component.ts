import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CustomerService } from './services/customer.service';
import { CustomerType } from './models/customer.model';


@Component({
  selector    : 'app-root',
  standalone  : true,
  imports     : [RouterOutlet],
  templateUrl : './app.component.html',
  styleUrl    : './app.component.css'
})
export class AppComponent {

  public customerCollection: CustomerType[] = [];
  public isEditFormVisible = false;
  public selectedCustomer: CustomerType | null = null;

  // Ще ни трябва инструмент за HTTP заявки
  // private httpClient = inject(HttpClient)

  // Добавям си сервиза за работа с customer обекти
  private customerService = inject(CustomerService)


  public ngOnInit() {

    this.customerService.getAllCustomers().subscribe((result: any) => {
        this.customerCollection = result.data;
    })
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

  public processOnChangeCustomerName($customerInput: string) {

    if(this.selectedCustomer) {
      this.selectedCustomer.name = $customerInput;
    }
  }
}
