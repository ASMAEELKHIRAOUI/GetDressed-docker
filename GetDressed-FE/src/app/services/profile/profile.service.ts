import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { User } from '../../modals/user';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  private apiUrl = "http://127.0.0.1:8081/api/v1/user";

  constructor(private http: HttpClient) { }

  editUser(user: User): Observable<any> {
    return this.http.put(`${this.apiUrl}`, user);
  }
}
