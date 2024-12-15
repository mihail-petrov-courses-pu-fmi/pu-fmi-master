import { Component, inject, OnInit } from '@angular/core';
import { ProductApi } from './services/product.api';

@Component({
  selector    : 'app-root',
  templateUrl : './app.component.html',
  styleUrl    : './app.component.css'
})
export class AppComponent implements OnInit {

  private productApi = inject(ProductApi)

  public productCollection: any[] = [];

  public ngOnInit() {

    this.productApi.getAllProducts().subscribe((response: any) => {
      this.productCollection = response.data;
    })
  }


  public processBuy(product: any) {
    console.log(product);
  }
}
