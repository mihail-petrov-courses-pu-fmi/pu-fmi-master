import { Routes } from '@angular/router';
import { CustomerPage } from './features/customer/customer.component';
import { ProjectPage } from './features/project/project.component';

export const routes: Routes = [
  {
    path        : '',
    redirectTo  : 'customers',
    pathMatch   : 'full'
  },
  {
    path        : 'customers/:id/projects',
    component   : ProjectPage
  },
  {
    path        : 'customers',
    component   : CustomerPage
  }
];
