import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Cart } from '../../modals/cart';
import { ResponseModel } from '../../core/response.model';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  private apiUrl = "http://127.0.0.1:8081/api/v1/cart";

  constructor(private http: HttpClient) { }

  getCartsByUser(): Observable<Cart[]> {
    return this.http.get<Cart[]>(this.apiUrl + "/" + localStorage.getItem('id')).pipe(
      map((res: any) => res.data)
    )
  }

  addCart(cart: Cart): Observable<ResponseModel<Cart>> {
    return this.http.post<ResponseModel<Cart>>(this.apiUrl, cart);
  }

  deleteCart(id: number | undefined): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  editCart(id: number | undefined, cart: Cart): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, cart);
  }
}