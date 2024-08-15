import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { Customer } from '../customer.model';

@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {

  customer: Customer = new Customer();

  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
  }

  saveCustomer() {
    this.customerService.createCustomer(this.customer).subscribe({
      next: (data) => {
        console.log(data);
        this.redirectToCustomerList();
      },
      error: (e) => {
        console.log(e);
      }
    });
  }

  redirectToCustomerList() {
    this.router.navigate(['/customers']);
  }

  onSubmit() {
    console.log(this.customer);
    this.saveCustomer();
  }
}