import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from '../customer.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private basUrl = "http://localhost:8080/jaxrs-demo/customer"

  constructor(private httpClient: HttpClient) {

  }

  getCustomerList(): Observable<Customer[]> {
    return this.httpClient.get<Customer[]>(`${this.basUrl}`);
  }

  createCustomer(customer: Customer): Observable<Object> {
     console.log('Sending customer data:', customer);

     return this.httpClient.post(`${this.basUrl}`, customer, {
         headers: { 'Content-Type': 'application/json' }
       });

  }

  getCustomerById(id: number): Observable<Customer>{
    return this.httpClient.get<Customer>(`${this.basUrl}/${id}`);
  }

  updateCustomer(id:number, customer:Customer): Observable<Object>{
    return this.httpClient.put(`${this.basUrl}/${id}`, customer);
  }

  deleteCustomer(id:number): Observable<Object>{
    return this.httpClient.delete(`${this.basUrl}/${id}`);
  }
}
