import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

const BASE_URL = 'http://127.0.0.1:8081/api/v1/';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) {}

  login(email: string, password: string) {
    return this.http.post(`${BASE_URL}auth/authenticate`, {
      email,
      password,
    });
  }

  register(email: string, password: string, firstName: string, lastName: string) {
    return this.http.post(`${BASE_URL}auth/register`, {
      email,
      password,
      firstName,
      lastName
    });
  }

  logout(): void {
    localStorage.clear();
    console.log('logged out');
  }
}
