import { HttpClient } from '@angular/common/http';
import { Component, inject } from '@angular/core';
import { RouterOutlet } from '@angular/router';

@Component({
  selector    : 'app-root',
  standalone  : true,
  imports     : [RouterOutlet],
  templateUrl : './app.component.html',
  styleUrl    : './app.component.css'
})
export class AppComponent {

  // Ще ни трябва инструмент за HTTP заявки
  public httpClient = inject(HttpClient)

  public ngOnInit() {
    console.log("Start component");
  }




  public title = 'Solar Park';

  public getTitle() {
    return "Solar Park Title function"
  }


}
