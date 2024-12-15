import { HttpClient } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";

@Injectable({
  providedIn: 'root'
})
export class ProductApi {

  private http = inject(HttpClient)

  public getAllProducts() {
    return this.http.get(`http://localhost:8751/products`);
  }

}
