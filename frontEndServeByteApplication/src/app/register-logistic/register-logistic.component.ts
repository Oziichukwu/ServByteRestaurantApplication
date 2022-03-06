import { HttpEvent, HttpEventType } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { LogisticService } from '../services/logistic/logistic.service';
import { Logistic } from '../shared/models/Logistic';

@Component({
  selector: 'app-register-logistic',
  templateUrl: './register-logistic.component.html',
  styleUrls: ['./register-logistic.component.css']
})
export class RegisterLogisticComponent implements OnInit {


  form:FormGroup;
  progress:number=0
  msgs: any;
  constructor(public fb: FormBuilder, public logisticService: LogisticService) {

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

    this.logisticService.imageUpload(

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
