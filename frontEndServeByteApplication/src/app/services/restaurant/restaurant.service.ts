import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http'
import { catchError, Observable, throwError } from 'rxjs';



@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  constructor(private http: HttpClient) { }


imageUpload(name: string,
  email : string,
  phoneNumber: number,
  city: string,
  profileImage : File):Observable<any>{

    var formData: any = new FormData();
    formData.append("name", name);
    formData.append("email", email);
    formData.append("phoneNumber", phoneNumber);
    formData.append("city", city);
    formData.append("fileToUpload", profileImage);
    return this.http.post('http://localhost:8080/api/restaurant',
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
