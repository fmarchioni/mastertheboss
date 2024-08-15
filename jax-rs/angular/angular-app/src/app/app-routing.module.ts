import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CustomerCreateComponent } from './customer-create/customer-create.component';
import { CustomerListComponent } from './customer-list/customer-list.component';
import { CustomerUpdateComponent } from './customer-update/customer-update.component';

const routes: Routes = [
  { path: 'customers', component: CustomerListComponent },
  { path: '', redirectTo: 'customers', pathMatch: 'full' },
  { path: 'create-customer', component: CustomerCreateComponent },
  { path: 'update-customer/:id', component: CustomerUpdateComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
