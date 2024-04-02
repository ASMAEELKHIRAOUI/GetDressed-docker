import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Product } from '../../modals/product';
import { ResponseModel } from '../../core/response.model';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private apiUrl = "http://127.0.0.1:8081/api/v1/product";

  constructor(private http: HttpClient) { }

  getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + "/get").pipe(
      map((res: any) => res.data)
    )
  }

  search(search: string): Observable<Product[]> {
    return this.http.get<Product[]>(this.apiUrl + "/search/" + search).pipe(
      map((res: any) => res.data)
    )
  }

  getProduct(id: number): Observable<Product> {
    return this.http.get<Product[]>(this.apiUrl + "/get" + `/${id}`).pipe(
      map((res: any) => res.data)
    )
  }

  addProduct(product: Product): Observable<ResponseModel<Product>> {
    return this.http.post<ResponseModel<Product>>(this.apiUrl, product);
  }

  deleteProduct(id: number | undefined): Observable<any> {
    return this.http.delete(`${this.apiUrl}/${id}`);
  }

  editProduct(id: number | undefined, product: Product): Observable<any> {
    return this.http.put(`${this.apiUrl}/${id}`, product);
  }

  productsByCategory(category: string | undefined): Observable<Product[]> {
    return this.http.get(this.apiUrl + "/get/category" + `/${category}`).pipe(
      map((res: any) => res.data)
    );
  }
}
