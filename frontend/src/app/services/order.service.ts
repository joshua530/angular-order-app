import { Injectable } from '@angular/core';
import { catchError, Observable, of, retry, Subject } from 'rxjs';
import { Order, OrderStatus, PersonOrders } from '../types/Order';
import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { NotificationService } from './notification.service';
import { environment } from '../../environments/environment.prod';
import { UserService } from './user.service';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-type': 'application/json',
  }),
};

@Injectable({
  providedIn: 'root',
})
export class OrderService {
  private endPoint: string = environment.baseUri + '/orders';

  constructor(
    private http: HttpClient,
    private notifier: NotificationService,
    private userService: UserService
  ) {}

  fetchAllOrders(): Observable<Order[]> {
    const endPoint = `${this.endPoint}/all`;
    const token = { token: this.userService.getAuthToken() };
    return this.http.post<Order[]>(endPoint, token, httpOptions).pipe(
      catchError((err) => {
        return of([]);
      })
    );
  }

  private handleOrderProcessingError(
    err: HttpErrorResponse
  ): Observable<Order | null> {
    this.notifier.notify('Error occured while processing order', 'danger');
    return of(null);
  }

  processOrder(orderId: number, status: OrderStatus): Observable<Order | null> {
    const token = this.userService.getAuthToken();
    const requestBody = { orderId, token, status };
    const endPoint = `${this.endPoint}/update`;
    return this.http
      .post<Order>(endPoint, requestBody, httpOptions)
      .pipe(catchError(this.handleOrderProcessingError));
  }

  private handleOrderFetchingError(
    err: HttpErrorResponse
  ): Observable<Order[]> {
    this.notifier.notify('Error occured while fetching orders', 'danger');
    return of([]);
  }

  private handleOrderPlacementError(
    err: HttpErrorResponse
  ): Observable<Order | null> {
    this.notifier.notify(
      'Error occured while processing order, please refresh page and try again',
      'danger'
    );
    return of(null);
  }

  /** fetch user orders */
  fetchOrders(userId: string): Observable<Order[]> {
    const endPoint = `${this.endPoint}/${userId}`;
    return this.http
      .get<Order[]>(endPoint)
      .pipe(catchError(this.handleOrderFetchingError));
  }

  /** save user's order */
  saveOrder(order: Order): void {
    this.http
      .post<Order>(this.endPoint, order, httpOptions)
      .pipe(catchError(this.handleOrderPlacementError))
      .subscribe((order) => {
        if (order === null)
          this.notifier.notify(
            'Error occured while processing order, please refresh page and try again',
            'danger'
          );
        else this.notifier.notify('Order placed successfully', 'success');
      });
  }
}
