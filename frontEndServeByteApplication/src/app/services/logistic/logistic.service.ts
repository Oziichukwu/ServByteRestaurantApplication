import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { Logistic } from 'src/app/shared/models/Logistic';

@Injectable({
  providedIn: 'root'
})
export class LogisticService {

  constructor(private http : HttpClient) {}

  imageUpload(name: string,
    email : string,
    phoneNumber: number,
    deliveryOption: string,
    profileImage : File):Observable<any>{

      var formData: any = new FormData();
      formData.append("name", name);
      formData.append("email", email);
      formData.append("phoneNumber", phoneNumber);
      formData.append("deliveryOption", deliveryOption);
      formData.append("fileToUpload", profileImage);
      return this.http.post('http://localhost:8080/api/logistic',
      formData, {
        reportProgress : true,
        observe: 'events'
      }).pipe(
        catchError((err: any) => {
          alert(err.message);
          return throwError(err.message);
        })
      )
  }
}
