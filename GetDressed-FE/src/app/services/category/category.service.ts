import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, map } from 'rxjs';
import { Category } from '../../modals/category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  private apiUrl = "http://127.0.0.1:8081/api/v1/category/get";

  constructor(private http: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.apiUrl).pipe(
      map((res: any) => res.data)
    )
  }

}
