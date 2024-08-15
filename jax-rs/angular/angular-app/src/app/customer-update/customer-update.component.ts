import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { CustomerService } from '../services/customer.service';
import { Customer } from '../customer.model';

@Component({
  selector: 'app-customer-update',
  templateUrl: './customer-update.component.html',
  styleUrls: ['./customer-update.component.css']
})
export class CustomerUpdateComponent implements OnInit {
  id!: number;
  customer: Customer = new Customer();

  constructor(private customerService: CustomerService,
    private route: ActivatedRoute, private router: Router) { }

  private getCustomerById() {
    this.id = this.route.snapshot.params['id'];
    this.customerService.getCustomerById(this.id).subscribe({
      next: (data) => {
        this.customer = data;
      },
      error: (e) => {
        console.log(e);
      }
    });
  }
  ngOnInit(): void {
    this.getCustomerById();
  }
  updateCustomer() {
    this.customerService.updateCustomer(this.id, this.customer).subscribe({
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
    this.updateCustomer();
  }
}
