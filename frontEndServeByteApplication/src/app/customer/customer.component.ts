import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { CustomerService } from '../services/customer/customer.service';
import { Customer } from '../shared/models/Customer';

@Component({
  selector: 'app-customer',
  templateUrl: './customer.component.html',
  styleUrls: ['./customer.component.css']
})

export class CustomerComponent implements OnInit {

  customer : Customer = new Customer();
  constructor(private customerService: CustomerService, private router: Router) { }

  ngOnInit(): void {
  }

  saveCustomer(){
    this.customerService.createCustomer(this.customer).subscribe(data=>{
      console.log(data);
      this.goToHomePage();

    },
    error=>console.log(error));
  }

  goToHomePage(){
    this.router.navigate(["/"]);
  }

  onSubmit(){
    console.log(this.customer);
    this.saveCustomer();
  }

}
