import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Order } from '../../modals/order';
import { Observable, map } from 'rxjs';
import { ResponseModel } from '../../core/response.model';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  private apiUrl = "http://127.0.0.1:8081/api/v1/order";

  constructor(private http: HttpClient) { }

  getOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(this.apiUrl).pipe(
      map((res: any) => res.data)
    )
  }

  getOrder(id: number): Observable<Order> {
    return this.http.get<Order[]>(`${this.apiUrl}/${id}`).pipe(
      map((res: any) => res.data)
    )
  }

  getUsersOrders(): Observable<Order[]> {
    return this.http.get<Order[]>(`${this.apiUrl}`+"/user" ).pipe(
      map((res: any) => res.data)
    )
  }

  addOrder(order: Order): Observable<ResponseModel<Order>> {
    return this.http.post<ResponseModel<Order>>(this.apiUrl, order);
  }

  editOrder(id: number | undefined, order: Order): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, order);
  }

}
