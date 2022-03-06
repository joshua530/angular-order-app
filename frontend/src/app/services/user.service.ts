import {
  HttpClient,
  HttpErrorResponse,
  HttpHeaders,
} from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, of, retry, Subject } from 'rxjs';
import { v4 } from 'uuid';
import { environment } from 'src/environments/environment.prod';
import { NotificationService } from './notification.service';
import { AuthToken } from '../types/AuthToken';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-type': 'application/json',
  }),
};

const notifier: NotificationService = new NotificationService();

@Injectable({
  providedIn: 'root',
})
export class UserService {
  subject: Subject<any> = new Subject();
  endPoint: string = environment.baseUri + '/users';

  constructor(
    private http: HttpClient,
    private notifier: NotificationService
  ) {}

  onAuthChange(): Observable<any> {
    return this.subject.asObservable();
  }

  getAuthToken(): string | null {
    return localStorage.getItem('authToken');
  }

  getUserId(): string {
    let userId: string | null = localStorage.getItem('userId');
    if (!userId) {
      userId = v4();
      localStorage.setItem('userId', userId);
    }
    return userId;
  }

  login(token: string) {
    localStorage.setItem('authToken', token);
    this.notifier.notify('User logged in successfully', 'success');
    this.subject.next('logged-in');
  }

  logout(): void {
    localStorage.removeItem('authToken');
    this.notifier.notify('User logged out successfully', 'success');
    this.subject.next('logged-out');
  }

  authenticationErrorHandler(e: HttpErrorResponse): Observable<AuthToken> {
    notifier.notify(
      'Error occured during authentication, refresh page and try again',
      'danger'
    );
    const badToken = { token: null };
    return of(badToken);
  }

  /** Used to give user access to the /admin route */
  authenticateUser(username: string, password: string): Observable<AuthToken> {
    const endPoint = this.endPoint + '/login';
    return this.http
      .post<AuthToken>(endPoint, { username, password }, httpOptions)
      .pipe(catchError(this.authenticationErrorHandler));
  }

  isAuthenticated(): Observable<AuthToken> {
    let adminToken = localStorage.getItem('authToken') ?? null;
    const nullToken: AuthToken = { token: null };
    if (adminToken === null) return of(nullToken);
    adminToken = adminToken.trim();
    if (adminToken === '') return of(nullToken);
    return this.validateToken(adminToken);
  }

  validateToken(authToken: string): Observable<AuthToken> {
    // token will be returned in the form {token: <authToken>}
    const endPoint = this.endPoint + '/validate-token';
    const badToken = { token: authToken };
    return this.http
      .post<AuthToken>(endPoint, badToken, httpOptions)
      .pipe(catchError(this.authenticationErrorHandler));
  }
}
