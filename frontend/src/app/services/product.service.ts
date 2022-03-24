import { Injectable } from '@angular/core';
import Product, { CategorizedProducts } from '../types/Product';
import { Observable, Subject, tap } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment.prod';

@Injectable({
  providedIn: 'root',
})
export class ProductService {
  products: CategorizedProducts;
  private subject: Subject<Product[]> = new Subject();
  private endPoint = environment.baseUri + '/products';

  constructor(private http: HttpClient) {}

  onProductUpdate(): Observable<Product[]> {
    return this.subject.asObservable();
  }

  fetchProducts(): Observable<CategorizedProducts> {
    const observable = this.http.get<CategorizedProducts>(this.endPoint);
    observable.subscribe((prods) => (this.products = prods));
    return observable;
  }

  getProduct(id: number): Observable<Product> {
    const productUrl = this.endPoint + '/' + id;
    return this.http.get<Product>(productUrl);
  }
}
