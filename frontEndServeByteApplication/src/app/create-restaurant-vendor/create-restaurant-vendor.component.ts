import { HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup} from '@angular/forms';
import { RestaurantService } from '../services/restaurant/restaurant.service';



@Component({
  selector: 'app-create-restaurant-vendor',
  templateUrl: './create-restaurant-vendor.component.html',
  styleUrls: ['./create-restaurant-vendor.component.css']
})
export class CreateRestaurantVendorComponent implements OnInit {


  form:FormGroup;
  progress:number=0
  msgs: any;
  constructor(public fb: FormBuilder, public restaurantService: RestaurantService) {

      this.form = this.fb.group({
        name : [''],
        email : [''],
        phoneNumber : [''],
        city : [''],
        logo : [null],
      })
     }

  ngOnInit(): void {
  }

  uploadFile(event:any){
    const file = event.target.file ? event.target.files[0] : '' ;
    console.log(file);
    this.form.patchValue({
      image:file
    });
    this.form.get('image') ?.updateValueAndValidity()
  }

  submitForm(){

    this.restaurantService.imageUpload(

      this.form.value.name,
      this.form.value.email,
      this.form.value.phoneNumber,
      this.form.value.city,
      this.form.value.logo,
      ).subscribe((event : HttpEvent<any>)=>{

        switch(event.type){

          case HttpEventType.UploadProgress:
             if(event.total){
               this.progress = Math.round((100 / event.total) * event.loaded);
               this.msgs = `Uploaded! ${this.progress}%`
             }
             break;
             case HttpEventType.Response:
                event.body;
                console.log(event.body)
        }
      })
  }

}
