import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';


class Customer {
  public id?: number;
  public name?: string;
  public numberOfProjects?: number;
}

type CustomerType = {
  id: number;
  name: string;
  numberOfProjects: number;
}


@Component({
  selector    : 'app-root',
  standalone  : true,
  imports     : [RouterOutlet],
  templateUrl : './app.component.html',
  styleUrl    : './app.component.css'
})
export class AppComponent {

  public customerCollection: CustomerType[] = [];

  // Ще ни трябва инструмент за HTTP заявки
  private httpClient = inject(HttpClient)


  public ngOnInit() {

    this.httpClient.get('http://localhost:8165/customers').subscribe((result: any) => {

        this.customerCollection = result.data;
    })
  }
}
