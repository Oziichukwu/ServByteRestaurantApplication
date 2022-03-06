import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer } from 'src/app/shared/models/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseURL = "http://localhost:8080/api/user"

  constructor(private httpClient: HttpClient) { }


  createCustomer(customer:Customer): Observable<Object> {

    return this.httpClient.post(`${this.baseURL}`, customer);
  }
}
