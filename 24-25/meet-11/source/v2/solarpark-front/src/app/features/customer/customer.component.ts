import { Component, inject } from "@angular/core";
import { CustomerType } from "../../models/customer.model";
import { CustomerService } from "../../services/customer.service";
import { FormsModule } from "@angular/forms";
import { DataGridComponent, DataGridHeader } from "../../components/data-grid/data-grid.component";
import { Router } from "@angular/router";

@Component({
  standalone  : true,
  imports     : [FormsModule, DataGridComponent],
  templateUrl : './customer.component.html',
  styleUrl    : './customer.component.css'
})
export class CustomerPage {

  public dataGridMapping: DataGridHeader[] = [
    { column: 'Customer name' , value: 'name'},
    { column: '№ projects'    , value: 'numberOfProjects'}
  ];

  public customerCollection: CustomerType[] = [];
  public isEditFormVisible    = false;
  public isCreateFormVisible  = false;
  public selectedCustomer: CustomerType | null = null;

  // Добавям си сервиза за работа с customer обекти
  private customerService = inject(CustomerService)
  private router          = inject(Router);

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

  public processOnNavigate($customer: CustomerType) {
    this.router.navigateByUrl(`/customers/${$customer.id}/projects`)
  }
}
