import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CustomerPage } from './features/customer/customer.component';

@Component({
  selector    : 'app-root',
  standalone  : true,
  imports     : [RouterOutlet],
  templateUrl : './app.component.html',
  styleUrl    : './app.component.css'
})
export class AppComponent {

}
