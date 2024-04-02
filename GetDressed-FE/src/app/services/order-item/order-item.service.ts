import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { OrderItem } from '../../modals/order-item';

@Injectable({
  providedIn: 'root'
})
export class OrderItemService {

  private apiUrl = "http://127.0.0.1:8081/api/v1/order_item";

  constructor(private http: HttpClient) { }

  getOrderItems(id: number | undefined): Observable<OrderItem[]> {
    return this.http.get<OrderItem[]>(`${this.apiUrl}` + "/order" + `/${id}`).pipe(
      map((res: any) => res.data)
    )
  }
}
