import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CartPageComponent } from './cart-page/cart-page.component';
import { CreateRestaurantVendorComponent } from './create-restaurant-vendor/create-restaurant-vendor.component';
import { CustomerComponent } from './customer/customer.component';
import { FoodPageComponent } from './food-page/food-page.component';
import {HomeComponent} from './home/home.component'
import { RegisterLogisticComponent } from './register-logistic/register-logistic.component';


const routes: Routes = [

  {path: '',component: HomeComponent},
  {path:'search/:searchTerm', component: HomeComponent},
  {path: 'tag/:tag', component: HomeComponent},
  {path: 'food/:id', component: FoodPageComponent},
  {path: 'cart-page', component: CartPageComponent},
  {path: 'register-restaurant', component: CreateRestaurantVendorComponent},
  {path: 'register-customer', component: CustomerComponent},
  {path: 'register-logistic', component: RegisterLogisticComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
