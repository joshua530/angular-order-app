import { Injectable } from '@angular/core';
import Product from '../types/Product';
import { Observable, Subject, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  products: Product[];
  private subject: Subject<Product[]> = new Subject();
  private endPoint = environment.baseUri + '/products';

  constructor(private http: HttpClient) {}

  onProductUpdate(): Observable<Product[]> {
    return this.subject.asObservable();
  }

  fetchProducts(): Observable<Product[]> {
    const observable = this.http.get<Product[]>(this.endPoint);
    observable.subscribe((prods) => (this.products = prods));
    return observable;
  }

  getProduct(id: number): Observable<Product> {
    const productUrl = this.endPoint + '/' + id;
    return this.http.get<Product>(productUrl);
  }
}
